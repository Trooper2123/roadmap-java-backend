package com.pgg.roadmapjava.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper  implements RowMapper<User> {


    @Override
    public User mapRow(final ResultSet rs, final  int rowNum) throws SQLException {
        final User user = new User();

        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setAnniversary(rs.getString("anniversary"));
        user.setAddress(rs.getString("address"));
        user.setCpf(rs.getString("cpf"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        return user;
    }
}
