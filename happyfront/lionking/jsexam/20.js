console.log("시작!!!");

function work() {
  console.log("work 작업 시작!!");
  const start = Date.now();
  for (let i = 0; i < 1000000000; i++) {}
  const end = Date.now();
  console.log("work 작업 끝!!");

  console.log(end - start + "ms");
}

// work(); // 시간이 많이 걸리는 작업!!
setTimeout(work, 0);  //3000  정확히 3초있다 실행해!! 는 아니다.  

console.log("다음작업");

function hi() {
  console.log("hi");
}

hi();

console.log("다음 할일 1");
console.log("다음 할일 2");

console.log("끝!!");
