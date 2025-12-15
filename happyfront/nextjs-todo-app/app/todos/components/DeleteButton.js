"use client";
import { deleteTodo } from "@/lib/api";
import { useRouter } from "next/navigation";
import styles from "../todos.module.css";

function DeleteButton({ id }) {
    const router = useRouter();
    const handleDelete = async () => {
        await deleteTodo(id);
        router.refresh();
    };

    return(
        <button className={styles.deleteButton} onClick={handleDelete}>삭제</button>
    );
}

export default DeleteButton;