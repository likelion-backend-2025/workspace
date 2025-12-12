# Todo API 테스트 가이드

## 서버 실행 방법

### 방법 1: Gradle 사용
```powershell
cd backend
.\gradlew bootRun
```

### 방법 2: IDE에서 실행
- IntelliJ IDEA 또는 Eclipse에서 `BackendApplication.java`를 실행

## 서버 실행 확인

서버가 정상적으로 실행되었는지 확인:
```powershell
curl.exe http://localhost:8080/api/todos
```

빈 배열 `[]`이 반환되면 서버가 정상적으로 실행 중입니다.

## 테스트 스크립트 실행

### PowerShell 스크립트 (권장)
```powershell
cd backend
.\test-api.ps1
```

실행 정책 오류가 발생하면:
```powershell
powershell -ExecutionPolicy Bypass -File test-api.ps1
```

### Command Prompt 배치 파일
```cmd
cd backend
test-api.bat
```

## 수동 테스트 (curl 명령어)

### 1. 모든 할 일 조회
```powershell
curl.exe http://localhost:8080/api/todos
```

### 2. 할 일 생성
```powershell
curl.exe -X POST http://localhost:8080/api/todos -H "Content-Type: application/json" -d "{\"text\":\"테스트 할 일\",\"completed\":false}"
```

### 3. 특정 할 일 조회
```powershell
curl.exe http://localhost:8080/api/todos/1
```

### 4. 할 일 수정
```powershell
curl.exe -X PUT http://localhost:8080/api/todos/1 -H "Content-Type: application/json" -d "{\"text\":\"수정된 할 일\",\"completed\":true}"
```

### 5. 완료 상태 토글
```powershell
curl.exe -X PATCH http://localhost:8080/api/todos/1/toggle
```

### 6. 할 일 삭제
```powershell
curl.exe -X DELETE http://localhost:8080/api/todos/1
```

## 예상 응답

### 성공 응답 예시
```json
{
  "id": 1,
  "text": "테스트 할 일",
  "completed": false,
  "createdAt": "2024-01-01T12:00:00",
  "updatedAt": "2024-01-01T12:00:00"
}
```

### 에러 응답 예시 (404)
```json
{
  "error": "Resource Not Found",
  "message": "할 일을 찾을 수 없습니다. ID: 999",
  "status": 404
}
```

### Validation 에러 응답 예시 (400)
```json
{
  "error": "Validation Failed",
  "message": "입력값 검증에 실패했습니다.",
  "fieldErrors": {
    "text": "할 일 내용은 필수입니다."
  },
  "status": 400
}
```

