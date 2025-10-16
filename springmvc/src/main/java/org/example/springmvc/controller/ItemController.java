package org.example.springmvc.controller;

import org.example.springmvc.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class ItemController {

    @GetMapping("/item/form")
    public String form(){
        System.out.println("form called");
        return "item_form";
    }

    @PostMapping("/item/add")
    public String add(@ModelAttribute Item item /*@RequestParam("name2") String name, @RequestParam("price2")int price*/){

        System.out.println(item.getName());
        System.out.println(item.getPrice());

//        System.out.println(name);
//        System.out.println(price);

//        return "redirect:/item/form";
        return "redirect:/item/list";
    }

    @GetMapping("/item/list")
    public String list(Model model){
        model.addAttribute("welcomeMessage","안녕하세요, 반갑습니다.");

        //나중에는 서비스를 통해서 얻어오게 될겁니다.
        List<Item> items = Arrays.asList(
                new Item("사과",2000),
                new Item("바나나", 1000),
                new Item("망고", 3000),
                new Item("수박", 30000)
        );

        model.addAttribute("items", items);

        return "item_list";
    }

}
