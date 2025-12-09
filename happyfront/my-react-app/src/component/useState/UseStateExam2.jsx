import { useState } from "react";

const UseStateExam2 = () => {
  const [input, setInput] = useState("");
  const [names, setNames] = useState(["kang", "kim", "hong"]);

  const inputChangeHandler = (e) => {
    setInput(e.target.value);
  };

  const addHandler = () => {
    setNames((prevState) => {
      return [input, ...prevState];
    });
    setInput("");
  };

  return (
    <div>
      <input type="text" value={input} onChange={inputChangeHandler} />
      <button onClick={addHandler}> 입력 </button>

      {/* names 를 렌더링 하고 싶어요.   */}
      {names.map((name, index) => {
        return <p key={name + index}>{name}</p>;
      })}
    </div>
  );
};

export default UseStateExam2;
