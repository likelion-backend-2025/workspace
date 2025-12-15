import { fetchTodos } from "@/lib/api";
import AddTodoForm from "./components/AddTodoForm";
import TodoList from "./components/TodoList";
import styles from "./todos.module.css";

export default async function TodosPage() {
    const todos = await fetchTodos();

    console.log(todos.length,'개 데이터 조회');

    return(
        <div className={styles.container}>
        <h1 className={styles.title}>Todos Page</h1>
        <AddTodoForm />
        <TodoList todos={todos} />
        </div>
    );
}