package day06;

public class Exam03 {
    public static void main(String[] args) {

        //비슷한것들끼리만 가능 (기본 - 숫자, boolean)
        //객체들사이의 형변환은 어떤 조건들 사이에서만 가능할까요?
        //상속 관계에 있었을 때만 가능!!
        Parent1 p = new Child1();
        //형변환!!!  -  작은 그릇에서 큰그릇으로 바꿀때 (묵시적형변환가능)
        //큰그릇 - 부모               작은그릇 - 자식
        Object obj = p;  //묵시적 형변환 일어남.

        p =(Parent1) obj; // 더 큰 그릇에서 작은 그릇으로 옮겨담을때 오류!!

        Child1 c =(Child1) p;  //가능하다.

        Parent1 pp = new Parent1();

        //형변환시에는 인스턴스가 중요!!!
        if(pp instanceof  Child1) {
            Child1 cc = (Child1) pp;  // 이것이 실행되면 어떤일이 벌어질까요?
        }

    }
}
