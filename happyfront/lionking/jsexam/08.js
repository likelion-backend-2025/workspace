const user = {
  username: "john",
  email: "john@email.com",
  age: 30,
};

const username2 = user.username;
const email2 = user.email;
const age2 = user.age;

// const { email } = user;

// console.log(username);

// console.log(email);

//객체 구조분해 할당.  -- 이름이 중요해요~~~
const { a, b, c } = user;

console.log(a);

const { age, username, email } = user;

console.log(age);

function print2(person) {
  const text = `${person.username} 의 나이는 ${person.age} 이고 이메일은 ${person.email} 입니다.`;
  console.log(text);
}

//구조분해 이해되시나요??

function print2({ username, age, email }) {
  const text = `${username} 의 나이는 ${age} 이고 이메일은 ${email} 입니다.`;
  console.log(text);
}

print(user);

console.log("hi");
