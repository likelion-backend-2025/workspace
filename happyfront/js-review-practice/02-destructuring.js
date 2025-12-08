const user = {
  name: "김철수",
  age: 25,
  email: "kim@example.com",
};

// // 기존 방식
// const name = user.name;
// const age = user.age;
// const email = user.email;

// 구조 분해 할당
const { age, address } = user;

// console.log(name); // 김철수
console.log(age); // 25
// console.log(email); // kim@example.com

console.log("\n=== 기본값과 변수명 변경 ===");

const apiResponse = {
  userId: 1,
  username: "kim_minji",
  email: "minji@example.com",
  profileImage: "test.jpg",
  // profileImage가 없음!
};

// 기본값 설정 + 변수명 변경
const {
  userId: id, // userId를 id로 변경
  username: userName, // username을 userName으로 변경
  email, // 그대로 사용
  profileImage = "/images/default-avatar.png", // 없으면 기본값
} = apiResponse;

console.log("ID:", id);
console.log("이름:", userName);
console.log("이메일:", email);
console.log("프로필:", profileImage);

console.log("\n=== 중첩 객체 구조 분해 ===");

const user2 = {
  id: 1,
  profile: {
    firstName: "철수",
    lastName: "김",
    address: {
      city: "서울",
      district: "강남구",
    },
  },
  settings: {
    theme: "dark",
    notifications: true,
  },
};

// 중첩 객체 한 번에 구조 분해
const {
  profile: {
    firstName,
    lastName,
    address: { city, district },
  },
  settings: { theme },
} = user2;

console.log(`${lastName}${firstName}님은 ${city} ${district}에 삽니다.`);
console.log(`테마: ${theme}`);

// 배열 구조분해

function useState(initialValue) {
  let state = initialValue;

  const setState = (newValue) => {
    state = newValue;
    console.log(`state 가 ${newValue}로 변경됨!!`);
  };

  return [state, setState];
}

const [a, b] = useState(0);
console.log("값", a);
b(1);

const [count, setCount] = useState(10);
console.log(count);
setCount(11);

const [name, setName] = useState("no name");
console.log("이름::", name);
setName("carami");

const [price, setPrice] = useState(2000);
console.log("가격 :: ", price);
setPrice(1000);

//함수 매개변수에서 구조분해.
function printUserInfo(user) {
  console.log(`이름 : ${user.naem}`);
}
function printUserInfo({ name, age, email = "없음" }) {
  console.log(`이름 : ${name}`);
  console.log(`나이 : ${age}`);
  console.log(`이메일: ${email}`);
}

printUserInfo({ name: "carami", age: 20 });
printUserInfo({ name: "kim", age: 19, email: "kim@exam.com" });

const movieData = {
  title: "인터스텔라",
  year: 2014,
  director: {
    name: "크리스토퍼 놀란",
    nationality: "영국",
  },
  ratings: {
    imdb: 8.6,
    rottenTomatoes: 72,
  },
  genres: ["SF", "드라마", "모험"],
};

//알맞게 구조분해 해주세요^^
const {
  title,
  director: { name: directorName },
  ratings: { imdb },
} = movieData;

console.log(`영화: ${title}`);
console.log(`감독: ${directorName}`);
console.log(`IMDB 점수: ${imdb}`);
