package com.pgg.roadmapjava.user;

import java.util.List;

public interface UserDao {


    List<User> findAllUsers();
    List<User> findUserByCPF(String cpf);
    int deleteUserByCPF (String cpf);
    int deleteUserById(int id);
    int insertUser(User user);
    int updateUserById(int id,User user);
    int updateUserByCPF(String cpf,User user);

}
