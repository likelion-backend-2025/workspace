import TodoItem from "./TodoItem";

const TodoList = ({ todos, onDelete }) => {
  return (
    <div>
      <h1>할 일 목록</h1>
      <ul style={{ listStyle: "none" }}>
        {todos.map((todo) => (
          <TodoItem key={todo.id} todo={todo} onDelete={onDelete} />
        ))}
      </ul>
    </div>
  );
};

export default TodoList;
