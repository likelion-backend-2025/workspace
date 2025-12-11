import { useState } from 'react';

function Input({ onAdd }) {
  const [text, setText] = useState('');

  const handleSubmit = () => {
    // 빈 입력 무시
    if (text.trim() === '') {
      return;
    }
    onAdd(text.trim());
    setText('');
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      handleSubmit();
    }
  };

  return (
    <div className="input-container">
      <input
        type="text"
        className="todo-input"
        placeholder="할 일을 입력하세요"
        value={text}
        onChange={(e) => setText(e.target.value)}
        onKeyDown={handleKeyPress}
      />
      <button className="add-button" onClick={handleSubmit}>
        추가
      </button>
    </div>
  );
}

export default Input;

