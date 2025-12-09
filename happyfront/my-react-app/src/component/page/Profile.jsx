function Profile({ name, hobbies }) {
  //   const name = "강멋사";
  //   const hobbies = ["코딩", "스키", "운동", "독서"];

  return (
    <div>
      <h2>{name}님 프로필</h2>
      <p>취미 : {hobbies.join(",")}</p>
    </div>
  );
}

export default Profile;
