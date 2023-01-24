package com.playtech.bookstore.service;

import com.playtech.bookstore.data.ClientData;
import com.playtech.bookstore.jpa.ClientEntity;
import com.playtech.bookstore.repository.ClientEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientEntityRepository clientEntityRepository;
    public void addClient(ClientData client){
        clientEntityRepository.save(new ClientEntity(client.getName(), client.getEmail(), client.getPhone(), client.getAddress()));
    }
    public ClientData getClient(String nimi) {
        ClientEntity clientEntity = clientEntityRepository.findClientEntitiesByName(nimi);
        ClientData client = new ClientData(clientEntity.getName(), clientEntity.getEmail(), clientEntity.getPhone(), clientEntity.getAddress());
        return client;
    }
    public List<ClientData> getClients(){
        List<ClientEntity> clientEntities = clientEntityRepository.findAll();
        List<ClientData> allClients = new ArrayList<>();
        for(ClientEntity clientEntity : clientEntities){
            allClients.add(new ClientData(clientEntity.getName(), clientEntity.getEmail(), clientEntity.getPhone(), clientEntity.getAddress()));
        }
        return allClients;
    }
}
