package com.mycompany;

import com.mycompany.model.Product;
import com.mycompany.service.ProductService;

public class Test {
    public static void main(String[] args) {
        ProductService service = new ProductService();
        service.addProduct(new Product("컵",2000));
        service.addProduct(new Product("book",30000));

        service.displayAll();
    }
}
