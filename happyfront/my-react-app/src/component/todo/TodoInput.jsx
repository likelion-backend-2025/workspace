import { useState } from "react";

const TodoInput = ({ onAdd }) => {
  const [text, setText] = useState("");

  const handleSubmit = () => {
    if (text.trim()) {
      onAdd(text);
      setText("");
    }
  };

  return (
    <div>
      <input
        type="text"
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder="할 일을 입력하세요."
      />
      <button onClick={handleSubmit}>추가</button>
    </div>
  );
};

export default TodoInput;
