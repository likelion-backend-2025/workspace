package org.example.restexam.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restexam.dto.ProductDTO;
import org.example.restexam.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    //url --  http://localhost:8080/api/products  -- get
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }
    //url --  http://localhost:8080/api/products/{id}  -- get
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
    //url --  http://localhost:8080/api/products  -- post
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        System.out.println(productDTO.getName());
//        if(productDTO.getName() == null && "".equals(productDTO.getName()))

        return ResponseEntity.ok(productService.createProduct(productDTO));
    }
    //url --  http://localhost:8080/api/products/{id}  -- put
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id")Long id, @RequestBody ProductDTO productDTO) {
        System.out.println(productDTO.getName());
        System.out.println(id);
        productDTO.setId(id);
        return ResponseEntity.ok(productService.updateProduct(productDTO));
    }
    //url --  http://localhost:8080/api/products/{id}  -- delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(id+"상품 삭제^^");
    }
}
