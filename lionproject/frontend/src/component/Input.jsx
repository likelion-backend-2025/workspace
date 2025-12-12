import { useState } from 'react';

const MAX_LENGTH = 500; // 최대 길이 제한

function Input({ onAdd }) {
  const [text, setText] = useState('');
  const [error, setError] = useState('');

  const handleChange = (e) => {
    const value = e.target.value;
    
    // 최대 길이 검증
    if (value.length > MAX_LENGTH) {
      setError(`할 일 내용은 ${MAX_LENGTH}자 이하여야 합니다.`);
      return;
    }
    
    setText(value);
    setError(''); // 에러 초기화
  };

  const handleSubmit = () => {
    const trimmedText = text.trim();
    
    // 빈 입력 검증
    if (trimmedText === '') {
      setError('할 일 내용을 입력해주세요.');
      return;
    }
    
    // 최대 길이 재검증
    if (trimmedText.length > MAX_LENGTH) {
      setError(`할 일 내용은 ${MAX_LENGTH}자 이하여야 합니다.`);
      return;
    }
    
    // 에러 초기화 후 추가
    setError('');
    onAdd(trimmedText);
    setText('');
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      handleSubmit();
    }
  };

  return (
    <div className="input-container">
      <div style={{ width: '100%' }}>
        <div style={{ display: 'flex', gap: '10px' }}>
          <input
            type="text"
            className="todo-input"
            placeholder="할 일을 입력하세요"
            value={text}
            onChange={handleChange}
            onKeyDown={handleKeyPress}
            maxLength={MAX_LENGTH}
            style={{ flex: 1 }}
          />
          <button className="add-button" onClick={handleSubmit}>
            추가
          </button>
        </div>
        
        {/* 에러 메시지 표시 */}
        {error && (
          <div style={{ 
            color: 'red', 
            fontSize: '12px', 
            marginTop: '5px',
            padding: '5px'
          }}>
            {error}
          </div>
        )}
        
        {/* 글자 수 표시 */}
        <div style={{ 
          fontSize: '12px', 
          color: text.length > MAX_LENGTH * 0.9 ? 'orange' : '#666',
          marginTop: '5px',
          textAlign: 'right'
        }}>
          {text.length} / {MAX_LENGTH}
        </div>
      </div>
    </div>
  );
}

export default Input;

