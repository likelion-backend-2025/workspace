package day09;


import java.util.*;

public class PersonDemo {
    public static void main(String[] args) {
        //Person을 여러명 저장하고 싶다.
//1. Person 객체 5개만 생성해주세요.
        Person person1 = new Person("kim", "900101", "010-1111-1111", "서울시 강남구");
        Person person2 = new Person("kang", "900102", "010-2222-2222", "서울시 성동구");
        Person person3 = new Person("lee", "900103", "010-3333-3333", "서울시 은평구");
        Person person4 = new Person("park", "900104", "010-4444-4444", "서울시 중구");
        Person person5 = new Person("jeong", "900105", "010-5555-5555", "서울시 서초구");

//        이렇게 생성한 person 객체들을 List, Set, Map에 각각 담아주세요.
//        map은 key를 idNumber로 하고, value 는 Person을 담는것으로 해주세요.

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);


        Set<Person> personSet = new HashSet<>();
        personSet.add(person1);
        personSet.add(person2);
        personSet.add(person3);
        personSet.add(person4);
        personSet.add(person5);


        Map<String, Person> personMap = new HashMap<>();
        personMap.put(person1.getIdNumber(), person1);
        personMap.put(person2.getIdNumber(),person2);
        personMap.put(person3.getIdNumber(), person3);
        personMap.put(person4.getIdNumber(),person4);
        personMap.put(person5.getIdNumber(),person5);

        //각 List, Set, Map 에서 특정 idNumber에 해당하는 Person객체를 찾고 싶다면??
        //list부터 구현해주세요.  900103
        //가장 간단한것은??
        Person findPerson = null;

        //Map에서 원하는 key에 해당하는 데이터를 찾는다면??
        findPerson = personMap.get("900103");

        System.out.println(findPerson);

        //List에서 원하는 데이터를 찾으려면??
        for(int i = 0 ; i < personList.size(); i++){
            Person person = personList.get(i);
            if(person != null && "900103".equals(person.getIdNumber())){
                findPerson = person;
                break;
            }
        }

        System.out.println(findPerson);
        //Set에서 찾기
        for(Person person:personSet){
            if(person != null && "900103".equals(person.getIdNumber())){
                findPerson = person;
                break;
            }
        }
        System.out.println(findPerson);





    }
}
