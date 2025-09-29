### 실습 문제: 동시 카운터

#### 목표
- Java의 `Thread`와 `Runnable` 인터페이스를 사용하여 멀티스레딩을 구현합니다.
- 두 개의 스레드를 사용하여 동시에 숫자를 세고, 각 스레드의 작업을 콘솔에 출력합니다.

#### 요구사항
1. **스레드 구현**:
    - `IncrementCounter` 클래스를 구현하여, 1부터 100까지 숫자를 증가시키면서 각 숫자를 콘솔에 출력합니다.
    - `DecrementCounter` 클래스를 구현하여, 100부터 1까지 숫자를 감소시키면서 각 숫자를 콘솔에 출력합니다.
    - 각 클래스는 `Runnable` 인터페이스를 구현해야 합니다.

2. **스레드 실행**:
    - 메인 메소드에서 `IncrementCounter`와 `DecrementCounter`를 각각의 스레드로 실행합니다.
    - 각 스레드는 시작되자마자 자신의 카운트 작업을 시작해야 합니다.

3. **출력**:
    - 각 스레드의 출력은 다음 형식을 따라야 합니다: `Increment: {number}` 또는 `Decrement: {number}`.
    - 스레드가 숫자를 출력할 때마다 약간의 시간 지연(`sleep`)을 둡니다. 이는 `Thread.sleep()`을 사용하여 구현하며, 지연 시간은 0~10밀리초 사이의 무작위 시간입니다.

4. **동기화 및 경합 조건 처리**:
    - 이 기본 실습에서는 각 스레드가 독립적으로 동작하므로, 동기화 메커니즘은 필요하지 않습니다.
    - 출력 순서에 대한 일관성은 보장되지 않으며, 실행할 때마다 결과가 달라질 수 있습니다.

#### 예상되는 결과
- 프로그램을 실행할 때마다 "Increment" 스레드와 "Decrement" 스레드가 동시에 실행되며, 출력 순서는 실행할 때마다 달라집니다.
- 숫자가 순차적으로 증가하고 감소하는 것을 볼 수 있지만, 두 스레드의 출력이 서로 섞여 나타납니다.

#### 작성할 클래스
- `IncrementCounter.java`: 숫자를 1에서 100까지 증가시키는 스레드 구현 파일.
- `DecrementCounter.java`: 숫자를 100에서 1까지 감소시키는 스레드 구현 파일.
- `CounterApp.java`: 메인 메소드를 포함하며, 두 스레드를 생성하고 실행하는 메인 클래스 파일.

```java
public class CounterApp {
    public static void main(String[] args) {
        Thread incrementThread = new Thread(new IncrementCounter());
        Thread decrementThread = new Thread(new DecrementCounter());

        incrementThread.start();
        decrementThread.start();
    }
}
```