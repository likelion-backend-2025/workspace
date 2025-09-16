package net.likelion.demo.repository;

import lion.common.DBUtil;
import net.likelion.demo.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public boolean addProduct(ProductDTO product) {
        boolean result = false;
        String sql = "insert into products (name,price,reg_date) values (?,?,now())";
        try(
                //접속
                Connection conn = DBUtil.getConnection();
                //쿼리작성
                PreparedStatement ps = conn.prepareStatement(sql);

        ) {
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            //실행
            int count = ps.executeUpdate();
            if (count > 0) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int addProductAndGetId(ProductDTO product) {
        return 0;
    }

    @Override
    public boolean updateProduct(ProductDTO product) {
        return false;
    }

    @Override
    public boolean deleteProduct(ProductDTO product) {
        return false;
    }

    @Override
    public List<ProductDTO> getProducts() {
        return List.of();
    }

    @Override
    public ProductDTO getProduct(int id) {
        return null;
    }
}
