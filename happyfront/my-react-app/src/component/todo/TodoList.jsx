import TodoItem from "./TodoItem";

const TodoList = ({ todos }) => {
  return (
    <div>
      <h1>할 일 목록</h1>
      <ul style={{ listStyle: "none" }}>
        {todos.map((todo) => (
          <TodoItem todo={todo} />
        ))}
      </ul>
    </div>
  );
};

export default TodoList;
