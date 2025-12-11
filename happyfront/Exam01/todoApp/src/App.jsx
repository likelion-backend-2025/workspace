import { useState, useEffect } from 'react'

// 스타일 정의
const styles = {
  container: {
    maxWidth: '500px',
    margin: '40px auto',
    padding: '20px',
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
    backgroundColor: '#f5f5f5',
    borderRadius: '10px',
    boxShadow: '0 2px 10px rgba(0,0,0,0.1)'
  },
  title: {
    textAlign: 'center',
    color: '#333',
    marginBottom: '20px'
  },
  inputContainer: {
    display: 'flex',
    gap: '10px',
    marginBottom: '20px'
  },
  input: {
    flex: 1,
    padding: '10px 15px',
    fontSize: '16px',
    border: '1px solid #ddd',
    borderRadius: '5px',
    outline: 'none'
  },
  addButton: {
    padding: '10px 20px',
    fontSize: '16px',
    backgroundColor: '#4CAF50',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer'
  },
  todoList: {
    listStyle: 'none',
    padding: 0,
    margin: 0
  },
  todoItem: {
    display: 'flex',
    alignItems: 'center',
    padding: '12px 15px',
    backgroundColor: 'white',
    marginBottom: '8px',
    borderRadius: '5px',
    boxShadow: '0 1px 3px rgba(0,0,0,0.1)'
  },
  checkbox: {
    width: '18px',
    height: '18px',
    marginRight: '12px'
  },
  todoText: {
    flex: 1,
    fontSize: '16px',
    color: '#333'
  },
  completedText: {
    textDecoration: 'line-through',
    color: '#999'
  },
  deleteButton: {
    padding: '6px 12px',
    fontSize: '14px',
    backgroundColor: '#f44336',
    color: 'white',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer'
  },
  emptyMessage: {
    textAlign: 'center',
    color: '#999',
    padding: '20px'
  }
}

// Input 컴포넌트: 새 할 일 입력
function Input({ onAdd }) {
  const [text, setText] = useState('')

  const handleSubmit = () => {
    if (text.trim() === '') return
    onAdd(text.trim())
    setText('')
  }

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      handleSubmit()
    }
  }

  return (
    <div style={styles.inputContainer}>
      <input
        type="text"
        style={styles.input}
        value={text}
        onChange={(e) => setText(e.target.value)}
        onKeyPress={handleKeyPress}
        placeholder="할 일을 입력하세요"
      />
      <button style={styles.addButton} onClick={handleSubmit}>
        추가
      </button>
    </div>
  )
}

// TodoItem 컴포넌트: 개별 할 일 표시
function TodoItem({ todo, onDelete, onToggle }) {
  const handleDelete = () => {
    if (window.confirm(`"${todo.text}"을(를) 삭제하시겠습니까?`)) {
      onDelete(todo.id)
    }
  }

  const handleToggle = (e) => {
    console.log('[TodoItem] checkbox change', {
      id: todo.id,
      text: todo.text,
      checked: e.target.checked
    })
    onToggle(todo.id)
  }

  return (
    <li style={styles.todoItem}>
      <input
        type="checkbox"
        style={styles.checkbox}
        checked={todo.completed}
        onChange={handleToggle}
      />
      <span style={{
        ...styles.todoText,
        ...(todo.completed ? styles.completedText : {})
      }}>
        {todo.text}
      </span>
      <button style={styles.deleteButton} onClick={handleDelete}>
        삭제
      </button>
    </li>
  )
}

// TodoList 컴포넌트: 할 일 목록 표시
function TodoList({ todos, onDelete, onToggle }) {
  if (todos.length === 0) {
    return <p style={styles.emptyMessage}>할 일이 없습니다.</p>
  }

  return (
    <ul style={styles.todoList}>
      {todos.map((todo) => (
        <TodoItem key={todo.id} todo={todo} onDelete={onDelete} onToggle={onToggle} />
      ))}
    </ul>
  )
}

// App 컴포넌트: 전체 상태 관리
function App() {
  const [todos, setTodos] = useState([])

  // 할 일 추가
  const handleAdd = (text) => {
    const newTodo = {
      id: Date.now(),
      text: text,
      completed: false
    }
    setTodos([...todos, newTodo])
  }

  // 할 일 삭제
  const handleDelete = (id) => {
    setTodos(todos.filter((todo) => todo.id !== id))
  }

  // 할 일 완료 토글
  const handleToggle = (id) => {
    console.log('[App] handleToggle 호출', { id, todosBefore: todos })
    setTodos(todos.map((todo) =>
      todo.id === id ? { ...todo, completed: !todo.completed } : todo
    ))
  }

  useEffect(() => {
    console.log('[App] todos 변경됨', todos)
  }, [todos])

  return (
    <div style={styles.container}>
      <h1 style={styles.title}>📝 Todo App</h1>
      <Input onAdd={handleAdd} />
      <TodoList todos={todos} onDelete={handleDelete} onToggle={handleToggle} />
    </div>
  )
}

export default App
