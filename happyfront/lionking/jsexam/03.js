// 기본 산술
let a = 10 + 5; // 15
let b = 10 - 5; // 5
let c = 10 * 5; // 50
let d = 10 / 5; // 2
let e = 10 % 3; // 1 (나머지)

// 증감 연산자
let count = 0;
count++; // 1
++count; // 2
count--; // 1
--count; // 0

// 일치 비교 (타입까지 체크)
console.log(1 === 1); // true
console.log(1 === "1"); // false

// 동등 비교 (타입 변환 후 비교) - 사용 비권장
console.log(1 == "1"); // true

// 불일치
console.log(1 !== "1"); // true

// 크기 비교
console.log(10 > 5); // true
console.log(10 >= 10); // true

// AND (&&)
console.log(true && true); // true
console.log(true && false); // false

// OR (||)
console.log(true || false); // true
console.log(false || false); // false

// NOT (!)
console.log(!true); // false
console.log(!false); // true

// 조건 ? 참일때 : 거짓일때
const age = 20;
const status = age >= 18 ? "성인" : "미성년자";
console.log(status); // 성인

// 중첩 삼항 연산자 (가독성 주의)
const score = 85;
const grade = score >= 90 ? "A" : score >= 80 ? "B" : score >= 70 ? "C" : "F";
console.log(grade); // B

// const age = 18;

if (age >= 18) {
  console.log("성인입니다.");
} else if (age >= 13) {
  console.log("청소년입니다.");
} else {
  console.log("어린이입니다.");
}

const fruit = "apple";

switch (fruit) {
  case "apple":
    console.log("사과입니다.");
    break;
  case "banana":
    console.log("바나나입니다.");
    break;
  default:
    console.log("기타 과일입니다.");
}
