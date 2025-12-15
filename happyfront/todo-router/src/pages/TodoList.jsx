// src/pages/TodoList.jsx
import { useState } from "react";
import { Link } from "react-router-dom";

function TodoList({ todos, onAdd }) {
  // 입력값 상태
  const [newTodo, setNewTodo] = useState("");

  // 폼 제출 핸들러
  const handleSubmit = (e) => {
    e.preventDefault(); // 페이지 새로고침 방지

    if (newTodo.trim()) {
      onAdd(newTodo.trim());
      setNewTodo(""); // 입력창 초기화
    }
  };

  // 완료된 할 일 개수
  const completedCount = todos.filter((todo) => todo.completed).length;

  return (
    <div className="todo-list">
      <h2>할 일 목록</h2>

      {/* 할 일 추가 폼 */}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={newTodo}
          onChange={(e) => setNewTodo(e.target.value)}
          placeholder="새 할 일을 입력하세요"
        />
        <button type="submit">추가</button>
      </form>

      {/* 할 일 목록 */}
      <ul>
        {todos.map((todo) => (
          <li key={todo.id}>
            {/* Link로 상세 페이지 이동 */}
            <Link
              to={`/todos/${todo.id}`}
              style={{
                textDecoration: todo.completed ? "line-through" : "none",
              }}
            >
              {todo.completed ? "✓ " : "○ "}
              {todo.text}
            </Link>
          </li>
        ))}
      </ul>

      {/* 통계 */}
      <p>
        전체: {todos.length}개 | 완료: {completedCount}개
      </p>
    </div>
  );
}

export default TodoList;
