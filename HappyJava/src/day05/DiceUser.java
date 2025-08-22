package day05;

public class DiceUser {
    public static void countNum(Dice dice, int rollTimes, int eye){
        int totalCount=0;
        for(int i = 0; i < rollTimes; i++){
            dice.roll();
//            System.out.println(dice.eye);
            if(dice.eye == eye)
                totalCount++;

        }
        System.out.printf("주사위를 %d번 굴려 %d 나온 횟수 : %d\n",rollTimes,eye,totalCount);
    }
    public static void main(String[] args) {
        Dice dice = new Dice();

        dice.roll(); //주사위를 굴렸어요.

        System.out.println("주사위를 굴려서 나온 눈 : "+dice.eye);


//        주사위를 10번 굴려서 3이 몇번 나오는지 알고 싶어요.
        int totalCount=0;
        for(int i = 0; i < 10; i++){
            dice.roll();
//            System.out.println(dice.eye);
            if(dice.eye == 3)
                totalCount++;
        }
        System.out.println("주사위를 10번 굴려서 3이 나온 횟수 : "+totalCount);

        //같은 코드가 계속 중복되는것 같다~~ 라는 느낌이 들면....
//        주사위를 100번 굴려서 5이 몇번 나오는지 알고 싶어요.
        for(int i = 0; i < 100; i++){
            dice.roll();
//            System.out.println(dice.eye);
            if(dice.eye == 5)
                totalCount++;
        }
        System.out.println("주사위를 100번 굴려서 5이 나온 횟수 : "+totalCount);

//        이렇게 다른 경우에도 계속 값을 구하고 싶다면???
        DiceUser.countNum(dice,100,2);
        DiceUser.countNum(dice,200,6);




    }
}
