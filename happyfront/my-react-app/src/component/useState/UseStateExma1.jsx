import { useState } from "react";

const UseStateExam1 = () => {
  console.log("👌 UseStateExam1 실행!!");
  //   let count = 0;
  const [count, setCount] = useState(0);
  console.log(count);
  const handleClick = () => {
    // count += 1; //이 코드 실행될까요?
    setCount(count + 1);
    console.log(count);
  };

  return (
    <div>
      <p>카운트 : {count}</p>
      <button onClick={handleClick}>up</button>
      {/* <button onClick={console.log("hi")}>up</button> */}
    </div>
  );
};

export default UseStateExam1;
