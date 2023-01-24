package com.playtech.bookstore.controller;

import com.playtech.bookstore.data.ClientData;
import com.playtech.bookstore.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientsController {
    @Autowired
    ClientService clientService;

    @PostMapping("client")
    public void addClient(@RequestBody ClientData client){
        clientService.addClient(client);
    }
    @GetMapping("client/{name}")
    public ClientData getClient(@PathVariable("name") String name){
        return clientService.getClient(name);
    }
    @GetMapping("clients")
    public List<ClientData> getClients(){
        return clientService.getClients();
    }
}
