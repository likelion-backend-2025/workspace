import Calculator from "./Calculator.js";
import { add, PI, USER_NAME } from "./utils.js";

console.log(PI);
console.log(USER_NAME);

const result = add(3, 6);
console.log(result);

const cal = new Calculator();

let sum = cal.add(2, 4);
console.log(sum);
