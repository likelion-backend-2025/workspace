const TodoItem = ({ todo }) => {
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
    </li>
  );
};

export default TodoItem;
