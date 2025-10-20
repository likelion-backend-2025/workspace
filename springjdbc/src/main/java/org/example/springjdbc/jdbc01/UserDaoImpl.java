package org.example.springjdbc.jdbc01;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;


//    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }


    @Override
    public void insertUser(User user) {
        String sql = "insert into users(name,email)values(?,?)";
        int count = jdbcTemplate.update(sql, user.getUsername(), user.getEmail());
    }

    @Override
    public List<User> findAllUsers() {
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                return user;
            }
        };
        return jdbcTemplate.query("select * from users",rowMapper);
//        return jdbcTemplate.query("select * from users",new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void updateUser(User user)  {
        String sql = "update users set name=?,email=? where id=?";
        int count = jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getId());

        if (count == 0) {
            throw new DataAccessException("사용자를 찾을 수 없습니다. "+user.getEmail()){} ;
        }
    }

    @Override
    public void deleteUser(Long id) {
        int count = jdbcTemplate.update("delete from users where id = ?", id);

        if (count == 0) {
            throw new DataAccessException("사용자를 찾을 수 없습니다. "+id){} ;
        }
    }
}
