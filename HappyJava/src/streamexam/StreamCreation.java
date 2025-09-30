package streamexam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamCreation {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "Python", "JavaScript");
        System.out.println(list);
        System.out.println("=".repeat(50));
        list.stream()
                .filter(s -> s.startsWith("J"))
                .forEach(s-> System.out.println(s));
        
        System.out.println("=".repeat(50));

        //처리가 좀 헷갈리시는분들은 stream을 쓰지 않았다면 어떻게 구현하지??
        // .filter(s -> s.startsWith("J"))
        List<String> filterResult = new ArrayList<>();
        for(String str : list){
            if(str.startsWith("J")){
                filterResult.add(str);
            }
        }
        //.forEach(s-> System.out.println(s));
        for(String str : filterResult){
            System.out.println(str);
        }

        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);

        for(int i = 0 ; i<list.size() ; i++) {
            System.out.println(list.get(i));
        }

        //이미 사용된 stream 을 다시 쓸 수 있을까요???
        System.out.println("=".repeat(50));
        stream.forEach(System.out::println);

//
    }

}
