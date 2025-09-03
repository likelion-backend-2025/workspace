package day10;

import java.time.*;

public class PeriodDurationExample {
    public static void main(String[] args) {
        LocalDate birth = LocalDate.of(2000, 1, 1);
        LocalDate today = LocalDate.now();

        Period age = Period.between(birth, today);
        System.out.printf("나이: %d년 %d개월 %d일%n",
                age.getYears(), age.getMonths(), age.getDays());


        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 30);

        Duration workTime = Duration.between(start, end);
        System.out.println("근무 시간: " + workTime.toHours() + "시간 " +
                (workTime.toMinutes() % 60) + "분");


        //에포크(epoch): 1970-01-01T00:00:00Z (UTC) 기준
        Instant instant = Instant.now();
        System.out.println("타임스탬프: " + instant);
        System.out.println("에포크 초: " + instant.getEpochSecond());

//        LocalDateTime - 사람이 읽는 날짜, 시간 (타임존 없음)
//        ZonedDateTime - 사람이 읽는 날짜.시간 + 타임존 (지역반영)
//        Instant -  컴퓨터가 읽을 목적. 항상 UTC기준 (서버/DB/로그 기록등에 자주쓰임)

    }
}
