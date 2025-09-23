package day08;

public class StringBuilderExample {
    public static void main(String[] args) {
        // StringBuilder 사용
        StringBuilder sb = new StringBuilder("Hello");

        // 문자열 추가
        sb.append(" World");
        sb.append(" Java").append(" Programming"); // 메소드 체이닝
        System.out.println(sb); // Hello World Java Programming

        // 삽입
        sb.insert(6, "Beautiful ");
        System.out.println(sb); // Hello Beautiful World Java Programming

        // 삭제
        sb.delete(6, 16); // 6번째부터 16번째 전까지
        System.out.println(sb); // Hello World Java Programming

        // 역순
        sb.reverse();
        System.out.println(sb);

        // 성능 비교
        long start = System.currentTimeMillis();

        // String 연결 (비효율적)
        String str = "";
        for(int i = 0; i < 10000; i++) {
            str += i;
        }
        System.out.println("String: " + (System.currentTimeMillis() - start) + "ms");

        // StringBuilder (효율적)
        start = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder();
        for(int i = 0; i < 10000; i++) {
            sb2.append(i);
        }
        System.out.println("StringBuilder: " + (System.currentTimeMillis() - start) + "ms");


        // StringBuilder (효율적)
        start = System.currentTimeMillis();
        StringBuffer sb3  = new StringBuffer();
        for(int i = 0; i < 10000; i++) {
            sb3.append(i);
        }
        System.out.println("StringBuffer: " + (System.currentTimeMillis() - start) + "ms");

    }
}
