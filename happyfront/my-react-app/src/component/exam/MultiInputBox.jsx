import { useState } from "react";
import MyInputBox from "./MyInpuBox";

const MultiInputBox = () => {
  const [inputs, setInputs] = useState({
    title: "",
    price: "",
    color: "",
    count: "",
  });
  const { title, price, color, count } = inputs;

  const changeHandler = (e) => {
    // 이 때 값을 어떻게 저장할까요?
    console.log(e.target);
    console.log(e.target.name);
    const { name, value } = e.target;
    setInputs({
      ...inputs,
      [name]: value,
    });
  };
  const resetHandler = () => {
    setInputs({
      title: "",
      price: "",
      color: "",
      count: "",
    });
  };
  return (
    <div>
      <p>title :{title} </p>
      <p>price :{price} </p>
      <p>color :{color} </p>
      <p>count :{count} </p>
      <label htmlFor="title"> title : </label>
      <input
        id="title"
        type="text"
        value={title}
        name="title"
        placeholder="상품이름을 입력하세요."
        onChange={changeHandler}
      />
      <br />
      <label htmlFor="price"> price : </label>
      <input
        id="price"
        type="number"
        value={price}
        name="price"
        placeholder="상품 가격을 입력하세요."
        onChange={changeHandler}
      />
      <br />
      <label htmlFor="color"> color : </label>
      <input
        id="color"
        type="text"
        value={color}
        name="color"
        placeholder="색상을 입력하세요."
        onChange={changeHandler}
      />
      <br />
      <label htmlFor="count"> count : </label>
      <input
        id="count"
        type="text"
        value={count}
        name="count"
        placeholder="수량을 입력하세요."
        onChange={changeHandler}
      />
      <br />
      <button onClick={resetHandler}>RESET</button>
    </div>
  );
};

export default MultiInputBox;
