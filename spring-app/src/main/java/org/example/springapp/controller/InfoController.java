package org.example.springapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class InfoController {
    @GetMapping("/")
    public String index() {
        return "Hello lion";
    }

    @GetMapping("/info")
    public String info() throws  Exception{
        String hostName = InetAddress.getLocalHost().getHostName();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return "Server: " + hostName + " | Time: " + time;
    }
}
