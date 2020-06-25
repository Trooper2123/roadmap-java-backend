package com.pgg.roadmapjava.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper  implements RowMapper<User> {


    @Override
    public User mapRow(final ResultSet rs, final  int rowNum) throws SQLException {
        final User user = new User();

        user.setId(rs.getInt("ID"));
        user.setName(rs.getString("NAME"));
        user.setAnniversary(rs.getString("ANNIVERSARY"));
        user.setAddress(rs.getString("ADDRESS"));
        user.setCpf(rs.getString("CPF"));
        user.setEmail(rs.getString("EMAIL"));
        user.setPhone(rs.getString("PHONE"));
        return user;
    }
}
