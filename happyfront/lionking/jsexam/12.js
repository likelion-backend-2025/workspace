// 스프레드 연산자 (...)
const arr1 = [1, 2, 3];
const arr2 = [4, 5, 6];

console.log([arr1, arr2]);

console.log(arr1);

console.log(...arr1);

const combined = [...arr1, ...arr2]; // [1, 2, 3, 4, 5, 6]

console.log(combined);

// 배열 복사
const original = [1, 2, 3];
const copy = [...original]; // 새로운 배열 생성

original.push(30);

console.log(original);
console.log(copy);

console.log(("b" + "a" + +"a" + "a").toLowerCase());
console.log((+"b" + "a" + +"a" + "a").toLowerCase());
