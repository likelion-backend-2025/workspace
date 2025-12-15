import TodoItem from "./TodoItem";
import styles from "../todos.module.css";

function TodoList({ todos }) {

return(
    <>
    <ul className={styles.list}>
    {todos.map((todo) => (
        <TodoItem key={todo.id} todo={todo} />
    ))}
    </ul>
    {todos.length === 0 && (
        <div className={styles.empty}>할 일이 없습니다.</div>
    )}
    </>
);
}

export default TodoList;