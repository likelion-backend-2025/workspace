package day09;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapExam {
    public static void main(String[] args) {
        //list 에 50명의 person 이 저장되어있다.
        //이름이 강경미인 사람의 주소를 확인하고 싶다!! 라고 한다면???
        //키 : 값 이런 형태로 데이터가관리 된다.
        //어디서 본적 있나요?
        //이런 일들이 많을 것 같은 생각이 들까요?

        Map<String, String> phoneBook = new HashMap<>();

        phoneBook.put("kang","010-111-1111");
        phoneBook.put("kim","010-222-2222");
        phoneBook.put("hong","010-333-3333");

        System.out.println(phoneBook.get("kang"));

        Set<String> keys = phoneBook.keySet();
        System.out.println(keys);

        Iterator<String> iterator = keys.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            System.out.println(key +":"+phoneBook.get(key));
        }
        System.out.println("==================================");
        for(String key:keys){
            System.out.println(key +":"+phoneBook.get(key));
        }
        System.out.println("==================================");
        for (Map.Entry<String,String> entry:phoneBook.entrySet()){
            System.out.println(entry.getKey() +"::" +entry.getValue());
        }


    }
}
