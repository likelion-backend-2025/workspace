const Card = ({ children, title }) => {
  const cardStyle = {
    border: "1px solid #ddd",
    borderRadius: "8px",
    boxShadow: "0 4px 6px rgba(0,0,0,0.1)",
    padding: "20px",
    margin: "10px",
    maxWidth: "300px",
    backgroundColor: "white",
  };

  return (
    <div style={cardStyle}>
      {/* 제목이 있으면 보여주고 없으면 생략 */}
      {title && (
        <h2 style={{ margin: "0 0 10px 0", fontSize: "1.2rem" }}>{title}</h2>
      )}
      <hr
        style={{
          border: "0",
          borderTop: "1px solid #eee",
          marginBottom: "15px",
        }}
      />

      {/* 내용물 들어갈 자리 */}
      {children}
    </div>
  );
};

const Dashboard = () => {
  return (
    <div
      style={{
        display: "flex",
        gap: "20px",
        background: "#f5f5f5",
        padding: "20px",
      }}
    >
      {/* 1. 사용자 프로필 카드 */}
      <Card title="내 프로필">
        <img src="vite.svg" alt="User" style={{ borderRadius: "50%" }} />
        <p>
          <strong>Carami</strong>님 안녕하세요.
        </p>
        <button>로그아웃</button>
      </Card>

      {/* 2. 할 일 목록 카드 */}
      <Card title="오늘의 할 일">
        <ul style={{ paddingLeft: "20px" }}>
          <li>리액트 강의 준비</li>
          <li>저녁 장보기</li>
        </ul>
      </Card>
    </div>
  );
};

export default Dashboard;
