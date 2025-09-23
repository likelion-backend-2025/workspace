function add(a) {
  console.log("a+a");
  return a + a;
}

function add(a, b) {
  console.log("a+b");
  console.log(arguments.length);
  console.log("4번째 매개변수 :: " + arguments[3]);
  return a + b;
}

//자바스크립트는 오버로딩이 되지 않는다.

let result = add(10);
console.log(result);

console.log(add(10, 20));

console.log(add(10, 20, 30));
console.log(add(10, 20, 30, 40));
console.log(add(10, 20, 30, 40, 50));
