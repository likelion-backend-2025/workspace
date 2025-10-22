package org.example.springjdbc.friendapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.springjdbc.friendapp.domain.Friend;
import org.example.springjdbc.friendapp.service.FriendService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

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
    public String addFriend(@ModelAttribute Friend friend){
        //서비스에게 이름과 이메일을 보내서 친구정보를 저장해 달라고 할꺼예요.
//         이때..  서비스에 이름과 이메일을 담은 Friend 객체를 전달할것인지.  name, email을 직접 전달할지..

        Friend saveFriend = friendService.addFriend(friend);
        System.out.println(saveFriend);


        return "redirect:/friends/list";  //임시로 저장되고 다시 친구추가폼을 요청함!!
    }


    //친구목록보기
    @GetMapping("/list")
//    public String listFriends(Model model, @RequestParam(name="page",required = false,defaultValue = "1") int page){
//
//
//        Pageable pageable = PageRequest.of(page -1, 3, Sort.by(Sort.Direction.DESC, "id"));
    public String listFriends(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Friend> friends = friendService.getFriends(pageable);
        model.addAttribute("friends", friends);

        return  "friends/list";
    }
    //친구상세보기
    @GetMapping("/{id}")
    public String detailFriend(@PathVariable("id") Long id, Model model){
        Friend friend = friendService.getFriendById(id);
        model.addAttribute("friend", friend);
        return "friends/detail";
    }
    //친구정보수정  수정 폼 보여주세요.
    @GetMapping("/edit/{id}")
    public String editFriendForm(@PathVariable("id") Long id, Model model){
        Friend friend = friendService.getFriendById(id);
        model.addAttribute("friend", friend);
        return "friends/editform";
    }

    //수정해주세요.
    @PostMapping("/edit")
    public String editFriend(@ModelAttribute Friend friend){
        System.out.println("editFriend"+friend);
        friendService.updateFriend(friend);
        return "redirect:/friends/list";
    }

    //친구삭제
    @GetMapping("/delete/{id}")
    public String deleteFriend(@PathVariable("id") Long id){
        friendService.deleteFriendById(id);
        return "redirect:/friends/list";
    }

    //친구정보 수정
    //url 어떻게 사용하는게 좋을까요??
    //ex1) http://localhost:8080/addFriend
//    http://localhost:8080/updateFriend

    //ex2) http://localhost:8080/friends/add
    //ex) http://localhost:8080/friends/update


}
