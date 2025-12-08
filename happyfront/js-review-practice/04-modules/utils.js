export const PI = 3.14159;
export const USER_NAME = "carami";

// utils.js
function add(a, b) {
  return a + b;
}

function subtract(a, b) {
  return a - b;
}

// 또는 한 번에 내보내기
const multiply = (a, b) => a * b;
const divide = (a, b) => a / b;

export { multiply, divide, add, subtract };
