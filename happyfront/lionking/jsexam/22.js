function work(callback) {
  console.log("work start!!");

  setTimeout(() => {
    console.log("setTimeout() start!!");
    console.log("setTimeout() end!!");

    callback();
  }, 1000);
}

work(() => {
  console.log("work end!!");
}); //실제 하고 싶은 work() 함수가...

// work 함수가 호출 될때.. 끝난 다음에 할일을 같이 보내야 함.

// setTimeout() 이 끝난 다음에 실행하고 싶은 일이 있다면??
