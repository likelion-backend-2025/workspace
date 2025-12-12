@echo off
REM Todo API 테스트 스크립트 (Windows Command Prompt)
REM 사용법: test-api.bat

echo ========================================
echo Todo API 테스트 시작
echo ========================================
echo.

set BASE_URL=http://localhost:8080/api/todos

echo 1. 모든 할 일 조회 (GET /api/todos)
curl.exe -X GET %BASE_URL%
echo.
echo.

echo 2. 할 일 생성 (POST /api/todos)
curl.exe -X POST %BASE_URL% -H "Content-Type: application/json" -d "{\"text\":\"테스트 할 일 1\",\"completed\":false}"
echo.
echo.

echo 3. 특정 할 일 조회 (GET /api/todos/1)
curl.exe -X GET %BASE_URL%/1
echo.
echo.

echo 4. 할 일 수정 (PUT /api/todos/1)
curl.exe -X PUT %BASE_URL%/1 -H "Content-Type: application/json" -d "{\"text\":\"수정된 할 일\",\"completed\":true}"
echo.
echo.

echo 5. 완료 상태 토글 (PATCH /api/todos/1/toggle)
curl.exe -X PATCH %BASE_URL%/1/toggle
echo.
echo.

echo 6. Validation 테스트 - 빈 텍스트 (POST /api/todos)
curl.exe -X POST %BASE_URL% -H "Content-Type: application/json" -d "{\"text\":\"\",\"completed\":false}"
echo.
echo.

echo 7. 존재하지 않는 할 일 조회 - 404 테스트 (GET /api/todos/9999)
curl.exe -X GET %BASE_URL%/9999
echo.
echo.

echo 8. 할 일 삭제 (DELETE /api/todos/1)
curl.exe -X DELETE %BASE_URL%/1
echo.
echo.

echo ========================================
echo Todo API 테스트 완료
echo ========================================
pause

