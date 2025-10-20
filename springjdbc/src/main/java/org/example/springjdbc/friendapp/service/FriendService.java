package org.example.springjdbc.friendapp.service;

import lombok.RequiredArgsConstructor;
import org.example.springjdbc.friendapp.repository.FriendRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;


    //서비스와 레파지토리의 구분이 모호??
    //레파지토리는 DB에 저장 조회 삭제 수정..
    //비지니스로직을 레파지토리가 가지면 안됨..
    //회원가입 -  14세 이상만 회원으로 받겠다.
    // 서비스 -  회원가입해줘()
    // 레파지토리에서는 - 회원정보저장()
    //제가 왜 강조하고 있을까요??
    //우리가 하는 예제는 특별한 비지니스가 있을까요??
    //대체로 서비스에서 그냥 repository만 이용하고 있는 경우가 많아요.  그렇다보니..
    //여러분이 자꾸 이걸 꼭 써야하나요???
    //실제 좀 더 복잡한 어플리케이션에서는 가장 중요한 부분이 서비스가 될거예요.

}
