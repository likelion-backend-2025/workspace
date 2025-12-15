import Link from "next/link";

export default function Home() {
  return (
    <div>
      <h1>Hello next.js</h1>

      <Link href="/about">About</Link> <br />
      <Link href="/todos">Todos</Link> <br />
    </div>
  );
}
