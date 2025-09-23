package net.likelion.demo.service;

import net.likelion.demo.dto.ProductDTO;
import net.likelion.demo.repository.ProductDAO;
import net.likelion.demo.repository.ProductDAOImpl;

import java.util.List;

public class ProductTest {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAOImpl();
//        boolean resultFlag = productDAO.addProduct(new ProductDTO("book", 20000));
//        System.out.println(resultFlag);


//        int id = productDAO.addProductAndGetId(new ProductDTO("phone", 300000));
//        System.out.println("product 이 저장되면서 자동 생성한 id : "+id);
        //게시글저장 { 1. 게시글 테이블에 글 저장,  2. 첨부된 이미지 저장 (게시글이 저장되면서 생성된 id 필요) }

        //하나의 서비스(기능) 이 실행 될 때  DAO 가 여러번 호출 될 수 있음!!

        //커밋 롤백 까지 같이 이해해 보세요.

        ProductDTO product = productDAO.getProduct(1);
//        System.out.println(product);

        List<ProductDTO> products = productDAO.getProducts();

        for(ProductDTO productDTO : products){
            System.out.println(productDTO);
        }

    }
}
