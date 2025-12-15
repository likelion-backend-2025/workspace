// src/App.jsx
import { useState } from "react";
import { Routes, Route } from "react-router-dom";
import TodoList from "./pages/TodoList";
import TodoDetail from "./pages/TodoDetail";
import "./App.css";

function App() {
  const [todos, setTodos] = useState([
    { id: 1, text: "운동하기", completed: false },
    { id: 2, text: "책 읽기", completed: true },
    { id: 3, text: "청소하기", completed: false },
  ]);

  const addTodo = (text) => {
    const newTodo = {
      id: Date.now(),
      text,
      completed: false,
    };
    setTodos([...todos, newTodo]);
  };

  const deleteTodo = (id) => {
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  return (
    <div className="app">
      <h1>Todo App with Router</h1>

      <Routes>
        {/* 목록 페이지 */}
        <Route path="/" element={<TodoList todos={todos} onAdd={addTodo} />} />

        {/* 상세 페이지 */}
        <Route
          path="/todos/:id"
          element={<TodoDetail todos={todos} onDelete={deleteTodo} />}
        />
      </Routes>
    </div>
  );
}

export default App;
