package streamexam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExam01 {
    public static void main(String[] args) {
        int[] iarr = {1,2,4,5,6,7,8,0};

        //나는 이 배열에서 짝수만 출력하고 싶어요.  (스트림없이.)
        for(int a:iarr){
            if(a%2==0){
                System.out.println(a);
            }
        }
        System.out.println("=".repeat(50));
        //스트림 이용해서
        Arrays.stream(iarr)
                .filter(i -> i % 2 == 0 )
//                .forEach(i -> System.out.println(i));
                .forEach(System.out::println);




        List<String> words = Arrays.asList("Apple", "Banana", "Cherry", "Apple", "Cherry", "Date");
        //words 에서 글자수가 5개 이상인 것만 필터링하고,
        // 중복을 제거하고 새로운 리스트를 얻고 싶다면?
        // 리스트.contains(단어)  --   리스트에 단어가 있다면 true 리턴
        List<String> resultWords = new ArrayList<>();
        for(String word:words){
            if(word.length() >= 5){
                if(resultWords.contains(word)) continue;
                resultWords.add(word);
            }
        }
        System.out.println(resultWords);
        System.out.println("=".repeat(50));


        //스트림에서는 distinct()   메서드 제공!!
        List<String> resultWords2 = words.stream()
                .filter(word -> word.length() >= 5)
                .distinct()
                .toList();   //불변리스트로 리턴

        System.out.println(resultWords2);
//        resultWords2.add("test");

        System.out.println("=".repeat(50));

        List<String> resultWords3 = words.stream()
                .filter(word -> word.length() >= 5)
                .distinct()
                .collect(Collectors.toList());  // 가변리스트로 결과반환

        System.out.println(resultWords3);
        resultWords3.add("test");

        System.out.println(resultWords3);
    }
}
