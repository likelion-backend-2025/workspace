import { useState, useEffect } from 'react';
import Input from './Input';
import TodoList from './TodoList';
import { getAllTodos, createTodo, toggleTodo, deleteTodo } from '../services/todoApi';

function TodoComponent() {
  const [todos, setTodos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 컴포넌트 마운트 시 할 일 목록 로드
  useEffect(() => {
    loadTodos();
  }, []);

  // 할 일 목록 로드
  const loadTodos = async () => {
    try {
      setLoading(true);
      setError(null);
      const data = await getAllTodos();
      setTodos(data);
    } catch (err) {
      let errorMessage = '할 일 목록을 불러오는데 실패했습니다.';
      
      if (err.response) {
        // 서버가 응답했지만 에러 상태 코드
        errorMessage = `서버 오류 (${err.response.status}): ${err.response.data?.message || err.response.statusText}`;
      } else if (err.request) {
        // 요청이 전송되었지만 응답을 받지 못함 (네트워크 오류)
        errorMessage = '백엔드 서버에 연결할 수 없습니다. 서버가 실행 중인지 확인해주세요.';
      } else {
        // 요청 설정 중 오류
        errorMessage = `요청 오류: ${err.message}`;
      }
      
      setError(errorMessage);
      console.error('할 일 목록 로드 실패:', err);
    } finally {
      setLoading(false);
    }
  };

  // 할 일 추가
  const addTodo = async (text) => {
    try {
      setError(null);
      const newTodo = await createTodo(text, false);
      setTodos([...todos, newTodo]);
    } catch (err) {
      setError('할 일 추가에 실패했습니다.');
      console.error('할 일 추가 실패:', err);
    }
  };

  // 할 일 완료 상태 토글
  const handleToggleTodo = async (id) => {
    try {
      setError(null);
      const updatedTodo = await toggleTodo(id);
      setTodos(
        todos.map((todo) =>
          todo.id === id ? updatedTodo : todo
        )
      );
    } catch (err) {
      setError('할 일 상태 변경에 실패했습니다.');
      console.error('할 일 토글 실패:', err);
      // 에러 발생 시 목록 다시 로드
      loadTodos();
    }
  };

  // 할 일 삭제
  const handleDeleteTodo = async (id) => {
    try {
      setError(null);
      await deleteTodo(id);
      setTodos(todos.filter((todo) => todo.id !== id));
    } catch (err) {
      setError('할 일 삭제에 실패했습니다.');
      console.error('할 일 삭제 실패:', err);
      // 에러 발생 시 목록 다시 로드
      loadTodos();
    }
  };

  return (
    <div className="todo-container">
      <h1 className="todo-title">📝 할 일 목록</h1>
      
      {/* 에러 메시지 표시 */}
      {error && (
        <div className="error-message" style={{ 
          color: 'red', 
          padding: '10px', 
          marginBottom: '10px',
          backgroundColor: '#ffe6e6',
          borderRadius: '4px'
        }}>
          {error}
          <button 
            onClick={() => setError(null)}
            style={{ 
              marginLeft: '10px', 
              cursor: 'pointer',
              background: 'none',
              border: 'none',
              color: 'red',
              textDecoration: 'underline'
            }}
          >
            닫기
          </button>
        </div>
      )}

      <Input onAdd={addTodo} />
      
      {/* 로딩 중 표시 */}
      {loading ? (
        <p className="loading-message" style={{ textAlign: 'center', padding: '20px' }}>
          로딩 중...
        </p>
      ) : (
        <TodoList todos={todos} onToggle={handleToggleTodo} onDelete={handleDeleteTodo} />
      )}
    </div>
  );
}

export default TodoComponent;

