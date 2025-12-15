import { fetchTodos } from "@/lib/api";

export default async function TodosPage() {
    const todos = await fetchTodos();

    console.log(todos.length,'개 데이터 조회');

    return(
        <div>
        <h1>Todos Page</h1>
        <ul>
            {todos.map((todo) => (
                <li key={todo.id}>{todo.text}</li>
            ))}
        </ul>
        </div>
    );
}