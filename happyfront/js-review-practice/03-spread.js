const arr1 = [1, 2, 3];
const arr2 = [4, 5, 6];

const combined = [...arr1, ...arr2];
console.log(combined); // [1, 2, 3, 4, 5, 6]

const copy = [...arr1]; //...  이 사용될 때 새로운 배열이 만들어지는 것이 핵심!!!
// const copy = arr1;
console.log(copy); // [1, 2, 3]

arr1[0] = 100;

console.log(arr1);
console.log(copy);

const withMiddle = [...arr1, 10, 20, ...arr2];
console.log(withMiddle);

const user = {
  name: "김철수",
  age: 25,
};

const userCopy = { ...user };

const userWithEmail = { ...user, email: "kim@example.com" };

const changeName = { ...user, name: "kang" };

console.log(userCopy);
console.log(userWithEmail);
console.log(changeName);

let todos = [
  { id: 1, text: "리액트 공부하기", done: false },
  { id: 2, text: "자바스크립트 복습하기", done: true },
];

function setTodos(newTodos) {
  todos = newTodos;
  console.log("Todo update :: ", todos);
}
// 퀴즈1  위의 todos 배열에 항목을 하나 더 추가해보세요.
const newTodo = { id: 3, text: "맛있는거 먹기", done: false };
setTodos([...todos, newTodo]);

//퀴즈2.  todos 배열에서 id 가 2 번인 데이터를 삭제해 주세요.
setTodos(todos.filter((todo) => todo.id !== 2));

// 퀴즈3 todos 배열에서 id 가 1번인 데이터의 done 이 true라면 false로
// false라면 true로 바꿔 주세요.
setTodos(
  todos.map((todo) => (todo.id === 1 ? { ...todo, done: !todo.done } : todo))
);

let cartState = [
  { id: 1, productName: "pen", price: 3000, quantity: 1 },
  { id: 2, productName: "note", price: 5000, quantity: 5 },
  { id: 3, productName: "pad", price: 300000, quantity: 2 },
];

//퀴즈1 id가 1인 상품의 수량을 3으로 바꿔 주세요.

let cartState1 = cartState.map((product) =>
  product.id === 1 ? { ...product, quantity: 3 } : product
);
console.log(cartState1);

// 퀴즈2 새로운 상품을 하나 추가해보세요.
let cartState2 = [
  ...cartState,
  { id: 4, productName: "phone", price: 500000, quantity: 1 },
];
console.log(cartState2);

// 퀴즈3 id가 3번인 상품을 삭제해 주세요.
let cartState3 = cartState.filter((product) => product.id !== 3);
console.log(cartState3);

// 첫 번째 인자는 first, 나머지는 모두 others 배열로
function introduce(first, ...others) {
  console.log("첫 번째:", first);
  console.log("나머지:", others);
}

introduce("사과", "바나나", "딸기", "포도");
// 첫 번째: 사과
// 나머지: ["바나나", "딸기", "포도"]

const numbers = [1, 2, 3, 4, 5];

const [first, second, ...rest] = numbers;

console.log(first); // 1
console.log(second); // 2
console.log(rest); // [3, 4, 5]

const user2 = {
  name: "김철수",
  age: 25,
  email: "kim@example.com",
  phone: "010-1234-5678",
};

const { name, ...others } = user2;

console.log(name); // 김철수
console.log(others); // { age: 25, email: "kim@example.com", phone: "010-1234-5678" }
