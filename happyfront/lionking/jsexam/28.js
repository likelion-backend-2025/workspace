// Promise 생성
function delay(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(`${ms}ms 대기 완료`);
    }, ms);
  });
}

// async 함수
async function fetchData() {
  try {
    console.log("데이터 요청 시작");

    // 1초 대기
    await delay(1000);
    console.log("첫 번째 작업 완료");

    // 2초 대기
    await delay(2000);
    console.log("두 번째 작업 완료");

    return "모든 작업 완료!";
  } catch (error) {
    console.error("에러 발생:", error);
  }
}

// 실행
fetchData().then((result) => {
  console.log(result);
});
