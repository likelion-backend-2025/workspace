// 반복문
const array = [1, 2, 3, 4, 5, 6, 7];
for (let i = 0; i < array.length; i++) {
  console.log(array[i]);
}

const squared = [];

for (let index = 0; index < array.length; index++) {
  //   squared.push(array[index] * array[index]);
  squared.push(squar(array[index]));
}

console.log(squared);

const squared2 = [];

const func2 = (n) => squared2.push(n * n);

array.forEach(func2);

array.forEach(squar);

function squar(n) {
  return n * n;
}
