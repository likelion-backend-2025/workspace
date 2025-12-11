import { useEffect, useState } from "react";

const UseEffectExam = () => {
  console.log("👌 UseEffectExam 컴포넌트 실행!!");
  const [count, setCount] = useState(0);
  const [text, setText] = useState("");

  //1. 의존성 배열 없음.  매번 렌더링 될 때  실행됨!!! (거의 안씀.)
  useEffect(() => {
    console.log("렌더링 시 매번 수행됨!!");
  });
  // 2. 의존성 배열이 [] 빈 배열일 때 :
  useEffect(() => {
    console.log("이 컴포넌트가 처음(마운트) 실행될 때 한 번 만 수행됨!!");
  }, []);
  // 3. 의존성배열 [count] : count 값이 바뀔 때 마다 실행!!
  useEffect(() => {
    console.log("count가 변경 될 때 수행됨!!");
  }, [count]);
  // 4. 의존성배열 [text] : text 값이 바뀔 때 마다 실행!!
  useEffect(() => {
    console.log("text가 변경 될 때 수행됨!!");
  }, [text]);

  return (
    <div>
      <h2>useEffect 예제</h2>

      <div>
        <span>Count : {count}</span>
        <button onClick={() => setCount(count + 1)}>Count 증가</button>
      </div>
      <div>
        <span>Text : {text}</span>
        <input
          type="text"
          value={text}
          onChange={(e) => setText(e.target.value)}
          placeholder="글자를 입력하세요."
        />
      </div>
    </div>
  );
};

export default UseEffectExam;
