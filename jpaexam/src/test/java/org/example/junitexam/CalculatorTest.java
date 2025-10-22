package org.example.junitexam;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {
    Calculator calculator = null;
    @BeforeAll
    static void beforeAll() {
        System.out.println("테스트가 실행될 때 한 번 만 실행 -  전체적인 셋팅 등을 할 수 있음");
    }
    @AfterAll
    static void afterAll() {
        System.out.println("테스트가 전체 종료될때 한 번 만 실행 - 마무리 작업진행.");
    }

    @BeforeEach
    public void setup() {
        calculator =new Calculator();
        System.out.println("테스트 실행전..  beforeEach");
    }
    @AfterEach
    public void teardown() {
        calculator = null;
        System.out.println("테스트 실행후...  afterEach");
    }
    @Test
    @Order(4)
    void add() {
        System.out.println("add 테스트 실행");

        int result = calculator.add(1, 2);
        assertEquals(3, result);
    }

    @Test
    @DisplayName("빼기 테스트")
    @Order(2)
    void subtract() {
        System.out.println("subtract 테스트 실행");
        int result = calculator.subtract(5, 2);
        assertEquals(3, result);
    }

    @Test
    @DisplayName("곱하기 테스트")
    @Order(3)
    void multiply() {
        assertEquals(10,calculator.multiply(5, 2));
        assertEquals(20,calculator.multiply(5, 4));
    }
}
