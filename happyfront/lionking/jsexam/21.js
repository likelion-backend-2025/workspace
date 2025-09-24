function work() {
  console.log("work start!!");

  setTimeout(() => {
    console.log("setTimeout() start!!");
  }, 1000);
  console.log("work end!!");
}

work();
