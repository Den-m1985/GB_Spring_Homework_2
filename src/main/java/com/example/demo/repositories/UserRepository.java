package com.example.demo.repositories;

import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbc;
    private final DataBaseProperties databaseProperties;

    public List<User> findAll() {
        String sql = databaseProperties.getFindAll();
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = databaseProperties.getSave();
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    public void deleteById(int id) {
        String sql = databaseProperties.getDeleteById();
        jdbc.update(sql, String.valueOf(id));
    }

    public User getOne(int id) {
        String sql = databaseProperties.getGetOne();
        return jdbc.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(User.class));
    }

    public User update(User user) {
        String sql = databaseProperties.getUpdate();
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

}
