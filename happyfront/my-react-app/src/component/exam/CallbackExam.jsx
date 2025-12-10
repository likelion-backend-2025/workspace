import { useState } from "react";

export default function CallbackExam() {
  const [logs, setLogs] = useState([]);

  const doClick = (title) => {
    console.log(`${title} 클릭됨 (${new Date().toLocaleTimeString()})`);
    setLogs([...logs, `${title} 클릭됨 (${new Date().toLocaleTimeString()})`]);
  };

  return (
    <div>
      <ActionButton title={"저장"} color="red" onAction={doClick} />
      <ActionButton title={"삭제"} color={"green"} onAction={doClick} />
      <ActionButton title={"지우기"} color={"blue"} onAction={doClick} />

      <div>
        <h2>로그 : </h2>
        {logs.map((log, index) => (
          <p key={index}>{log}</p>
        ))}
      </div>
    </div>
  );
}

function ActionButton({ title, color, onAction }) {
  return (
    <button
      style={{ backgroundColor: color, color: "white" }}
      onClick={() => onAction(title)}
    >
      {title}
    </button>
  );
}
