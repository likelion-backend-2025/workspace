// const Welcome = () => <h1> 안녕!! 난 리액트야. </h1>;

import Hello from "./Hello";

function Welcome() {
  return (
    <div>
      <h1>안녕!! 난 리액트야. </h1>
      <h2>hahah</h2>

      <Hello />
      <Hello />
      <Hello />
    </div>
  );
}

export default Welcome;
