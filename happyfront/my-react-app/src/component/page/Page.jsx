import Footer from "./Footer";
import Header from "./Header";
import Main from "./Main";

const Page = () => {
  const name = "carami";
  const hobbies = ["축구", "코딩", "수영"];
  return (
    <div>
      <Header />
      <Main name={name} hobbies={hobbies} />
      <Footer></Footer>
    </div>
  );
};

export default Page;
