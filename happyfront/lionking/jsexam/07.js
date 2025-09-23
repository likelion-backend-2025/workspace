const person = {
  name: "강경미",
  age: 25,
  city: "서울",
  isStudent: true,
};

console.log(typeof person);

console.log(person.name);
console.log(person["name"]);

const obj = {}; // {} 가 객체임을 나타냄

console.log(typeof obj);

person.address = "경기도";

console.log(person.address);
person.age = 30;

let result = person.age;

const dog = {
  name: "멍멍이",
  age: 2,
  sound: "멍멍",

  say: function () {
    console.log(this.sound);
    console.log("say" + this);
  },
  //   say2: () => {
  //     console.log(this.sound);
  //     console.log("say2" + this);
  //   },  //객체 안에서는 화살표 함수로 함수를 정의하면 안됨!!
};

const winTest = () => {
  console.log("객체 밖:::" + this);
};

console.log(dog);
console.log(dog.name);
console.log(dog["name"]);
dog.say();
// dog.say2();
winTest();
