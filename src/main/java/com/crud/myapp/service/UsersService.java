package com.crud.myapp.service;

import com.crud.myapp.model.Users;
import com.crud.myapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsersService {

    @Autowired
    public UsersRepository usersRepository;

    public Users findByUsername(String username) {
        return usersRepository.findByLoginUser(username);
    }

    public void save(Users user) {
        usersRepository.save(user);
    }
}


