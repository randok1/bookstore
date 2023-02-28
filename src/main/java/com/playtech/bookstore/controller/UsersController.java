package com.playtech.bookstore.controller;

import com.playtech.bookstore.data.UsersData;
import com.playtech.bookstore.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    UsersService usersService;

    @PostMapping("api/users")
    public void addUser(@RequestBody UsersData users){
        usersService.addUsers(users);
    }
    @GetMapping("api/users")
    public List<UsersData> getUsers () {
        return usersService.getUsers();
    }
    @PostMapping("api/public/login")
    public LoginResponse login(@RequestBody LoginRequest login){
        return usersService.login(login);
    }
}
