package day08;

public class StringExam {
    public static void main(String[] args) {
        //String은 상속할 수 없다.
        //우리가 알고있는 객체를 생성하려면??
        String str = new String("abc");

        //조금 특별한 클래스로 아래처럼 문자열을 생성할 수 있다.
        String str2 = "abc";

        System.out.println(str2.charAt(0));
        char c = str2.charAt(1);
        System.out.println(c);

        String str3 = new String("abc");
        String str4 = "abc";

        System.out.println(str == str3);
        System.out.println(str2 == str4);

        str2 = str2.concat("def");
        System.out.println(str2);
        System.out.println(str4);

        System.out.println(str.equals(str3));
        String str5 = " abc";
        String str6 = "abc";

        System.out.println(str5.trim().equals(str6));

        System.out.println("a"+"b"+str+"1"+"str6"+3);



    }

}
