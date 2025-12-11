import { useRef, useState } from "react";

const UseRefExam = () => {
  //useState vs useRef vs let
  console.log("👌 렌더링 중!!  ");

  let countLet = 0;
  console.log("countLet 초기화:::", countLet);
  const [count, setCount] = useState(0);
  const countRef = useRef(0);

  const increaseState = () => {
    setCount(count + 1);
    console.log("useState :::::  " + (count + 1));
  };
  const increaseLet = () => {
    countLet++;
    console.log("let :::::  " + countLet);
  };
  const increaseRef = () => {
    countRef.current++;
    console.log("useRef :::::  " + countRef.current);
  };

  return (
    <div>
      <p>useState :: {count}</p>
      <p>Lef :: {countLet}</p>
      <p>useRef :: {countRef.current}</p>

      <button onClick={increaseState}>useState update</button>
      <button onClick={increaseLet}>let update</button>
      <button onClick={increaseRef}>useRef update</button>
    </div>
  );
};

export default UseRefExam;
