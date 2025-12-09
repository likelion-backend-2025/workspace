import { useState } from "react";

function TextInput() {
  const [text, setText] = useState("");

  const handleChange = (e) => {
    setText(e.target.value);
  };

  return (
    <div>
      <input
        type="text"
        value={text}
        onChange={handleChange}
        placeholder="텍스트를 입력하세요"
      />
      <p>입력한 내용: {text}</p>
    </div>
  );
}

export default TextInput;
