# 자바 객체지향 기초 실습 문제

## 📚 학습 목표
- 클래스와 객체의 개념 이해
- 생성자와 메서드 활용
- 캡슐화(접근 제어자) 적용
- 객체 간의 관계 설계

---

## 🎯 문제 1: 학생 정보 관리 시스템

### 요구사항
**Student 클래스**를 만들어 학생 정보를 관리하는 프로그램을 작성하세요.

### 클래스 설계
```java
public class Student {
   
}
```

### 구현해야 할 기능
1. **생성자 2개**
    - 모든 정보를 받는 생성자
    - 이름과 학번만 받는 생성자 (나이는 18, GPA는 0.0으로 초기화)

2. **정보 수정 메서드**
    - `updateAge(int newAge)`: 나이 수정 (유효성 검사 포함)
    - `updateGpa(double newGpa)`: GPA 수정 (유효성 검사 포함)

3. **비즈니스 메서드**
    - `displayInfo()`: 학생 정보를 출력
    - `isExcellent()`: GPA가 3.5 이상이면 true 반환
    - `updateGpa(double newGpa)`: GPA 업데이트 (0.0~4.0 범위 검사)

### 유효성 검사 규칙
- 나이: 15~100 사이
- GPA: 0.0~4.0 사이
- 범위를 벗어나면 기존 값 유지하고 경고 메시지 출력

### 실행 예시
```java
// StudentTest.java - 테스트 클래스
class StudentTest {
    public static void main(String[] args) {
        System.out.println("===== 학생 정보 관리 시스템 테스트 =====\n");

        // 학생 객체 생성 테스트
        Student student1 = new Student("강경미", 20241001, 20, 3.8);
        Student student2 = new Student("김멋사", 20241002);

        // 정보 출력 테스트
        student1.displayInfo();
        student2.displayInfo();

        // 우수학생 여부 테스트
        System.out.println(student1.name + " 우수학생 여부: " + student1.isExcellent());
        System.out.println(student2.name + " 우수학생 여부: " + student2.isExcellent());
        System.out.println();

        // GPA 업데이트 테스트
        student2.updateGpa(3.2);
        student2.updateGpa(5.0); // 잘못된 값

        // 나이 업데이트 테스트
        student1.updateAge(21);
        student1.updateAge(150); // 잘못된 값

        System.out.println("\n=== 업데이트 후 정보 ===");
        student1.displayInfo();
        student2.displayInfo();
    }
}
```

---

## 🎯 문제 2: 은행 계좌 시스템

### 요구사항
**BankAccount 클래스**를 만들어 은행 계좌를 관리하는 프로그램을 작성하세요.

### 클래스 설계
```java
public class BankAccount {
    
}
```

### 구현해야 할 기능
1. **생성자**
    - 계좌번호와 예금주 이름을 받는 생성자
    - 잔액은 0으로 초기화, totalAccounts 증가

2. **입출금 메서드**
    - `deposit(double amount)`: 입금 (양수 금액만 허용)
    - `withdraw(double amount)`: 출금 (잔액 부족 시 실패)
    - `transfer(BankAccount target, double amount)`: 계좌 이체

3. **조회 메서드**
    - `showBalance()`: 잔액 출력
    - `showAccountInfo()`: 계좌 정보 출력
    - `static showTotalAccounts()`: 전체 계좌 수 출력

### 비즈니스 규칙
- 입금/출금 금액은 양수여야 함
- 출금 시 잔액이 부족하면 실패 메시지 출력
- 모든 거래는 성공/실패 여부를 boolean으로 반환

```java
// BankAccountTest.java - 테스트 클래스
class BankAccountTest {
    public static void main(String[] args) {
        System.out.println("===== 은행 계좌 시스템 테스트 =====\n");
        
        // 계좌 생성
        BankAccount account1 = new BankAccount("123-456-789", "김은행");
        BankAccount account2 = new BankAccount("987-654-321", "이저축");
        
        // 전체 계좌 수 확인
        BankAccount.showTotalAccounts();
        System.out.println();
        
        // 입금 테스트
        account1.deposit(50000);
        account1.deposit(-1000); // 잘못된 금액
        account2.deposit(30000);
        System.out.println();
        
        // 출금 테스트
        account1.withdraw(20000);
        account1.withdraw(50000); // 잔액 부족
        System.out.println();
        
        // 계좌 이체 테스트
        account1.transfer(account2, 15000);
        account1.transfer(account2, 30000); // 잔액 부족
        System.out.println();
        
        // 계좌 정보 출력
        account1.showAccountInfo();
        account2.showAccountInfo();
    }
}
```
---

## 🎯 문제 3: 도서관 관리 시스템

### 요구사항
**Book 클래스**와 **Library 클래스**를 만들어 도서관 시스템을 구현하세요.

### Book 클래스
```java
public class Book {
    
}
```

### Library 클래스
```java
public class Library {
    
}
```

### 구현해야 할 기능

**Book 클래스:**
- 생성자: 제목, 저자, ISBN 받기 (대출 가능으로 초기화)
- `borrowBook()`: 도서 대출 (가능한 경우만)
- `returnBook()`: 도서 반납
- `showBookInfo()`: 도서 정보 출력

**Library 클래스:**
- 생성자: 빈 도서관 초기화
- `addBook(Book book)`: 도서 추가
- `findBookByTitle(String title)`: 제목으로 도서 검색
- `borrowBook(String title)`: 제목으로 도서 대출
- `returnBook(String title)`: 제목으로 도서 반납
- `displayAvailableBooks()`: 대출 가능한 도서 목록 출력
```java
// LibraryTest.java - 테스트 클래스
class LibraryTest {
    public static void main(String[] args) {
        System.out.println("===== 도서관 관리 시스템 테스트 =====\n");

        // 도서관 생성
        Library library = new Library();

        // 도서 생성 및 추가
        Book book1 = new Book("자바의 정석", "남궁성", "978-89-7914-726-8");
        Book book2 = new Book("이펙티브 자바", "조슈아 블로크", "978-89-6626-284-4");
        Book book3 = new Book("클린 코드", "로버트 마틴", "978-89-7914-725-1");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        System.out.println();

        // 대출 가능한 도서 출력
        library.displayAvailableBooks();

        // 도서 대출 테스트
        library.borrowBook("자바의 정석");
        library.borrowBook("자바의 정석"); // 이미 대출된 도서
        library.borrowBook("존재하지 않는 책"); // 없는 도서
        System.out.println();

        // 대출 후 도서 목록
        library.displayAvailableBooks();

        // 도서 반납 테스트
        library.returnBook("자바의 정석");
        library.returnBook("이미 있는 책"); // 없는 도서
        System.out.println();

        // 반납 후 도서 목록
        library.displayAvailableBooks();
    }
} 
```

---

## 🎯 문제 4: 자동차 관리 시스템 (종합 문제)

### 요구사항
**Car 클래스**를 만들어 자동차의 상태를 관리하는 프로그램을 작성하세요.

### 클래스 설계
```java
public class Car {
    
}
```

### 구현해야 할 기능
1. **생성자**
    - 브랜드, 모델, 연식, 최대연료량을 받는 생성자
    - 연료량은 최대의 절반, 주행거리는 0, 엔진은 꺼진 상태로 초기화

2. **엔진 제어**
    - `startEngine()`: 엔진 시동 (연료가 있어야 함)
    - `stopEngine()`: 엔진 정지

3. **운전 관련**
    - `drive(double distance)`: 주행 (엔진이 켜져있고 연료가 충분해야 함)
    - 연비: 10km/L로 계산
    - `refuel(double amount)`: 주유 (최대량 초과 불가)

4. **정보 조회**
    - `showCarStatus()`: 자동차 현재 상태 출력
    - `canDrive(double distance)`: 해당 거리 주행 가능 여부 확인 후 결과 출력

### 비즈니스 로직
- 엔진이 꺼져있으면 주행 불가
- 연료 부족 시 주행 중단 및 엔진 정지
- 주유 시 최대 용량 초과 불가


```java
// CarTest.java - 테스트 클래스
class CarTest {
    public static void main(String[] args) {
        System.out.println("===== 자동차 관리 시스템 테스트 =====\n");
        
        // 자동차 생성
        Car myCar = new Car("현대", "아반떼", 2023, 50.0);
        
        // 초기 상태 확인
        myCar.showCarStatus();
        
        // 엔진 시동 테스트
        myCar.startEngine();
        System.out.println();
        
        // 주행 가능 여부 확인
        myCar.canDrive(100);
        myCar.canDrive(300);
        System.out.println();
        
        // 주행 테스트
        myCar.drive(50);
        myCar.drive(100);
        myCar.drive(200); // 연료 부족
        System.out.println();
        
        // 현재 상태 확인
        myCar.showCarStatus();
        
        // 주유 테스트
        myCar.refuel(30);
        myCar.refuel(50); // 최대 용량 초과
        System.out.println();
        
        // 엔진 시동 후 주행
        myCar.startEngine();
        myCar.drive(100);
        System.out.println();
        
        // 최종 상태
        myCar.showCarStatus();
        
        // 엔진 정지
        myCar.stopEngine();
        myCar.canDrive(50); // 엔진 꺼진 상태에서 주행 시도
    }
}
```
---

## 📋 제출 가이드

### 파일 구조
```
src/hw2
├── problem1/
│   ├── Student.java
│   └── StudentTest.java
├── problem2/
│   ├── BankAccount.java
│   └── BankAccountTest.java
├── problem3/
│   ├── Book.java
│   ├── Library.java
│   └── LibraryTest.java
└── problem4/
    ├── Car.java
    └── CarTest.java
```

### 코딩 규칙
1. **캡슐화**: 모든 필드는 private으로 선언
2. **네이밍**: 카멜케이스 사용
3. **주석**: 메서드마다 기능 설명 주석 추가
4. **정보 출력**: 값을 반환하지 말고 메서드 내에서 직접 출력

### 
- ✅ 객체지향 설계 원칙 준수
- ✅ 캡슐화 적절한 적용
- ✅ 생성자와 메서드 올바른 구현
- ✅ 코드 가독성과 주석

---

