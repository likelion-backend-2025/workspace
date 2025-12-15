import DeleteButton from "./DeleteButton";
import ToggleButton from "./ToggleButton";
import styles from "../todos.module.css";

function TodoItem({ todo }) {
    return(
        <li className={styles.item}>
            <ToggleButton id={todo.id} completed={todo.completed} />
            <span className={todo.completed ? styles.completed : ""}>{todo.text}</span>
        <DeleteButton id={todo.id} />
        </li>
    );

}

export default TodoItem;