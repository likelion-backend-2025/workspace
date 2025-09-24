// Promise 생성
function delay(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(`${ms}ms 대기 완료`);
    }, ms);
  });
}

// Promise 사용
delay(1000)
  .then((result) => {
    console.log(result);
    return delay(2000);
  })
  .then((result) => {
    console.log(result);
  })
  .catch((error) => {
    console.error("에러:", error);
  });
