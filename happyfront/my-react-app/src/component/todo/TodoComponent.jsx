import { useState } from "react";
import TodoInput from "./TodoInput";
import TodoList from "./TodoList";
function TodoComponent() {
  //   const todos = [
  //     { id: 1, text: "아침 운동하기", completed: false },
  //     { id: 2, text: "책 읽기", completed: true },
  //     { id: 3, text: "저녁 요리하기", completed: false },
  //   ];
  const [todos, setTodos] = useState([
    { id: 1, text: "아침 운동하기", completed: false },
    { id: 2, text: "책 읽기", completed: true },
    { id: 3, text: "저녁 요리하기", completed: false },
  ]);

  const addTodo = (text) => {
    setTodos([...todos, { id: Date.now(), text, completed: false }]);
  };

  const deleteTodo = (id) => {
    setTodos(todos.filter((todo) => todo.id != id));
  };
  return (
    <div>
      <TodoInput onAdd={addTodo} />

      <TodoList todos={todos} onDelete={deleteTodo} />
    </div>
  );
}

export default TodoComponent;
