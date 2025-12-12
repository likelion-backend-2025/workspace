# Todo API 테스트 스크립트 (Windows PowerShell)
# 사용법: .\test-api.ps1

$baseUrl = "http://localhost:8080/api/todos"
$headers = @{
    "Content-Type" = "application/json"
}

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Todo API 테스트 시작" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# 1. 모든 할 일 조회
Write-Host "1. 모든 할 일 조회 (GET /api/todos)" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri $baseUrl -Method Get
    Write-Host "성공: $($response.Count)개의 할 일이 있습니다." -ForegroundColor Green
    $response | ConvertTo-Json -Depth 3
} catch {
    Write-Host "실패: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# 2. 할 일 생성
Write-Host "2. 할 일 생성 (POST /api/todos)" -ForegroundColor Yellow
$newTodo = @{
    text = "테스트 할 일 1"
    completed = $false
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri $baseUrl -Method Post -Headers $headers -Body $newTodo
    $createdId = $response.id
    Write-Host "성공: 할 일이 생성되었습니다. ID: $createdId" -ForegroundColor Green
    $response | ConvertTo-Json -Depth 3
} catch {
    Write-Host "실패: $($_.Exception.Message)" -ForegroundColor Red
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $responseBody = $reader.ReadToEnd()
        Write-Host "응답: $responseBody" -ForegroundColor Red
    }
}
Write-Host ""

# 3. 특정 할 일 조회
Write-Host "3. 특정 할 일 조회 (GET /api/todos/1)" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/1" -Method Get
    Write-Host "성공: 할 일을 조회했습니다." -ForegroundColor Green
    $response | ConvertTo-Json -Depth 3
} catch {
    Write-Host "실패: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# 4. 할 일 수정
Write-Host "4. 할 일 수정 (PUT /api/todos/1)" -ForegroundColor Yellow
$updateTodo = @{
    text = "수정된 할 일"
    completed = $true
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "$baseUrl/1" -Method Put -Headers $headers -Body $updateTodo
    Write-Host "성공: 할 일이 수정되었습니다." -ForegroundColor Green
    $response | ConvertTo-Json -Depth 3
} catch {
    Write-Host "실패: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# 5. 완료 상태 토글
Write-Host "5. 완료 상태 토글 (PATCH /api/todos/1/toggle)" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/1/toggle" -Method Patch
    Write-Host "성공: 완료 상태가 토글되었습니다." -ForegroundColor Green
    $response | ConvertTo-Json -Depth 3
} catch {
    Write-Host "실패: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# 6. Validation 테스트 (빈 텍스트)
Write-Host "6. Validation 테스트 - 빈 텍스트 (POST /api/todos)" -ForegroundColor Yellow
$invalidTodo = @{
    text = ""
    completed = $false
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri $baseUrl -Method Post -Headers $headers -Body $invalidTodo
    Write-Host "실패: Validation이 작동하지 않습니다!" -ForegroundColor Red
} catch {
    Write-Host "성공: Validation이 정상 작동합니다." -ForegroundColor Green
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $responseBody = $reader.ReadToEnd()
        Write-Host "에러 응답: $responseBody" -ForegroundColor Yellow
    }
}
Write-Host ""

# 7. 존재하지 않는 할 일 조회 (404 테스트)
Write-Host "7. 존재하지 않는 할 일 조회 - 404 테스트 (GET /api/todos/9999)" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/9999" -Method Get
    Write-Host "실패: 404 에러가 발생하지 않았습니다!" -ForegroundColor Red
} catch {
    Write-Host "성공: 404 에러가 정상적으로 발생했습니다." -ForegroundColor Green
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $responseBody = $reader.ReadToEnd()
        Write-Host "에러 응답: $responseBody" -ForegroundColor Yellow
    }
}
Write-Host ""

# 8. 할 일 삭제
Write-Host "8. 할 일 삭제 (DELETE /api/todos/1)" -ForegroundColor Yellow
try {
    Invoke-RestMethod -Uri "$baseUrl/1" -Method Delete
    Write-Host "성공: 할 일이 삭제되었습니다." -ForegroundColor Green
} catch {
    Write-Host "실패: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Todo API 테스트 완료" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

