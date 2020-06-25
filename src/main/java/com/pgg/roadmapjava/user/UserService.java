package com.pgg.roadmapjava.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;



    public List<User> findAllUsers(){
        return userDao.findAllUsers();
    }

    public List<User> findUserByCPF(String cpf){
        return userDao.findUserByCPF(cpf);
    }

    public int deleteUserByCPF(String cpf){
        return userDao.deleteUserByCPF(cpf);
    }


    public int deleteUserById(int id){
        return userDao.deleteUserById(id);
    }

    public int insertUser(User user){
        return userDao.insertUser(user);
    }


    public int updateUserById(int id, User user){
        return userDao.updateUserById(id,user);
    }

    public int updateUserByCPF(String cpf, User user){
        return userDao.updateUserByCPF(cpf,user);
    }


}
