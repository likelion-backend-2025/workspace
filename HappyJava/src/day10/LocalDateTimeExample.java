package day10;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeExample {
    public static void main(String[] args) {
        // 현재 날짜/시간
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("날짜: " + date);        // 2024-04-10
        System.out.println("시간: " + time);        // 15:30:45.123
        System.out.println("날짜시간: " + dateTime); // 2024-04-10T15:30:45.123

        // 특정 날짜/시간 생성
        LocalDate birthday = LocalDate.of(2000, 1, 1);
        LocalTime noon = LocalTime.of(12, 0);
        LocalDateTime appointment = LocalDateTime.of(2024, 12, 25, 18, 30);

        // 날짜 연산
        LocalDate tomorrow = date.plusDays(1);
        LocalDate lastWeek = date.minusWeeks(1);
        LocalDate nextMonth = date.plusMonths(1);

        // 날짜 정보 추출
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        System.out.printf("%d년 %s %d일 %s%n",
                year, month, dayOfMonth, dayOfWeek);

        // 포맷팅
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
        String formatted = dateTime.format(formatter);
        System.out.println("포맷팅: " + formatted);

        // 파싱
        LocalDate parsed = LocalDate.parse("2024-04-10");
        LocalDateTime parsedDT = LocalDateTime.parse("2024-04-10T15:30:45");


    }
}
