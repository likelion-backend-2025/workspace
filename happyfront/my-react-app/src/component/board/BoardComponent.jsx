import { useRef, useState } from "react";
import BoardWrite from "./BoardWrite";
import BoardList from "./BoardList";

// App.js (또는 Page 컴포넌트)
const BoardComponent = () => {
  // 1. 초기 데이터 설정 (보통은 빈 배열이거나 API에서 가져온 데이터)
  const [boardList, setBoardList] = useState([
    {
      id: 1,
      title: "게시판 제목 1",
      created: "2021-07-03",
      readCount: 4,
      writer: "도우너",
    },
    {
      id: 2,
      title: "게시판 제목 2",
      created: "2021-09-22",
      readCount: 5,
      writer: "둘리",
    },
    {
      id: 3,
      title: "게시판 제목 3",
      created: "2021-10-01",
      readCount: 10,
      writer: "고길동",
    },
  ]);

  // 2. 새로운 ID를 발급하기 위한 Ref (렌더링과 무관한 변수 관리)
  // 초기 데이터가 3개이므로 4부터 시작
  const nextId = useRef(4);

  // 3. 데이터 저장 함수 (자식에게 전달할 함수)
  const handleSave = (data) => {
    // 오늘 날짜 포맷팅 (YYYY-MM-DD)
    const date = new Date();
    const created = `${date.getFullYear()}-${
      date.getMonth() + 1
    }-${date.getDate()}`;

    // 새 게시글 객체 생성
    const newBoard = {
      id: nextId.current,
      title: data.title,
      writer: data.writer,
      created: created,
      readCount: 0, // 초기 조회수는 0
    };

    // [중요] 불변성 유지: 기존 배열을 복사하고 새 항목을 추가
    // push()를 쓰면 React가 변경을 감지하지 못함
    setBoardList([...boardList, newBoard]);
    // 혹은 setBoardList(boardList.concat(newBoard));

    // 다음 ID 증가
    nextId.current += 1;
  };

  // [삭제 기능]
  // id를 매개변수로 받아, 해당 id가 아닌 요소들만 남김
  const onRemove = (id) => {
    // filter 역시 원본 배열을 건드리지 않고 새 배열을 반환함 (불변성 유지)
    setBoardList(boardList.filter((board) => board.id !== id));
  };

  // [수정 기능]
  // id: 수정할 게시글의 ID
  // updatedData: 수정된 내용 (title, writer 등) 객체
  const onEdit = (id, updatedData) => {
    setBoardList(
      boardList.map((board) =>
        // id가 일치하면 기존 객체(...board)에 수정된 데이터(...updatedData)를 덮어씌움
        // 일치하지 않으면 기존 객체(board)를 그대로 유지
        board.id === id ? { ...board, ...updatedData } : board
      )
    );
  };

  return (
    <div>
      <h1>게시판</h1>
      {/* 글쓰기 컴포넌트에 저장 함수 전달 */}
      <BoardWrite handleSave={handleSave} />

      <br />

      {/* 목록 컴포넌트에 현재 상태 전달 */}
      <BoardList list={boardList} onRemove={onRemove} onEdit={onEdit} />
    </div>
  );
};

export default BoardComponent;
