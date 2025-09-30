package streamexam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
//        stream.forEach(System.out::println);

//        각 컬렉션에서 stream을 생성해보고  filter 혹은 forEach 를 적용해보세요.

//
        System.out.println("====정적메서드 사용===================");
        // Stream.of() 사용
        Stream<Integer> numberStream = Stream.of(1, 2, 3, 4, 5);
        numberStream
                .filter(number -> number % 2 == 0)
        .forEach(System.out::println);

        // Stream.iterate() - 무한 스트림 생성
        Stream<Integer> iterateStream = Stream.iterate(0, n -> n + 2)
                .limit(10);
        iterateStream.forEach(System.out::println);

        System.out.println("=".repeat(50));
        Stream<Double> randomStream = Stream.generate(Math::random)
                .limit(5);

        randomStream.forEach(System.out::println);

        System.out.println("=".repeat(50));


        try {
            Stream<String> lines = Files.lines(Paths.get("output.txt"));
            lines.forEach(System.out::println);
            lines.close(); // 파일 스트림은 반드시 닫아야 함
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
