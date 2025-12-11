import { useState } from "react";
import Timer from "./Timer";

const UseEffectToggle = () => {
  const [showTimer, setShowTimer] = useState(false);
  return (
    <div>
      <button onClick={() => setShowTimer(!showTimer)}>
        {showTimer ? "타이머 숨기기 " : "타이머 보기"}
      </button>
      <hr />
      {/* showTimer 가 true 일때만 화면에 보여짐!! */}
      {showTimer && <Timer />}
    </div>
  );
};

export default UseEffectToggle;
