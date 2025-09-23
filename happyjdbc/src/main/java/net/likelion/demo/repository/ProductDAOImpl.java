package net.likelion.demo.repository;


import net.likelion.demo.common.DBUtil;
import net.likelion.demo.dto.ProductDTO;

import java.sql.*;
import java.util.ArrayList;
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
        int id = 0;
        String sql = "insert into products (name,price,reg_date) values (?,?,now())";

        try(
                //접속
                Connection conn = DBUtil.getConnection();
                //쿼리작성
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ) {
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            //실행
            int count = ps.executeUpdate();
            if (count > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    id = rs.getInt(1);
                }
            }    else{
                System.out.println("상품등록에 실패했습니다.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id;
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
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from products";

        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs= ps.executeQuery();

            while(rs.next()){
                products.add(resultSetToProduct(rs));
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            DBUtil.close(conn,ps,rs);
        }



        return products;
    }

    @Override
    public ProductDTO getProduct(int id) {

        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from products where id = ?";

        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if(rs.next()) {
              product =  resultSetToProduct(rs);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            DBUtil.close(conn,ps,rs);
        }


        return product;
    }


    // select 할때  ResultSet에서 값을 꺼내서 DTO에 담는 작업을 한건조회할때도, 여러건 조회할때도 계속 사용
    //되고 있어요.   상품명으로 조회, 등등 메서드가 더 추가된다면???  어떤가요??
//   그 일만하는 메서드를 따로 꺼내서 사용하고 싶으신가요??
//    접근제한자 ? -뭘로???   리턴타입!!!    뭘 리턴하면 될까요?
    private ProductDTO resultSetToProduct(ResultSet rs) throws SQLException {
        ProductDTO product = new ProductDTO();

        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getInt("price"));
//        product.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());


        Timestamp regDate = rs.getTimestamp("reg_date");
        if(regDate != null){
            product.setRegDate(regDate.toLocalDateTime());
        }

        return product;
    }
}
