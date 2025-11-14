package org.example.swaggerexam.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name="Shop", description = "쇼핑 관련 API")
@RestController
public class ShopController {
    @GetMapping
    public ResponseEntity<?> getShop(){
        return ResponseEntity.ok().build();
    }
}
