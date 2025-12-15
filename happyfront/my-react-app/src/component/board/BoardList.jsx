import BoardItem from "./BoardItem";

// BoardList.js (새로 생성)
const BoardList = ({ list, onRemove, onEdit }) => {
  return (
    <table border="1">
      <caption>게시판 목록</caption>
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
          <th>조회수</th>
          <th>수정/삭제</th>
        </tr>
      </thead>
      <tbody>
        {list.map((board) => (
          <BoardItem
            key={board.id}
            {...board} // Spread Operator 사용 예시
            onRemove={onRemove} // Board에게 함수 전달
            onEdit={onEdit}
          />
        ))}
      </tbody>
    </table>
  );
};

export default BoardList;
