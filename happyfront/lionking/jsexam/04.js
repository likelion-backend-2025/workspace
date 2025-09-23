//자바스크립트는 함수를 일급객체로 취급한다.
//함수도 타입이 될 수 있다.   -- 타입이 된다라는 것은 매개변수로도 사용가능, 리턴값으로도 사용가능..
function test() {
  console.log("hi!!");
}

console.log(typeof test);

function func2(abc) {
  return abc; //()  --  함수를 실행해주세요.   함수가 전달될때..
}

let value = 10;

let result = func2(test); //함수가 전달될때 () 쓰는것과 쓰지않는것의 차이점!!
result();
