function FruitList() {
  const fruits = ["사과", "바나나", "오렌지", "포도", "딸기"];

  return (
    <ul>
      {fruits.map((fruit, index) => (
        <li key={index}>{fruit}</li>
      ))}
    </ul>
  );
}

export default FruitList;
