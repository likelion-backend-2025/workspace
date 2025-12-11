import { useEffect } from "react";

const Timer = () => {
  console.log("👌 Timer 실행 (마운트) ");
  useEffect(() => {
    console.log("👌 useEffect[] 실행 ");
    //1. 마운트 될 때 타이머 시작!!
    const timer = setInterval(() => {
      console.log("째깍째깍 .. (타이머실행중!!)");
    }, 1000);
    //2. 컴포넌트가 언마운트 될때 실행!!! (Clean-up)
    return () => {
      clearInterval(timer);
      console.log("타이머종료!! (clean-up 실행됨)");
    };
  }, []);
  return (
    <div>
      <h3>타이머 작동중 ⏱️⏱️</h3>
    </div>
  );
};

export default Timer;
