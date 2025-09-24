let oldname = "kang";
let newname = "carami";

if (newname) {
  oldname = newname;
}

//위의 if문과 같은 코드!!
newname && (oldname = newname);

newname &&= oldname;
//위의 세개가 같은 결과

const person = { name: "", age: 0 };

// person.age ||= 30;
// person.name ||= "carami";

console.log(person);

function makePerson(obj) {
  obj.name ??= "guest";
  obj.age ??= 20;
  return obj;
}

console.log(makePerson({}));
console.log(makePerson({ name: "carami" }));
console.log(makePerson(person));
