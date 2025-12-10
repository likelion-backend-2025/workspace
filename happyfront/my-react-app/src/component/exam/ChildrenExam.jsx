const First = () => {
  return <h3>나는 1번 자식 컴포넌트</h3>;
};

const Second = ({ name }) => {
  return <h3>나는 {name} 자식 컴포넌트</h3>;
};

const Parent = ({ a, test, children, color }) => {
  const style = {
    border: "4px solid green",
    padding: "20px",
    margin: "10px",
    borderRadius: "10px",
  };
  // {color} == {color:color}
  return (
    <div style={style}>
      <p style={{ color }}> parent 가 보여줄 컴포넌트 영역</p>
      <hr style={{ borderColor: "green", opacity: 0.3 }} />
      {children}
    </div>
  );
};

const ChildrenExam = () => {
  return (
    <div>
      <h2>Props.children </h2>

      <Parent a="abc" test="123" color="red">
        <First />
        <Second name={"kang"} />
        <Second name={"kim"} />
      </Parent>
    </div>
  );
};

export default ChildrenExam;
