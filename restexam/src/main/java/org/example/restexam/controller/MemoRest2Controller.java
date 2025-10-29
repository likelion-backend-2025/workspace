package org.example.restexam.controller;

import org.example.restexam.domain.Memo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MemoRest2Controller {
    private final Map<Long, Memo> memos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    //http://localhost:8080/api2/memos    -- GET   (모든 메모를 조회)
    //http://localhost:8080/api2/memos/{id}  -- GET  id에 해당하는 메모 조회
    //http://localhost:8080/api2/memos     -- POST    MEMO 생성
    //http://localhost:8080/api2/memos/{id}  --  PUT   메모 수정
    //http://localhost:8080/api2/memos/{id}  --   DELETE  메모 삭제

//    위에 제시한 요청이 들어왔을 때 이것을 모두 처리할 수 있는 RestController 를 만들어 주세요.
}
