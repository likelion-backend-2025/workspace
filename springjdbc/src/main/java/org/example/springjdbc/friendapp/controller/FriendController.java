package org.example.springjdbc.friendapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.springjdbc.friendapp.domain.Friend;
import org.example.springjdbc.friendapp.service.FriendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    //친구추가   -- 폼 보여주세요(GET),   친구추가해주세요. (Post)
    @GetMapping("/add")
    public String addFriendForm(){
        return "friends/addform";
    }

    @PostMapping("/add")
    public String addFriend(){

        return "";
    }


    //친구목록보기
    //친구정보 수정
    //url 어떻게 사용하는게 좋을까요??
    //ex1) http://localhost:8080/addFriend
//    http://localhost:8080/updateFriend

    //ex2) http://localhost:8080/friends/add
    //ex) http://localhost:8080/friends/update


}
