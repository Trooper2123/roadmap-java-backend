package com.pgg.roadmapjava.user;

import com.pgg.roadmapjava.exception.CustomSQLErrorCodeTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserJDBC implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        final CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("USER");
    }

    public List<User> findAllUsers() {
        return jdbcTemplate.query("SELECT * FROM USER", new UserRowMapper());
    }

    public Optional<User> findUserById(int id) {
        return Optional.ofNullable(jdbcTemplate
                .queryForObject("SELECT * FROM USER WHERE ID = ?"
                        , new Object[]{id}
                        , new BeanPropertyRowMapper<User>(User.class)));
    }

    public List<User> findUserByCPF(String cpf) {
        return jdbcTemplate
                .query("SELECT * FROM USER WHERE CPF = ?"
                        , new Object[]{cpf}
                        , new BeanPropertyRowMapper<User>(User.class));
    }

    public int deleteUserById(int id) {
        return jdbcTemplate.update("DELETE FROM USER WHERE ID = ?"
                , new Object[]{id});
    }

    public int deleteUserByCPF(String cpf) {
        return jdbcTemplate.update("DELETE FROM USER WHERE CPF = ?"
                , new Object[]{cpf});
    }

    public int insertUser(User user) {
        return jdbcTemplate.update("INSERT INTO USER (NAME,ANNIVERSARY,PHONE,EMAIL,CPF,ADDRESS)" +
                "VALUES (?,?,?,?,?,?)", new Object[]{
                user.getName()
                , user.getAnniversary()
                , user.getPhone()
                , user.getEmail()
                , user.getCpf()
                , user.getAddress()
                });
    }

    public int updateUserById(int id, User user) {
        return jdbcTemplate.update("UPDATE USER SET NAME= ?" +
                ",ANNIVERSARY= ?" +
                ",PHONE = ?," +
                "EMAIL = ?," +
                "ADDRESS = ?", new Object[]{id,
                user.getName()
                , user.getAnniversary()
                , user.getPhone()
                , user.getEmail()
                , user.getAddress()
        });
    }

    public int updateUserByCPF(String cpf, User user) {
        return jdbcTemplate.update("UPDATE USER SET NAME= ?" +
                ",ANNIVERSARY= ?" +
                ",PHONE = ?" +
                ",EMAIL = ?" +
                ",ADDRESS = ?" +
                "WHERE CPF = ?", new Object[]{cpf,
                user.getName()
                , user.getAnniversary()
                , user.getPhone()
                , user.getEmail()
                , user.getAddress()
        });
    }

}
