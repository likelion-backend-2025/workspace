package org.example.jwtexam.jwt.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String)request.getAttribute("exception");

        //1. request.getAttribute("exception"); 이 null일 경우 어떻게 처리할까요?
        //spirng security가 전달한 AuthenticationException 을 로그로 남긴다.
        if(exception==null){
            log.error("Commence Occurred ::  "+ authException.getMessage());
        }

        //요청이 뷰를 요청하는 것인지 데이터를 요청하는 것인지에 따라서 다르게 처리 될 필요가 있다.
        //Restful 로 요청한건지..  그냥 페이지를 페이지를 요청한것인지..   구분하고 싶다.
        if(isRestRequest(request)){
            //rest 요청이 들어왔을 때 처리
            handleRestResponse(exception,request,response);

        }else{
            //페이지로 요청이 들어왔을 때 처리.
           handlePageResponse(exception,request,response,authException);
        }

    }
    private void handleRestResponse(String exception,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.error("Rest Request - Commence Occurred ::  "+ exception);
        //jwt를 이용해서 인증할 때 발생할 수 있는 예외들이 좀 있을 것 같아요.

        JwtExceptionCode code = JwtExceptionCode.findByCode(exception);

        //UNKNOWN_ERROR 로 나왔다면, authException으로 에러를 남겨볼까요?
        if(code==JwtExceptionCode.UNKNOWN_ERROR && exception==null){
            //이런 경우 해야할 일이 있다면 여기 구현!!
            log.error("Rest Request - (authExceptin) : 인증 예외 발생!!  ");
        }

        //나머지 경우는 code 값에 따라서 응답으로 보내주면 되겠죠??
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        //에러 코드와 에러메시지를 넣어주면 될꺼예요.
        HashMap<String,Object> errorInfo = new HashMap<>();
        errorInfo.put("message",code.getMessage());
        errorInfo.put("code",code.getCode());

        String responseJson = objectMapper.writeValueAsString(errorInfo);
        response.getWriter().print(responseJson);
    }


    //페이지로 요청이 들어왔을 때 인증되지 않은 사용자라면 /loginform으로 리다이렉션 하게 구현
    private void handlePageResponse(String exception,HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Page Request - Commence : {}", exception);

        if(exception == null){
            //추가적으로 할일이 있다면 이쪽에 구현 할 수 있을 꺼예요.  (예외처리 로직등..)
            log.error("Page Request - Commence : {}", exception);
        }else{
            log.error("Page Request - Commence : {}", authException.getMessage());
        }
        response.sendRedirect("/loginform");
    }


    //요청이 rest 인가요?
    private boolean isRestRequest(HttpServletRequest request) {
        String requestWithHeader = request.getHeader("X-Requested-With");
        String uri = request.getRequestURI();

        boolean isApi = uri.startsWith("/api/") || uri.equals("/error");
        return "XMLHttpRequest".equals(requestWithHeader) || isApi;
    }
}
