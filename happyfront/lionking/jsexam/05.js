// 함수 호이스팅
hello();
function hello() {
  console.log("hello!!");
}

//함수 중복정의 가능
function hello() {
  console.log("carami hello!!");
}

// const  --  상수  dom  <div></div>

// Person kang = new Person();
// kang.setName("강경미");
// kang.setAge(20);

// 값이 추가되거나, 수정되었다고 해서 kang이라는 Person 객체 자체가 바뀐걸까요???

// add(1, 2);  함수 호이스팅 되지 않는다.

const add = function (a, b) {
  console.log("hi");

  return a + b;
};

let result = add(10, 20);
console.log(result);

// 화살표 함수

function hi(a) {
  console.log("hi " + a);
}

hi("carami");

const hi2 = (a) => console.log("hi " + a);

hi2("carami");

const add2 = (a, b) => {
  console.log("hi");
  return a + b;
};

function add3(a, b) {
  return a + b;
}

const add4 = (a, b) => a + b;

console.log(add4(10, 20));

//화살표 함수안에서의 this  항상 전역을 의미함. (우리가 정의한 객체 안에서 사용할 때.. )
