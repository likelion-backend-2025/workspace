import axios from 'axios';

// API 기본 URL 설정
const API_BASE_URL = 'http://localhost:8080/api/todos';

// axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

/**
 * 모든 할 일 조회
 * @returns {Promise<Array>} 할 일 목록
 */
export const getAllTodos = async () => {
  try {
    const response = await apiClient.get('');
    return response.data;
  } catch (error) {
    console.error('할 일 목록 조회 실패:', error);
    if (error.response) {
      console.error('응답 상태:', error.response.status);
      console.error('응답 데이터:', error.response.data);
    } else if (error.request) {
      console.error('요청이 전송되었지만 응답을 받지 못했습니다:', error.request);
    } else {
      console.error('요청 설정 중 오류:', error.message);
    }
    throw error;
  }
};

/**
 * 특정 할 일 조회
 * @param {number} id - 할 일 ID
 * @returns {Promise<Object>} 할 일 객체
 */
export const getTodoById = async (id) => {
  try {
    const response = await apiClient.get(`/${id}`);
    return response.data;
  } catch (error) {
    console.error(`할 일 조회 실패 (ID: ${id}):`, error);
    if (error.response) {
      console.error('응답 상태:', error.response.status);
      console.error('응답 데이터:', error.response.data);
    }
    throw error;
  }
};

/**
 * 할 일 생성
 * @param {string} text - 할 일 내용
 * @param {boolean} completed - 완료 여부 (기본값: false)
 * @returns {Promise<Object>} 생성된 할 일 객체
 */
export const createTodo = async (text, completed = false) => {
  try {
    const response = await apiClient.post('', {
      text: text,
      completed: completed,
    });
    return response.data;
  } catch (error) {
    console.error('할 일 생성 실패:', error);
    if (error.response) {
      console.error('응답 상태:', error.response.status);
      console.error('응답 데이터:', error.response.data);
    }
    throw error;
  }
};

/**
 * 할 일 수정
 * @param {number} id - 할 일 ID
 * @param {Object} todo - 수정할 할 일 데이터
 * @param {string} todo.text - 할 일 내용
 * @param {boolean} todo.completed - 완료 여부
 * @returns {Promise<Object>} 수정된 할 일 객체
 */
export const updateTodo = async (id, todo) => {
  try {
    const response = await apiClient.put(`/${id}`, {
      text: todo.text,
      completed: todo.completed,
    });
    return response.data;
  } catch (error) {
    console.error(`할 일 수정 실패 (ID: ${id}):`, error);
    throw error;
  }
};

/**
 * 완료 상태 토글
 * @param {number} id - 할 일 ID
 * @returns {Promise<Object>} 토글된 할 일 객체
 */
export const toggleTodo = async (id) => {
  try {
    const response = await apiClient.patch(`/${id}/toggle`);
    return response.data;
  } catch (error) {
    console.error(`할 일 토글 실패 (ID: ${id}):`, error);
    throw error;
  }
};

/**
 * 할 일 삭제
 * @param {number} id - 할 일 ID
 * @returns {Promise<void>}
 */
export const deleteTodo = async (id) => {
  try {
    await apiClient.delete(`/${id}`);
  } catch (error) {
    console.error(`할 일 삭제 실패 (ID: ${id}):`, error);
    throw error;
  }
};

