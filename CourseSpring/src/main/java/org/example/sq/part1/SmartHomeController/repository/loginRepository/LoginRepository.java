package org.example.sq.part1.SmartHomeController.repository.loginRepository;

import org.example.sq.part1.SmartHomeController.dto.LoginDTO;
import org.example.sq.part1.SmartHomeController.model.LoginModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRepository {
    private final JdbcTemplate jdbcTemplate;

    public LoginRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LoginModel> getLoginObjects(LoginDTO.Request requestDTO){
        String sql = "SELECT * FROM login WHERE name=? AND password = ?";

        RowMapper<LoginModel> rowMapper = (r,i) -> {
            LoginModel loginModel = new LoginModel();
            loginModel.setId(r.getInt("id"));
            loginModel.setUsername(r.getString("name"));
            loginModel.setPassword(r.getString("password"));
            return loginModel;
        };
        List<LoginModel> loginObjects = jdbcTemplate.query(
                sql,
                new Object[]{requestDTO.username(), requestDTO.password()},
                rowMapper
        );
        return loginObjects;
    }
}
