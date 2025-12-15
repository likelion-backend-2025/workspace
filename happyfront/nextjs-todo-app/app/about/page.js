"use client";
import { useState } from "react";

function AboutPage() {
    const [count, setCount] = useState(0);
    return(
        <h1>About Page{count}</h1>
    );
}

export default AboutPage;