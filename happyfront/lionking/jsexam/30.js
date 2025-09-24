// 단축평가
// 논리연산자 AND  - 특징  둘다참일때만 참.  (첫번째 조건이 거짓이라면 두번째조건은 실행 X )
//   OR  --  특징   첫번째 조건이 참이면 두번째 조건은 실행 안해요.

console.log(true && "hi");
console.log(false && "hi");

console.log(true || "hi");
console.log(false || "hi");

const food = { foodName: "햄버거" };

function getFoodName(food) {
  if (!food) return "아무거나";
  return food.foodName;
}

function getFoodName(food) {
  return food && food.foodName;
}

const foodname = getFoodName(food);
console.log(foodname);

//자바스크립트가 false라고 판단하는 값들.
console.log("" || "hi");
console.log(0 || "hi");
console.log(null || "hi");
console.log(undefined || "hi");

console.log(1 || "hi");

let a;
let b = null;
let c = undefined;
let d = 6;
let e = "test";

let result = a || b || c || d || e;

let username = "";

username = username || "carami";

console.log(username);

//??  거짓을 판단하는 기준이 값으로만 판단해요.
console.log("=============??==================");
console.log("" ?? "hi");
console.log(0 ?? "hi");
console.log(null ?? "hi");
console.log(undefined ?? "hi");

console.log(1 ?? "hi");
