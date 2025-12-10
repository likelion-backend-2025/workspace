import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Welcome from "./component/exam/Welcome";
import UserInfo from "./component/exam/UserInfo";
import Page from "./component/page/Page";
import UseStateExam1 from "./component/useState/UseStateExma1";
import Hello from "./component/exam/Hello";
import TextInput from "./component/useState/TextInput";
import Time from "./component/useState/Time";
import UseStateExam2 from "./component/useState/UseStateExam2";
import ParentComponent from "./component/exam/ParentComponent";
import ParentComponent2 from "./component/exam/ParentComponent2";
import ChildrenExam from "./component/exam/ChildrenExam";
import Layout from "./component/exam/Layout";
import Dashboard from "./component/exam/Dashboard";

function App() {
  console.log("👌 App 실행!!");

  return (
    <>
      {/* <UseStateExam1 />
      <UseStateExam2 /> */}
      {/* <ParentComponent /> */}
      {/* <ParentComponent2 /> */}
      {/* <ChildrenExam />
      <Layout>
        <h1>안녕하세요.</h1>
        <p>여기는 main 영역입니다. </p>
        <button>자세히 보기</button>
      </Layout> */}
      <Dashboard />
    </>
  );
}

export default App;
