// Spring Boot Todo API 연동 (Fetch API + async/await)
// API 엔드포인트: http://localhost:8080/api/todos

const API_BASE_URL = '/api/todos';

// ========================================
// API 함수들
// ========================================

/**
 * 모든 Todo 목록 조회
 */
async function getTodos() {
    try {
        const response = await fetch(API_BASE_URL);

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const todos = await response.json();
        console.log('Loaded todos:', todos);

        // 기존 목록 초기화 후 렌더링
        document.getElementById('todo-item-list').innerHTML = '';
        todos.forEach(todo => todoItemAdd(todo));

    } catch (error) {
        console.error('Failed to load todos:', error);
        alert('할 일 목록을 불러오는데 실패했습니다.');
    }
}

/**
 * Todo 생성
 * @param {string} title - Todo 제목
 */
async function createTodo(title) {
    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify({
                title: title,
                description: '',
                completed: false
            })
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const newTodo = await response.json();
        console.log('Todo created:', newTodo);
        todoItemAdd(newTodo);

    } catch (error) {
        console.error('Failed to create todo:', error);
        alert('할 일 추가에 실패했습니다.');
    }
}

/**
 * Todo 수정 (완료 상태 토글)
 * @param {number} id - Todo ID
 * @param {string} title - Todo 제목
 * @param {boolean} completed - 현재 완료 상태
 */
async function updateTodo(id, title, completed) {
    try {
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify({
                title: title,
                description: '',
                completed: !completed  // 토글
            })
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const updatedTodo = await response.json();
        console.log('Todo updated:', updatedTodo);

    } catch (error) {
        console.error('Failed to update todo:', error);
        alert('할 일 수정에 실패했습니다.');
    }
}

/**
 * Todo 삭제
 * @param {number} id - Todo ID
 */
async function deleteTodo(id) {
    try {
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            }
        });

        if (!response.ok && response.status !== 204) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        console.log('Todo deleted:', id);

    } catch (error) {
        console.error('Failed to delete todo:', error);
        alert('할 일 삭제에 실패했습니다.');
    }
}

// ========================================
// UI 렌더링 함수
// ========================================

/**
 * Todo 아이템을 DOM에 추가
 * @param {Object} todoObj - Todo 객체 {id, title, description, completed}
 */
function todoItemAdd(todoObj) {
    console.log('Adding todo:', todoObj);

    // li 요소 생성
    const li = document.createElement('li');
    li.className = 'todo-item';
    li.setAttribute('id', todoObj.id);

    // 텍스트 영역 생성
    const textSpan = document.createElement('span');
    textSpan.className = 'todo-text';
    textSpan.textContent = todoObj.title;

    // 완료된 항목이면 checked 클래스 추가
    if (todoObj.completed === true) {
        textSpan.classList.add('checked');
    }

    // 삭제 버튼 생성
    const removeSpan = document.createElement('span');
    removeSpan.className = 'remove';
    removeSpan.textContent = '\u00D7';

    // 삭제 버튼 이벤트
    removeSpan.addEventListener('click', async (event) => {
        event.stopPropagation();

        const todoId = li.getAttribute('id');
        await deleteTodo(todoId);
        li.remove();
    });

    // DOM에 추가
    li.appendChild(textSpan);
    li.appendChild(removeSpan);
    document.getElementById('todo-item-list').appendChild(li);
}

// ========================================
// 이벤트 리스너 설정
// ========================================

// 페이지 로드 시 Todo 목록 가져오기
getTodos();

// Todo 목록 클릭 이벤트 (완료 상태 토글)
const todoUl = document.querySelector('#todo-item-list');
todoUl.addEventListener('click', async (event) => {
    let eventTarget = event.target;

    // LI 또는 SPAN 클릭 시
    if (eventTarget.tagName === 'LI' || eventTarget.tagName === 'SPAN') {
        // LI를 클릭한 경우 내부 SPAN 찾기
        if (eventTarget.tagName === 'LI') {
            eventTarget = eventTarget.querySelector('.todo-text');
        }

        // 삭제 버튼이 아닌 경우만 처리
        if (eventTarget.className !== 'remove') {
            const liObj = eventTarget.parentElement;
            const todoId = liObj.getAttribute('id');
            const todoTitle = eventTarget.textContent;
            const isCompleted = eventTarget.classList.contains('checked');

            // 서버에 업데이트 요청
            await updateTodo(todoId, todoTitle, isCompleted);

            // UI 즉시 업데이트
            eventTarget.classList.toggle('checked');
        }
    }
});

// 추가 버튼 클릭 이벤트
const inputBtn = document.querySelector('.add-button');
inputBtn.addEventListener('click', async () => {
    const inputElement = document.querySelector('#myInput');
    const inputValue = inputElement.value.trim();

    if (inputValue === '') {
        alert('할 일을 입력해 주세요 ^^');
        return;
    }

    // 입력창 초기화
    inputElement.value = '';

    // Todo 생성
    await createTodo(inputValue);
});

// Enter 키로 Todo 추가
document.querySelector('#myInput').addEventListener('keypress', async (event) => {
    if (event.key === 'Enter') {
        inputBtn.click();
    }
});