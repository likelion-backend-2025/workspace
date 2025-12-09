import Profile from "./Profile";

function Main({ name, hobbies }) {
  // api 서버나, DB 등에서 값을 받아오는 코드
  //   const name = "carami";
  //   const hobbies = ["축구", "코딩", "수영"];

  return (
    <div>
      <p>여기는 main 👍.</p>
      <Profile name={name} hobbies={hobbies} />
      <Profile name={"kang"} hobbies={hobbies} />
      <Profile name={"kim"} hobbies={hobbies} />
    </div>
  );
}

export default Main;
