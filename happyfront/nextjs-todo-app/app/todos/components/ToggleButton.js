"use client";

import { toggleTodo } from "@/lib/api";
import { useRouter } from "next/navigation";
import styles from "../todos.module.css";

function ToggleButton({ id, completed }) {
    const router = useRouter();

    const handleToggle = async () => {
        await toggleTodo(id);
        router.refresh();
    };

    return(
        <button 
            type="button"
            className={`${styles.toggleButton} ${completed ? styles.checked : ""}`}
            onClick={handleToggle}
        >
            {completed ? "✓" : ""}
        </button>
    );
}

export default ToggleButton;