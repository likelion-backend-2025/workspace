const API_URL = "http://localhost:8080/api/todos";

//전체 목록 조회 
export async function fetchTodos(){
    const res = await fetch(API_URL,{
        method: "GET",
        cache: "no-store",  //Next.js에서 데이터를 캐시하지 않도록 설정
    });
    if (!res.ok){
        throw new Error("전체 목록 조회 실패");
    }
    return res.json();
}

