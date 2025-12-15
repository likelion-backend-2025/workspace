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

/**
 * 새 할 일 추가
 * POST /api/todos
 */
export async function createTodo(data) {
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    throw new Error('할 일 추가에 실패했습니다');
  }

  return response.json();
}

/**
 * 할 일 수정
 * PUT /api/todos/:id
 */
export async function updateTodo(id, data) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    throw new Error('할 일 수정에 실패했습니다');
  }

  return response.json();
}

/**
 * 할 일 완료 상태 토글
 * PATCH /api/todos/:id/toggle
 */
export async function toggleTodo(id) {
  const response = await fetch(`${API_URL}/${id}/toggle`, {
    method: 'PATCH',
  });

  if (!response.ok) {
    throw new Error('상태 변경에 실패했습니다');
  }

  return response.json();
}

/**
 * 할 일 삭제
 * DELETE /api/todos/:id
 */
export async function deleteTodo(id) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'DELETE',
  });

  if (!response.ok) {
    throw new Error('할 일 삭제에 실패했습니다');
  }
}