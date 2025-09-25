async function fetchUsers() {
  try {
    // 데이터 가져오기
    const response = await fetch("https://jsonplaceholder.typicode.com/users");

    // 응답 확인
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    // JSON 파싱
    const users = await response.json();

    // 이름과 이메일만 출력
    console.log("=== 사용자 목록 ===");
    users.forEach((user) => {
      console.log(`이름: ${user.name}, 이메일: ${user.email}`);
    });

    return users;
  } catch (error) {
    console.error("데이터를 가져오는 중 오류 발생:", error);
    return [];
  }
}

// 함수 실행
fetchUsers();
