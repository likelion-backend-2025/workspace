const Layout = ({ children }) => {
  return (
    <div>
      {/* 1. 고정된 헤더 */}
      <header style={{ background: "#333", color: "#fff", padding: "15px" }}>
        <nav>My Website Logo | Home | About | Contact</nav>
      </header>

      {/* 2. 페이지마다 바뀌는 콘텐츠 영역 (Slot) */}
      <main style={{ minHeight: "400px", padding: "20px" }}>{children}</main>

      {/* 3. 고정된 푸터 */}
      <footer
        style={{ background: "#eee", padding: "20px", textAlign: "center" }}
      >
        <p>© 2025 My Website. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default Layout;
