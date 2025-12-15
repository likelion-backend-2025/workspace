"use client";
import { createTodo } from "@/lib/api";
import { useRouter } from "next/navigation";
import { useState } from "react";
import styles from "../todos.module.css";

function AddTodoForm() {
    const [text, setText] = useState("");
    const router = useRouter();

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (text.trim()) {
            await createTodo({text,completed:false});
            setText("");
        }else{
            return;
        }
        router.refresh(); //페이지 새로고침  핵심!!!  
    };
    return(
        <form className={styles.form} onSubmit={handleSubmit}>
            <input 
            type="text" 
            className={styles.input}
            value={text}
            onChange={(e) => setText(e.target.value)}
            placeholder="할 일을 입력하세요" />
            <button type="submit" className={styles.addButton} disabled={!text.trim()}>추가</button>
        </form>
    );
}

export default AddTodoForm;