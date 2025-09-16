package net.likelion.demo.repository;

import net.likelion.demo.dto.ProductDTO;

import java.util.List;

public interface ProductDAO {
    public boolean addProduct(ProductDTO product);
    public int addProductAndGetId(ProductDTO product); //이해되셨어요??
    public boolean updateProduct(ProductDTO product);
    public boolean deleteProduct(ProductDTO product);
    public List<ProductDTO> getProducts();
    public ProductDTO getProduct(int id);

    //입력이 될때 아이디가 자동으로 입력되었어요.

    //트랜잭션
    //게시글 - id, 제목, 내용, user, 날짜     --   사진 3장 - 사진테이블  -  파일명, 경로,
    //게시글이 저장되면 자동으로 생성된 id 값을 얻어와야. 다음 사진의 정보를 저장할때 해당 게시글id를 저장할 수 있다.

}
