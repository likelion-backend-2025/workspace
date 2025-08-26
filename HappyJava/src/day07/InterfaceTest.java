package day07;

import java.util.Date;

public class InterfaceTest {
    public static void main(String[] args) {

        //실행되는 시점에....   나올 수 있는것!!  비행기, 새, 나비.
        나는것 aa = new 비행기();

        aa.날다();  //이 코드는, 인스턴스가 비행기든,나비든 새든 신경쓸필요없음.


        //별도의 기능을 사용함.
        Drawable.showInfo();

        //public class Photo implements Drawable,Resizable{
        //위처럼 정의되어 있어요~
        Photo photo = new Photo("b.png", 20,30);

        System.out.println(photo instanceof Photo);
        System.out.println(photo instanceof Drawable);
        System.out.println(photo instanceof Resizable);
//        System.out.println(photo instanceof Dog);

        Date date = new Date(2025,7,26);
        date.getDate();

        Drawable d = new Drawable() {
            @Override
            public void draw() {

            }

            @Override
            public void erase() {

            }
        };
    }
}
