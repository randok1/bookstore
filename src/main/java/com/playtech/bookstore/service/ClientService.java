package com.playtech.bookstore.service;

import com.playtech.bookstore.controller.LoginResponse;
import com.playtech.bookstore.data.ClientData;
import com.playtech.bookstore.data.PurchasesData;
import com.playtech.bookstore.exception.ApplicationException;
import com.playtech.bookstore.jpa.ClientEntity;
import com.playtech.bookstore.jpa.PurchasesEntity;
import com.playtech.bookstore.repository.ClientEntityRepository;
import com.playtech.bookstore.repository.PurchasesEntityRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {
    @Autowired
    ClientEntityRepository clientEntityRepository;
    @Autowired
    PurchasesEntityRepository purchasesEntityRepository;
    private final PasswordEncoder pwEncoder;

    public void addClient(ClientData client){
        clientEntityRepository.save(new ClientEntity(client.getName(), client.getEmail(), client.getPhone(), client.getAddress(), client.getPassword()));
    }
    public void signup(ClientData client){
        clientEntityRepository.save(new ClientEntity(client.getName(), client.getEmail(), client.getPhone(), client.getAddress(), pwEncoder.encode(client.getPassword())));
    }
    public LoginResponse login(ClientData client){
        ClientEntity clientEntity = clientEntityRepository.findClientEntityByEmail(client.getEmail());
        if(pwEncoder.matches(client.getPassword(), clientEntity.getPassword())){
            return new LoginResponse(generateJwt(clientEntity.getEmail())); // Loginresponse("JWT")
        } else {
            throw new ApplicationException("Wrong password");
        }
    }
    public void changePassword(ClientData client, String newPassword){
        ClientEntity clientEntity = clientEntityRepository.findClientEntityByEmail(client.getEmail());
        if(pwEncoder.matches(client.getPassword(), clientEntity.getPassword())){
            clientEntityRepository.save(new ClientEntity(client.getName(), client.getEmail(), client.getPhone(), client.getAddress(), pwEncoder.encode(newPassword)));
        } else {
            throw new ApplicationException("Wrong password");
        }
    }
    public ClientData getClient(String name) {
        ClientEntity clientEntity = clientEntityRepository.findClientEntitiesByName(name);
        ClientData client = new ClientData(clientEntity.getName(), clientEntity.getEmail(), clientEntity.getPhone(), clientEntity.getAddress(), clientEntity.getPassword());
        return client;
    }
    public Integer getClientId(String name) {
        ClientEntity clientEntity = clientEntityRepository.findClientEntitiesByName(name);
        return clientEntity.getId();
    }
    public List<ClientData> getClients(){
        List<ClientEntity> clientEntities = clientEntityRepository.findAll();
        List<ClientData> allClients = new ArrayList<>();
        for(ClientEntity clientEntity : clientEntities){
            allClients.add(new ClientData(clientEntity.getName(), clientEntity.getEmail(), clientEntity.getPhone(), clientEntity.getAddress(), clientEntity.getPassword()));
        }
        return allClients;
    }
    public void buyBook(PurchasesData purchasesData) {
        purchasesEntityRepository.save(new PurchasesEntity(purchasesData.getBooks(), purchasesData.getClient(), purchasesData.getTotal()));
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
