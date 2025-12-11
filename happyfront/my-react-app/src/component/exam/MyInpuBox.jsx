import { useRef, useState } from "react";

const MyInputBox = () => {
  const [text, setText] = useState("기본값");
  const inputRef = useRef(null);

  const changeHandler = (e) => {
    setText(e.target.value);
  };

  const resetHandler = () => {
    // console.log(inputRef);
    setText("");
    inputRef.current.focus();
  };
  return (
    <div>
      <p>현재 값 : {text} </p>
      <label htmlFor="user-input"> 입력 : </label>
      <input
        id="user-input"
        type="text"
        ref={inputRef}
        value={text}
        onChange={changeHandler}
      />

      <button onClick={resetHandler}>RESET</button>
    </div>
  );
};

export default MyInputBox;
