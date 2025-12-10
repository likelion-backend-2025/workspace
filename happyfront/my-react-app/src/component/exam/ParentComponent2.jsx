import { useState } from "react";

function ChildButton({ onButtonClick, btnText }) {
  return (
    <button onClick={() => onButtonClick("자식 버튼 클릭됨!")}>
      {btnText}
    </button>
  );
}

function ParentComponent2() {
  const [log, setLog] = useState("");

  const handleChildClick = (message) => {
    setLog(message);
  };
  const handleChildClick2 = (message) => {
    setLog("두번째 버튼!!" + message);
  };

  return (
    <div>
      <p>자식으로부터 받은 메시지: {log}</p>
      <ChildButton btnText={"더하기"} onButtonClick={handleChildClick} />
      <ChildButton btnText={"빼기"} onButtonClick={handleChildClick2} />
    </div>
  );
}

export default ParentComponent2;
