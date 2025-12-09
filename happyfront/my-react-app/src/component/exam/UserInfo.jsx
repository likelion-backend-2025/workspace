function UserInfo() {
  console.log("👌 UserInfo 실행!!");
  const userName = "kimg";
  const age = 25;
  return (
    <div>
      <h2>사용자 정보</h2>
      <p>이름: {userName}</p>
      <p>나이: {age}세</p>
      <p>성인 여부: {age >= 20 ? "성인" : "미성년자"}</p>
    </div>
  );
}

export default UserInfo;
