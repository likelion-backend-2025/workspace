import { useState, useRef } from "react";

const BoardWrite = ({ handleSave }) => {
  const [title, setTitle] = useState("");
  const [writer, setWriter] = useState("");

  // 제목 입력란으로 포커스를 이동하기 위한 Ref
  const titleRef = useRef();

  const changeTitle = (e) => setTitle(e.target.value);
  const changeWriter = (e) => setWriter(e.target.value);

  const onSave = () => {
    // 유효성 검사 (수업 팁: 공백일 때 저장을 막는 로직)
    if (!title || !writer) {
      alert("제목과 작성자를 모두 입력해주세요.");
      return;
    }

    // 부모(App)에게 입력된 데이터를 전달
    handleSave({
      title: title,
      writer: writer,
    });

    // 입력창 초기화 및 포커스 이동
    setTitle("");
    setWriter("");
    titleRef.current.focus();
  };

  return (
    <div>
      <input
        ref={titleRef}
        type="text"
        name="title"
        value={title}
        onChange={changeTitle}
        placeholder="제목"
      />
      <input
        type="text"
        name="writer"
        value={writer}
        onChange={changeWriter}
        placeholder="작성자"
      />
      <button onClick={onSave}>저장</button>
    </div>
  );
};

export default BoardWrite;
