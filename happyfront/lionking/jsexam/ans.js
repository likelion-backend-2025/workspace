// ===== 문제 1: 변수와 기본 연산 =====
let myName = "김철수";
let myAge = 25;
let isStudent = true;

let introduction =
  "안녕하세요, 저는 " +
  myName +
  "이고 " +
  myAge +
  "살입니다. " +
  (isStudent ? "학생입니다." : "학생이 아닙니다.");
console.log(introduction);

// ===== 문제 2: 함수 만들기 =====
// 1. 덧셈 함수
function add(a, b) {
  return a + b;
}

// 2. 원의 넓이 계산 함수
function getCircleArea(radius) {
  return 3.14 * radius * radius;
}

// 3. 인사 함수
function greet(name) {
  return "안녕하세요, " + name + "님!";
}

// 함수 호출 테스트
console.log(add(10, 5)); // 15
console.log(getCircleArea(3)); // 28.26
console.log(greet("영희")); // 안녕하세요, 영희님!

// ===== 문제 3: 객체 다루기 =====
let student = {
  name: "박민수",
  age: 17,
  grade: 2,
  favoriteSubjects: ["수학", "과학", "영어"],
};

// 1. 학생의 이름 출력
console.log("학생 이름:", student.name);

// 2. 나이 1살 증가
student.age = student.age + 1;
// 또는 student.age++;

// 3. 새로운 과목 추가
student.favoriteSubjects.push("체육");

// 4. 학생 정보 모두 출력
console.log("학생 정보:", student);

// ===== 문제 4: 배열 조작 =====
// 1. 과일 배열 생성
let fruits = ["사과", "바나나", "오렌지", "포도", "딸기"];

// 2. 첫 번째와 마지막 요소 출력
console.log("첫 번째 과일:", fruits[0]);
console.log("마지막 과일:", fruits[fruits.length - 1]);

// 3. 새로운 과일 추가
fruits.push("키위");

// 4. 배열 길이 출력
console.log("과일 개수:", fruits.length);

// 5. 특정 과일 존재 확인
let searchFruit = "바나나";
let found = false;
for (let i = 0; i < fruits.length; i++) {
  if (fruits[i] === searchFruit) {
    found = true;
    break;
  }
}
console.log(searchFruit + "이(가) 있나요?", found);

// 또는 간단하게 (반복문 없이):
// console.log("바나나가 있나요?", fruits.indexOf("바나나") !== -1);

// ===== 문제 5: 조건문 활용 =====
function getGrade(score) {
  if (score >= 90) {
    return "A";
  } else if (score >= 80) {
    return "B";
  } else if (score >= 70) {
    return "C";
  } else if (score >= 60) {
    return "D";
  } else {
    return "F";
  }
}

// 테스트
console.log("95점:", getGrade(95)); // A
console.log("87점:", getGrade(87)); // B
console.log("73점:", getGrade(73)); // C
console.log("65점:", getGrade(65)); // D
console.log("45점:", getGrade(45)); // F

// ===== 문제 6: switch문 활용 =====
function getDayName(dayNumber) {
  switch (dayNumber) {
    case 1:
      return "월요일";
    case 2:
      return "화요일";
    case 3:
      return "수요일";
    case 4:
      return "목요일";
    case 5:
      return "금요일";
    case 6:
      return "토요일";
    case 7:
      return "일요일";
    default:
      return "잘못된 요일 번호입니다";
  }
}

// 테스트
for (let i = 1; i <= 8; i++) {
  console.log(i + "번:", getDayName(i));
}

// ===== 문제 7: 종합 문제 (계산기) =====
let calculator = {
  add: function (a, b) {
    return a + b;
  },
  subtract: function (a, b) {
    return a - b;
  },
  multiply: function (a, b) {
    return a * b;
  },
  divide: function (a, b) {
    if (b === 0) {
      return "0으로 나눌 수 없습니다";
    }
    return a / b;
  },
};

function printCalculation(a, operator, b, result) {
  console.log(a + " " + operator + " " + b + " = " + result);
}

// 계산기 테스트
printCalculation(10, "+", 5, calculator.add(10, 5));
printCalculation(10, "-", 5, calculator.subtract(10, 5));
printCalculation(10, "*", 5, calculator.multiply(10, 5));
printCalculation(10, "/", 5, calculator.divide(10, 5));
printCalculation(10, "/", 0, calculator.divide(10, 0));

// ===== 문제 8: 실전 응용 (도서관 시스템) =====
let library = {
  books: [
    { title: "해리포터", author: "J.K. 롤링", year: 1997, available: true },
    { title: "반지의 제왕", author: "톨킨", year: 1954, available: false },
    { title: "1984", author: "조지 오웰", year: 1949, available: true },
    { title: "어린왕자", author: "생텍쥐페리", year: 1943, available: true },
  ],

  // 책 제목으로 검색
  searchByTitle: function (title) {
    for (let i = 0; i < this.books.length; i++) {
      if (this.books[i].title === title) {
        return this.books[i];
      }
    }
    return "책을 찾을 수 없습니다";
  },

  // 대여 가능한 책들의 제목 출력
  getAvailableBooks: function () {
    console.log("대여 가능한 책들:");
    for (let i = 0; i < this.books.length; i++) {
      if (this.books[i].available === true) {
        console.log("- " + this.books[i].title);
      }
    }
  },

  // 새 책 추가
  addBook: function (title, author, year) {
    let newBook = {
      title: title,
      author: author,
      year: year,
      available: true,
    };
    this.books.push(newBook);
    console.log("새 책이 추가되었습니다: " + title);
  },
};

// 도서관 시스템 테스트
console.log("=== 도서관 시스템 테스트 ===");
console.log("해리포터 검색:", library.searchByTitle("해리포터"));
library.getAvailableBooks();
library.addBook("자바스크립트 완벽 가이드", "데이비드 플래너건", 2020);
console.log("전체 책 목록:", library.books);
