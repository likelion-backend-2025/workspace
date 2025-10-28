package org.example.springdatajpa3;

import java.util.List;

public interface UserCustomRepository {
    void lionCustom();
    List<User> findUsersByName(String name);

    List<User> findUsersDynamically(String name, String email);

    //조회 -  이름, 내용, 날짜  이것을 실행하기 위해서 어떻게 구현해야할까요?
//    이름,  carami   -->
    // 내용, carami
    // 날짜, 111        이때 어떻게 처리할지!!
    //방법 1. 서비스 -  if(kind == 이름) repository.findByName()
    //방법 2. repostory 쪽에서 kind에 다라서 쿼리가 바뀐다면??
}
