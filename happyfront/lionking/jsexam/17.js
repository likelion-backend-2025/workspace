function Animal(type, name, sound) {
  this.type = type;
  this.name = name;
  this.sound = sound;
  this.say = function () {
    console.log(this.sound);
  };
}

const dog = new Animal("개", "멍멍이", "멍멍");

dog.say();
console.log(dog.name);

const cat = new Animal("고양이", "야옹이", "야옹");
cat.say();

cat.say = function () {
  console.log("hohoho");
};

cat.say();

dog.say();
