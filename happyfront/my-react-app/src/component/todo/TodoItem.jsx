const TodoItem = ({ todo, onDelete }) => {
  return (
    <li key={todo.id}>
      <input type="checkbox" checked={todo.completed} />
      <span
        style={{
          textDecoration: todo.completed ? "line-through" : "none",
        }}
      >
        {todo.text}
      </span>
      <button onClick={() => onDelete(todo.id)}>삭제</button>
    </li>
  );
};

export default TodoItem;
