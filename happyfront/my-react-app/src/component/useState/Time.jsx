import { useState } from "react";

const Time = () => {
  const [time, setTime] = useState(1);

  const updateHandler = () => {
    // setTime(time + 1);
    // setTime(time + 1);
    // 리액트가 관리하는 최신 state 값을 받아옴.
    // setTime((prevTime) => prevTime + 1);
    // setTime((prevTime) => prevTime + 1);

    setTime((prevTime) => {
      return prevTime >= 12 ? 1 : prevTime + 1;
    });
  };
  return (
    <div>
      <span>시간 : {time} 시</span>
      <button onClick={updateHandler}>update</button>
    </div>
  );
};

export default Time;
