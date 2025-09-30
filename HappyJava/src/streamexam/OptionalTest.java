package streamexam;

import java.util.Optional;

class User{
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class OptionalTest {
    public User findUser(int id){
        if(id == 1){
            return new User("carami");
        }
        return null;
    }

    public Optional<User> findUserOptional(int id){
        if(id == 1){
            return Optional.of(new User("carami"));
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        OptionalTest test = new OptionalTest();

        User findUser = test.findUser(2);
        //혹시나 findUser가 null일 경우에 대비한 코드가 필요했다!!!
        if(findUser != null) {
            System.out.println(findUser.getName());
        }else{
            System.out.println("사용자를 찾을 수 없습니다.");
        }
//Optional을 사용하면 null 처리 같은것들을 잊어버리는 실수를 방지하고,
//     값이 있을때와, 없을때 각 각 처리를 달리한다든지. 이런 로직들을 이용해서 더 명확하게 구현이 가능!!!

        System.out.println("====== Optional 방식  ===============");
        //사용방법이 여러개..  null이 아니라면 앞에 람다식이 실행되고, 널이라면 뒷쪽것을 실행해요.
        test.findUserOptional(2).ifPresentOrElse(
                user -> System.out.println("사용자이름 : "+user.getName()),
                ()-> System.out.println("사용자를 찾을 수 없습니다.")
        );

// 가장 단순하기는 하나..  Optional이 가진 장점을 사용할 수 없다.   없으면 오류발생!!!
        User user = test.findUserOptional(1).get();  //그냥 꺼내줘..

        System.out.println("============orElse=================");
        User guest = test.findUserOptional(2).orElse(new User("guest"));
        System.out.println(guest.getName());

        System.out.println("============ifPresent=================");
        //값이 존재할 때만 뭔가 하고 싶다..  ifPresent
        test.findUserOptional(1).ifPresent(u -> System.out.println("환영합니다. "+ u.getName()));



    }
}
