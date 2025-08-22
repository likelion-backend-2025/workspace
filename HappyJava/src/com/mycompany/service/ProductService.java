// 파일: com/mycompany/service/ProductService.java
package com.mycompany.service;

import com.mycompany.model.Product;  // import 문 사용
import java.util.ArrayList;          // Java 표준 라이브러리 import
import java.util.*;                  // 와일드카드 사용 (권장하지 않음)

public class ProductService {
    private ArrayList<Product> products;

    public ProductService() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayAll() {
        for(Product p : products) {
            p.display();
        }
    }
}