package day12;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOExam2 {
    //필수!!
    //사용자의 정보(이름, 전화번호, 주소) 를 입력 받아서 파일에 저장하는 메서드
    public void saveUserInfo(){
        String name = null;
        String phoneNumber = null;
        String address = null;

        try(
            PrintWriter pw = new PrintWriter(new FileOutputStream("src/day12/userInfo.txt",true));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                ){
            for(int count = 0; count < 3; count++){
                System.out.println("이름 : ");
                name = br.readLine();
                System.out.println("전화번호 : ");
                phoneNumber = br.readLine();
                System.out.println("주소 : ");
                address = br.readLine();

                pw.println(name + " "+phoneNumber + " "+address);
            }

            pw.close();
            System.out.println("사용자 정보가 src/day12/userInfo.txt 에 저장되었습니다.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //파일로부터 읽어와서 사용자의 정보를 출력하는 메서드
    public void printUserInfo(){
        String userInfo = null;

        try(
                BufferedReader br = new BufferedReader(new FileReader("src/day12/userInfo.txt"))
                ){
            System.out.println();
            System.out.println("src/day12/userInfo.txt 의 내용은 .....");
            while((userInfo = br.readLine()) != null){
                System.out.println(userInfo);
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    //collection의 값을 읽어와서 파일에 저장
    public void saveUsersToFile(List<Person> personList){
        try( PrintWriter pw = new PrintWriter(new FileOutputStream("src/day12/userInfo.txt",true))
                ){
            for(Person person : personList){
                pw.printf("%s %s %s\n",person.getName(),person.getPhoneNumber(),person.getAddress());
                //Pesrson 의 toString을 우리가 출력하기 원하는 형태로 오버라이딩해도 편하게 사용가능하겠죠?
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    //파일의 값을 읽어서 collection에 저장




    //선택!!
//    5건정도를 입력 받아서 Collection 에 저장하는 메서드
    //
    public void saveUserInfo(List<Person> personList){
        String name = null;
        String phoneNumber = null;
        String address = null;
        try(
                 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            for(int count = 0; count < 2; count++){
                System.out.println("이름 : ");
                name = br.readLine();
                System.out.println("전화번호 : ");
                phoneNumber = br.readLine();
                System.out.println("주소 : ");
                address = br.readLine();

                personList.add(new Person(name,phoneNumber,address));
            }


            System.out.println("사용자 정보가 src/day12/userInfo.txt 에 저장되었습니다.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    Collection 의 내용을 파일에 저장하는 메서드를 만들어 보세요.  (??)

    //파일의 내용을 읽어서 Collection으로 저장하는 메서드.
    public void readUsersFromFile(List<Person> personList, String fileName){
        String userInfo = null;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){

            while((userInfo =  br.readLine()) != null){
                String[] info = userInfo.split(" ",3);
                Person person = new Person(info[0],info[1],info[2]);
                personList.add(person);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }



    //만들어 볼까요?
    public static void main(String[] args) {
        IOExam2 io = new IOExam2();
//        io.saveUserInfo();
//        io.printUserInfo();

        List<Person> personList = new ArrayList<>();
//        personList.add(new Person("강경미","010-111-2222","경기도 고양시"));
//        personList.add(new Person("홍길동","010-666-6666","경기도 양주시"));
//        personList.add(new Person("김둘리","010-777-7777","경기도 광명시"));
//        io.saveUserInfo(personList);
//
//        System.out.println(personList.size());

        //리스의 정보를 파일에 저장 하는 메서드 만들 수 있을 까요???

//        io.saveUsersToFile(personList);

        io.readUsersFromFile(personList, "src/day12/userInfo.txt" );

        for (Person person : personList){
            System.out.println(person);
        }




    }
}
