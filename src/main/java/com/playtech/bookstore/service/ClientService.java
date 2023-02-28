package com.playtech.bookstore.service;

import com.playtech.bookstore.controller.LoginResponse;
import com.playtech.bookstore.data.BookData;
import com.playtech.bookstore.data.ClientData;
import com.playtech.bookstore.exception.ApplicationException;
import com.playtech.bookstore.jpa.BookEntity;
import com.playtech.bookstore.jpa.ClientEntity;
import com.playtech.bookstore.repository.ClientEntityRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.constant.Constable;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService{
    private final PasswordEncoder pwEncoder;

    private final ClientEntityRepository clientEntityRepository;

    public void signup(ClientData client){
        clientEntityRepository.save(new ClientEntity(client.getName(), client.getEmail(), client.getPhone(), client.getAddress(), pwEncoder.encode(client.getPassword())));
    }
    public void changePassword(ClientData client, String newPassword){
        ClientEntity clientEntity = clientEntityRepository.findClientEntityByEmail(client.getEmail());
        if(pwEncoder.matches(client.getPassword(), clientEntity.getPassword())){
            clientEntityRepository.save(new ClientEntity(client.getName(), client.getEmail(), client.getPhone(), client.getAddress(), pwEncoder.encode(newPassword)));
        } else {
            throw new ApplicationException("Wrong password");
        }
    }
    public LoginResponse login(ClientData client){
        ClientEntity clientEntity = clientEntityRepository.findClientEntityByEmail(client.getEmail());
        if(pwEncoder.matches(client.getPassword(), clientEntity.getPassword())){
            return new LoginResponse(generateJwt(clientEntity.getEmail())); // Loginresponse("JWT")
        } else {
            throw new ApplicationException("Wrong password");
        }
    }

    public ClientData getClient(String nimi) {
        ClientEntity clientEntity = clientEntityRepository.findClientEntitiesByName(nimi);
        ClientData client = new ClientData(clientEntity.getName(), clientEntity.getEmail(), clientEntity.getPhone(), clientEntity.getAddress(), clientEntity.getPassword());
        return client;
    }
    public List<ClientData> getClients(){
        List<ClientEntity> clientEntities = clientEntityRepository.findAll();
        List<ClientData> allClients = new ArrayList<>();
        for(ClientEntity clientEntity : clientEntities){
            allClients.add(new ClientData(clientEntity.getName(), clientEntity.getEmail(), clientEntity.getPhone(), clientEntity.getAddress(), clientEntity.getPassword()));
        }
        return allClients;
    }

    private String generateJwt(String id){
        Map<String, Object> claims = Map.of("email", id);
        byte[] secret = Decoders.BASE64.decode("YXNkZmdoamtsw7ZsamV5NjdneTMwNGowb2JnaHZneXViam5pbml1bmlidWJ1aGI");
        Key key = Keys.hmacShaKeyFor(secret);
        return Jwts.builder()
                .setIssuer("org")
                .setSubject(id)
                .setIssuedAt(new Date())
                .addClaims(claims)
                .signWith(key)
                .compact();
    }
}
