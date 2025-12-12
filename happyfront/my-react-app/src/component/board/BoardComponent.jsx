const BoardComponent = () => {
  // 데이터가 컴포넌트 내부에 강하게 결합되어 있음
  const boardList = [
    {
      id: 3,
      title: "게시판 제목 3",
      created: "2021-10-01",
      readCount: 10,
      writer: "고길동",
    },
    {
      id: 2,
      title: "게시판 제목 2",
      created: "2021-09-22",
      readCount: 5,
      writer: "둘리",
    },
    {
      id: 1,
      title: "게시판 제목 1",
      created: "2021-07-03",
      readCount: 4,
      writer: "도우너",
    },
  ];

  return (
    <div>
      <h1>게시판</h1>
      {/* 테이블 렌더링 로직이 그대로 노출됨 */}
      <table border="1">
        <caption>게시판 목록</caption>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          {boardList.map((board) => (
            <tr key={board.id}>
              <td>{board.id}</td>
              <td>{board.title}</td>
              <td>{board.writer}</td>
              <td>{board.created}</td>
              <td>{board.readCount}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default BoardComponent;
