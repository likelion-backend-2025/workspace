import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Welcome from "./component/exam/Welcome";
import UserInfo from "./component/exam/UserInfo";
import Page from "./component/page/Page";

function App() {
  const [count, setCount] = useState(0);

  return (
    <div>
      {/* <Welcome /> */}
      {/* <Hello /> */}
      {/* <UserInfo /> */}
      <Page />
    </div>
  );
}

export default App;
