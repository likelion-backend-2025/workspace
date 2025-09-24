// forEach(함수)  --  요소를 하나씩 꺼내서 함수를 실행해주는 일
// console.log("===================================");
const array = [1, 2, 3, 4, 5];
// function print(number) {
//   console.log(number);
// }
const print = (number) => console.log(number);

array.forEach(print);

array.forEach((n) => console.log(n));
