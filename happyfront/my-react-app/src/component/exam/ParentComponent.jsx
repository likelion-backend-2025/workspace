import { useState } from "react";

function ChildDisplay({ message }) {
  return (
    <div style={{ border: "1px solid blue", padding: "10px" }}>
      <h3>자식 컴포넌트</h3>
      <p>부모로부터 받은 메시지: {message}</p>
    </div>
  );
}

function ParentComponent() {
  const [message, setMessage] = useState("안녕하세요!");

  return (
    <div style={{ border: "2px solid green", padding: "10px" }}>
      <h2 style={{ color: "greenyellow" }}>부모 컴포넌트</h2>
      <input
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
      />
      <ChildDisplay message={message} />
    </div>
  );
}

export default ParentComponent;
