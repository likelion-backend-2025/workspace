function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

console.log("start");

async function process() {
  await sleep(1000);
  console.log("end");
}

console.log("process 호출 전!! ");

process().then(() => {
  console.log("작업이끝났어요.");
});

console.log("process 호출 후!! ");
