package day10;

import java.util.Calendar;

public class CalendarExam {
    public static void printCalendar(int year, int month){
        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,1);
        //시작요일
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.printf("========%d년  %d월========\n",year,month);
        System.out.println("일\t월\t화\t수\t목\t금\t토");

        //시작일 까지의 공백 출력
        for(int i = 1; i < firstDayOfWeek; i++){
            System.out.print("\t");
        }
        //날짜출력
        for(int day = 1; day <= lastDay; day++){
            System.out.printf("%d\t",day);

            //일주일마다 줄바꿈
            if((day + firstDayOfWeek -1) % 7 == 0)
                System.out.println();
        }
    }
    public static void main(String[] args) {
        printCalendar(2025,10);
    }
}
