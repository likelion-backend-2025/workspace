const person = {
  name: "carami",
  job: {
    title: "student",
    manager: {
      name: "kang",
    },
  },
};
// ?.
function printManagerName(person) {
  console.log(person?.job?.manager?.name);
}

printManagerName(person);
printManagerName({ name: "kim" });

const box = {
  name: "penbox",
  open() {
    console.log("나는 펜입니다.");
  },
};

//?.()

box.open?.();
const box2 = { name: "box" };

box2.open?.();

//?.[]
console.log(person?.["name"]);
console.log({}?.["name"]);
