## 문제 1: 기본적인 인터페이스 구현

### 요구사항

1. `Printer` 인터페이스를 정의한다.
   - 메서드: `void print(String message)`
2. `Printer` 인터페이스를 구현하는 `ConsolePrinter` 클래스를 만든다.
   - `print(String message)` 메서드에서 전달받은 메시지를 콘솔에 출력한다.
3. `main` 메서드에서 `Printer` 타입 변수에 `ConsolePrinter` 객체를 할당하고, `print` 메서드를 호출해 메시지를 화면에 출력해보자.

### 포인트

- 인터페이스 선언과 구현 연습
- 인터페이스 타입으로 구현 클래스를 참조하는 기본 패턴 익히기

---

## 문제 2: 다중 구현 연습

### 요구사항

1. `Movable` 인터페이스를 정의한다.
   - 메서드: `void move(int x, int y)`
2. `Drawable` 인터페이스를 정의한다.
   - 메서드: `void draw()`
3. `GameObject` 클래스를 만들고, `Movable`, `Drawable`를 동시에 구현한다.
   - `int x, y` 필드를 갖는다.
   - `move(int x, int y)` 메서드 구현 시: x,y 좌표를 갱신
   - `draw()` 메서드 구현 시: 현재 좌표를 "객체가 (x,y)에 그려졌습니다." 형태로 출력
4. `main` 메서드에서 `GameObject` 객체를 만들고, `move`와 `draw`를 호출해 동작을 확인한다.

### 포인트

- 인터페이스 다중 구현 경험
- 한 클래스에서 여러 기능(이동, 그리기)을 인터페이스로부터 강제하는 구조 이해

---

## 문제 3: 인터페이스를 통한 기능 확장

### 요구사항

1. `Calculator` 인터페이스를 정의한다.
   - 메서드: `int add(int a, int b)`, `int subtract(int a, int b)`
2. `Calculator`를 구현하는 `BasicCalculator` 클래스를 만든다.
   - 단순히 `+`, `-` 연산 결과를 반환
3. 기존 코드 수정 없이 기능을 확장하기 위해 `Multipliable` 인터페이스를 추가한다.
   - 메서드: `int multiply(int a, int b)`
4. `Multipliable`을 구현하는 `AdvancedCalculator` 클래스를 만든다.
   - `multiply` 메서드 구현
   - 추가로 `AdvancedCalculator`는 `Calculator`도 구현해 `add`, `subtract`도 가능하게 한다.
5. `main` 메서드에서 `Calculator` 타입으로 `BasicCalculator`와 `AdvancedCalculator`를 각각 사용해보고, `AdvancedCalculator`를 `Multipliable` 타입으로 사용해 `multiply`도 호출해본다.

### 포인트

- 인터페이스를 이용한 기능 확장과 다형성 체험
- 기존 코드(인터페이스)는 건드리지 않고 새 인터페이스와 클래스를 추가하여 기능을 확장하는 구조 이해

## 문제 4: 인터페이스 상속

### 요구사항

1. `Readable` 인터페이스 정의
   - 메서드: `String readContent()`
2. `Writable` 인터페이스 정의
   - 메서드: `void writeContent(String content)`
3. `ReadWritable` 인터페이스를 만들고, `Readable`과 `Writable`을 상속받는다.
4. `FileHandler` 클래스를 만들어 `ReadWritable` 구현
   - `readContent()` 메서드: "파일에서 내용을 읽습니다." 반환
   - `writeContent(String content)` 메서드: "파일에 내용을 썼습니다." 출력
5. `main`에서 `ReadWritable` 타입으로 `FileHandler` 객체를 다루며 `readContent()`, `writeContent()` 호출

### 포인트

- 인터페이스 상속을 통해 인터페이스의 기능 확장 연습
- `Readable`, `Writable`로 나눠진 기능을 상속해 `ReadWritable`로 통합하는 구조 이해

- 인터페이스 선언과 구현하는 방법
- 다중 구현을 통한 클래스의 다기능화
- 인터페이스로부터 강제되는 일관된 메서드 이름과 시그니처
- 인터페이스 타입 변수를 통해 구현 클래스를 유연하게 교체하는 방법
- 인터페이스 상속을 통한 기능 확장
