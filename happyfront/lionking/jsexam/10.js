const obj = {
  name: "객체",

  // 일반 함수 - this가 객체를 가리킴
  normalFunc: function () {
    console.log(this.name); // '객체'
  },

  // 화살표 함수 - this가 상위 스코프를 가리킴
  arrowFunc: () => {
    console.log(this.name); // undefined
  },
};

obj.normalFunc();
obj.arrowFunc();
