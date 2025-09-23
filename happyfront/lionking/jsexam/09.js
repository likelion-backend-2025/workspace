const user = {
  _name: "김철수",
  _age: 25,

  // Getter
  get name() {
    console.log("name getter 호출");
    return this._name;
  },

  // Setter
  set name(value) {
    console.log("name setter 호출");
    if (value.length < 2) {
      console.log("이름은 2글자 이상이어야 합니다.");
      return;
    }
    this._name = value;
  },
};

// 사용
console.log(user.name); // getter 호출   --함수에 접근 한 것 처럼 보이지는 않쵸?
user.name = "이영희"; // setter 호출
console.log(user.name);

// user.age = -5; // 에러 메시지

// console.log(user._name);
// user._name = "강경미";
// console.log(user._name);
// user._age = -5;
// console.log(user._age);

const numbers = {
  _a: 1,
  _b: 2,

  get sum() {
    return this._a + this._b;
  },
};

console.log(numbers.sum);
