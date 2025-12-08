// alert("hello");

console.log("hello");

//function 으로 정의 된것은 호이스팅 가능!!

//자바스크립트는 인터프리터언어!!

let msg = sayHello("carami");
console.log(msg);

function sayHello(name) {
  return `안녕하세요, ${name}님!!`;
}

//호이스팅 안됨!!
// console.log(greet("hoho"));
const greet = function (name) {
  return `안녕하세요, ${name}님!!`;
};

console.log(greet("hoho"));

//화살표함수

const greet2 = (name) => {
  return `안녕하세요, ${name}님!!`;
};

console.log(greet2("arrow function"));

const greet3 = (name) => `안녕하세요, ${name}님!!`;

function calculateArea(width, height) {
  return width + height;
}

const calculateArea2 = (width, height) => width * height;

console.log(calculateArea2(3, 5));

const createUser = (name) => ({ name: name });

console.log(createUser("kang"));

const createUser2 = (name) => ({ name });

console.log(createUser2("kang"));

const createTodo = (text) => ({
  id: Date.now(),
  //   text: text,
  text, //단축속성명
  done: false,
});

const todo1 = createTodo("리액트 공부하기");
const todo2 = createTodo("자바스크립트 복습하기");

console.log("할일 1: ", todo1);
console.log("할일 2: ", todo2);

//위에 정의한 create
// let color = red;
// {color:color}    ==    {color}

//this  기억나시나요??

const user = {
  name: "김철수",

  // 일반 함수: this = 호출한 객체 (user)
  greetNormal: function () {
    console.log(`안녕하세요, ${this.name}입니다.`);
  },

  // 화살표 함수: this = 상위 스코프의 this
  greetArrow: () => {
    console.log(`안녕하세요, ${this.name}입니다.`);
  },

  delayedGreetNormal: function () {
    setTimeout(function () {
      console.log(`setTimeout function() ${this.name}님 hello~`);
    }, 100);
  },
  delayedGreetArrow: function () {
    setTimeout(() => {
      console.log(`setTimeout function() ${this.name}님 hello~`);
    }, 100);
  },
};

user.greetNormal(); // 안녕하세요, 김철수입니다.
user.greetArrow(); // 안녕하세요, undefined입니다. (전역 this 참조)

user.delayedGreetNormal();
user.delayedGreetArrow();
