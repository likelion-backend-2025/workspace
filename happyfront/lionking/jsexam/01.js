console.log("hi");

// 변수, 상수
// let, const   (권장) (es6)  var   (x)
//1. var -- 중복 선언이 가능!!
//2. var -- 호이스팅 된다.
//3. var -- 변수의 스코프가 함수단위로 사용된다.

//왜 ES6 에서 var 를 지양하고 let, const를 추가했는지 이유!!

var v1 = 10;

var v1 = 20;

var v1 = 30;

//중복 선언 되었을때 문제점 이해되실까요?

console.log(v2);

var v2 = 20;

console.log(v2);

// console.log(v3);

varScopeTest();

function varScopeTest() {
  if (true) {
    var varScope = 10;
  }
  console.log(varScope);
}

// // 자바는 강형 - 값에 따라서 타입이 달라요.
// // 자바스크립트는 약형 - 타입이 정해지지 않는다.
console.log("====================let =======================");
// console.log(value);  당연히 호이스팅되지 않는다.

let value = 1;
console.log(value);

value = "aa";
console.log(value);

// let value = 20;   당연히 중복정의 안됨.

function letScopeTest() {
  if (true) {
    let letScope = 10;
  }
  //   console.log(letScope);   let scope 가 블럭단위로 사용된다.
}

letScopeTest();

const a = 10;

// a = 20;   상수이므로 값 변경이 안됨!!!    나머지 특징은 let과 동일
