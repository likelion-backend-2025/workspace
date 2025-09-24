// forEach(함수)  --  요소를 하나씩 꺼내서 함수를 실행해주는 일
// console.log("===================================");
const array = [1, 2, 3, 4, 5];
// function print(number) {
//   console.log(number);
// }
const print = (number) => console.log(number * 2);

array.forEach(print);

// array.forEach((n) => console.log(n));

//array 배열의 요소에 (n*n)   한 결과 배열을 새로 만들고 싶다.  [1,4,9,16..]

const resultArr = [];

for (let i = 0; i < array.length; i++) {
  resultArr.push(array[i] * array[i]);
}

console.log(resultArr);

//foreach 를 이용한다면?   --  각각 요소에다가 (뭔가(함수)) 를 해줘!!
console.log("===========forEach=================");

const resultArr2 = [];
array.forEach((n) => resultArr2.push(n * n));

console.log(resultArr2);

//map 사용한다면  -- 각각 요소를 바꿔줘!!  바꾼 값들을 새배열로 만들어서 리턴해줘요.
console.log("=================map====================");

const resultArr3 = array.map((n) => n * n);
console.log(resultArr3);

//filter   ---  참인것만 배열에 담아서 리턴.  (필터링, 조건선별)
console.log("=================filter====================");

const resultArr4 = array.filter((n) => n % 2 === 0);
console.log(resultArr4);

// const array = [1, 2, 3, 4, 5];
//reduce
console.log("=================reduce====================");

let sum = array.reduce((a, c) => {
  console.log(a + "::" + c);

  return a + c;
}, 0);

console.log(sum);
