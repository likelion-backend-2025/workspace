package org.example.springjdbc.friendapp.service;

import lombok.RequiredArgsConstructor;
import org.example.springjdbc.friendapp.domain.Friend;
import org.example.springjdbc.friendapp.repository.FriendRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    //친구를 추가해주세요.
    @Transactional
    public Friend addFriend(Friend friend){
        //친구를 추가하기 위해서 조건이 있다라면...  여기서 실행해서 조건에 만족하면 DB저장하고
        //그렇치 않다면, 친구추가를 하지 않을거예요.
        return friendRepository.save(friend);  //save 메서드가 실행되고 리턴되는 객체는 id 값이 채워져있어요.
    }

    //친구 리스트 보여주세요.
    @Transactional(readOnly = true)
    public Iterable<Friend> getFriends(){
        return friendRepository.findAll();
    }

    //페이징 처리된 친구 리스트 보기
    @Transactional(readOnly = true)
    public Page<Friend> getFriends(Pageable pageable){
        return friendRepository.findAll(pageable);
    }

    //친구정보 상세조회
    @Transactional(readOnly = true)
    public Friend getFriendById(Long id){
        return friendRepository.findById(id).orElseThrow();
    }
    //친구정보수정
    @Transactional
    public Friend updateFriend(Friend friend){
        //save() 메서드는 엔티티에 id 로 지정한 필드의 값이 존재하면, update 쿼리를 생성함.
        //id 필드가 null이라면 insert 쿼리 생성!!!




        return friendRepository.save(friend);
    }

    //친구삭제
    @Transactional
    public void deleteFriendById(Long id){
        friendRepository.deleteById(id);
    }


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
