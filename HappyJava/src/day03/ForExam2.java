package day03;

public class ForExam2 {
    public static void main(String[] args) {
//        1-100까지 짝수의 합을 구해보세요. (for 반복문 이용)
//        문제는 항상 작게 만들어보는 연습!!
//        1부터 100까지 출력
//        짝수만 출력해볼까?
        //짝수만 더한다면? 누적
        int sum = 0;
        for(int i = 1; i <= 100; i++){
            if(i % 2 == 0) {
//                System.out.println(i);
                sum += i;
            }
        }
        System.out.println("1부터 100까지의 짝수의 합은 : " +sum);
    }
}
