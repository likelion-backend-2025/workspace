import { useState } from "react";

function LoginStatus({ hasNewMessage, messageCount }) {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  //   if (isLoggedIn) {
  //     return (
  //       <div>
  //         <h2>환영합니다!</h2>
  //         <button onClick={() => setIsLoggedIn(false)}>로그아웃</button>
  //       </div>
  //     );
  //   }

  //   return (
  //     <div>
  //       <h2>로그인이 필요합니다</h2>
  //       <button onClick={() => setIsLoggedIn(true)}>로그인</button>
  //     </div>
  //   );
  return (
    <div>
      <h2>{isLoggedIn ? "환영합니다!" : "로그인이 필요합니다"}</h2>
      <button onClick={() => setIsLoggedIn(!isLoggedIn)}>
        {isLoggedIn ? "로그아웃" : "로그인"}
      </button>

      {hasNewMessage && (
        <p style={{ color: "red" }}>
          새로운 메시지가 {messageCount}개 있습니다!
        </p>
      )}
    </div>
  );
}

export default LoginStatus;
