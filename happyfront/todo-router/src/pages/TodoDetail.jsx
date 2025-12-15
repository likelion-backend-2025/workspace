// src/pages/TodoDetail.jsx
import { useParams, useNavigate, Link } from "react-router-dom";

function TodoDetail({ todos, onDelete }) {
  // URL에서 id 추출
  const { id } = useParams();

  // 페이지 이동 함수
  const navigate = useNavigate();

  // todos에서 해당 항목 찾기
  // 주의: id는 문자열이므로 parseInt로 변환
  const todo = todos.find((t) => t.id === parseInt(id));

  // 항목이 없는 경우 (잘못된 URL)
  if (!todo) {
    return (
      <div className="todo-detail">
        <h2>할 일을 찾을 수 없습니다</h2>
        <p>ID {id}에 해당하는 할 일이 없습니다.</p>
        <Link to="/">목록으로 돌아가기</Link>
      </div>
    );
  }

  // 삭제 핸들러
  const handleDelete = () => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      onDelete(todo.id);
      navigate("/"); // 삭제 후 목록으로 이동
    }
  };

  return (
    <div className="todo-detail">
      <h2>할 일 상세</h2>

      <div className="detail-content">
        <h3>{todo.text}</h3>
        <p>상태: {todo.completed ? "✓ 완료" : "○ 미완료"}</p>
        <p>ID: {todo.id}</p>
      </div>

      <div className="actions">
        <button onClick={handleDelete} className="delete-btn">
          삭제하기
        </button>
        <Link to="/">목록으로 돌아가기</Link>
      </div>
    </div>
  );
}

export default TodoDetail;
