let age = 25;
let price = 19.99;
let temperature = -5;
console.log(typeof age);

let myname = "hi";
console.log(typeof myname);

let name = "자바스크립트";
let message = "안녕하세요";

// 템플릿 리터럴 (ES6)
let greeting = `Hello, ${name}!`;
console.log(greeting); // Hello, 자바스크립트!

// 여러 줄 문자열
let multiline = `첫 번째 줄
두 번째 줄
세 번째 줄`;

// 표현식 삽입
let a = 10;
let b = 20;
console.log(`${a} + ${b} = ${a + b}`); // 10 + 20 = 30

// 문자열 메서드
let str = "  Hello World  ";
console.log(str.trim()); // 'Hello World'
console.log(str.toUpperCase()); // '  HELLO WORLD  '
console.log(str.toLowerCase()); // '  hello world  '
console.log(str.includes("World")); // true
console.log(str.indexOf("o")); // 6
console.log(str.replace("World", "JS")); // '  Hello JS  '
console.log(str.split(" ")); // ['', '', 'Hello', 'World', '', '']

let isActive = true;
let isCompleted = false;

let notDefined = null;
console.log(notDefined);

//초기값 (타입별 초기값)
let num = NaN;
let text = "";
let flag = false;
let obj = {};
let arr = [];

//null -- 진짜없다.
//undefined -- 아직없다.
//"",[],NaN, 0, false -- 비어있다.
