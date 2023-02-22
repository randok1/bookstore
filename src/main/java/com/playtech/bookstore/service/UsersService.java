package com.playtech.bookstore.service;

import com.playtech.bookstore.controller.LoginRequest;
import com.playtech.bookstore.controller.LoginResponse;
import com.playtech.bookstore.data.UsersData;
import com.playtech.bookstore.exception.ApplicationException;
import com.playtech.bookstore.jpa.UsersEntity;
import com.playtech.bookstore.repository.UsersEntityRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {
    @Autowired
    private UsersEntityRepository usersEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersService(UsersEntityRepository usersEntityRepository, PasswordEncoder passwordEncoder) {
        this.usersEntityRepository = usersEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUsers(UsersData users){
        usersEntityRepository.save(new UsersEntity(users.getUsername(), passwordEncoder.encode(users.getPassword()), users.getFirstname(), users.getLastname()));
    }
    public List<UsersData> getUsers(){
        List<UsersEntity> usersEntities = usersEntityRepository.findAll();
        List<UsersData> allUsers = new ArrayList<>();
        for (UsersEntity usersEntity : usersEntities){
            allUsers.add(new UsersData(usersEntity.getUsername(), passwordEncoder.encode(usersEntity.getPassword()), usersEntity.getFirstname(), usersEntity.getLastname()));
        }
        return allUsers;
    }
    public LoginResponse login(LoginRequest login){
        UsersEntity usersEntity = usersEntityRepository.findUsersEntitiesByUsername(login.getUsername());
        if(passwordEncoder.matches(login.getPassword(), usersEntity.getPassword())){
            return new LoginResponse(generateJwt(login.getUsername()));
        } else {
            throw new ApplicationException("Invalid password");
        }
    }
    private String generateJwt(String username){
        Map<String, Object> claims = Map.of("username", username);
        byte[] secret = Decoders.BASE64.decode("c2FmZ2hqZ2tsa2poZ2ZkZ2zDtmdkbGtzZ25kc2tsbGduZHNrbGZmZG5oa2dkc2duZmfDtmRkZw");
        Key key = Keys.hmacShaKeyFor(secret);
        return Jwts.builder()
                .setIssuer("org")
                .setSubject(username)
                .setIssuedAt(new Date())
                .addClaims(claims)
                .signWith(key)
                .compact();
    }
}
