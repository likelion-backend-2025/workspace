## 🍎 macOS에서 Base64 인코딩하기

맥에서는 기본적으로 `base64` 명령어를 지원합니다.

```bash
echo -n "carami:1234" | base64
```

출력 예시:

```text
Y2FyYW1pOjEyMzQ=
```

> ✅ `-n` 옵션을 꼭 붙여야 끝에 줄바꿈 문자가 포함되지 않습니다.
> (붙이지 않으면 결과가 달라질 수 있습니다.)

---

## 🪟 Windows에서 Base64 인코딩하기

Windows에서는 PowerShell을 이용하면 됩니다.

```powershell
[Convert]::ToBase64String([Text.Encoding]::UTF8.GetBytes("carami:1234"))
```

출력 예시:

```text
Y2FyYW1pOjEyMzQ=
```

> 💡 CMD에서는 이 기능이 기본 제공되지 않기 때문에
> 반드시 **PowerShell** 창에서 실행해야 합니다.
> (`powershell` 입력 후 진입 가능)

---

## ✅ 결과 사용 예시

이렇게 얻은 값을 헤더에 추가합니다.

```text
Authorization: Basic Y2FyYW1pOjEyMzQ=
```

또는 `curl` 명령에서도 바로 추가 가능 👇

### macOS / Linux

```bash
curl -u carami:1234 http://localhost:8080
```

### Windows CMD / PowerShell

```powershell
curl -u carami:1234 http://localhost:8080
```

이 경우 `curl`이 자동으로 `Basic` + Base64 인코딩된 값을 헤더에 추가해줍니다.
