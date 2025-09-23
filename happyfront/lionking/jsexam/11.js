const array = [];

console.log(typeof array);

const fruits = ["사과", "바나나", "오렌지"];
console.log(fruits);

const numbers = [1, 2, 3, 4, 5];
const mixed = [1, "hello", true, null];

console.log(mixed);
console.log(numbers);

numbers[0] = 100;

console.log(numbers);

console.log(numbers.length);

const array2 = new Array(10);

console.log(array2.length);

const array3 = new Array(10, 20, 30);

console.log(array3.length);

const arr = [1, 2, 3];

// 추가/제거
arr.push(4); // 끝에 추가: [1, 2, 3, 4]
arr.pop(); // 끝에서 제거: [1, 2, 3]
arr.unshift(0); // 앞에 추가: [0, 1, 2, 3]
arr.shift(); // 앞에서 제거: [1, 2, 3]

// 연결과 분할
const arr2 = [4, 5];
const combined = arr.concat(arr2); // [1, 2, 3, 4, 5]
const sliced = combined.slice(2, 5); // [2, 3]

console.log("concat" + combined);
console.log("slice" + sliced);

array3[0] = { name: "kang" };
console.log(array3);
array3.push(() => console.log("hello array!!"));

console.log(array3);
array3[3]();

//[ { name: 'kang' }, 20, 30, [Function (anonymous)] ]

//배열 비구조할당   -- 순서가 중요!!

const person = array3[0];
console.log(person);

const [a, b, c, d] = array3;

d();
console.log(c);

const [aa, bb] = array3;

console.log(aa);
