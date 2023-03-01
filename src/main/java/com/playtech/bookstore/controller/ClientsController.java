package com.playtech.bookstore.controller;

import com.playtech.bookstore.data.ClientData;
import com.playtech.bookstore.data.PurchasesData;
import com.playtech.bookstore.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientsController {
    @Autowired
    ClientService clientService;

    @PostMapping("api/public/signup")
    public void signup(@RequestBody ClientData client){
        clientService.signup(client);
    }
    @PostMapping("api/public/login")
    public LoginResponse login(@RequestBody ClientData client){
        return clientService.login(client);
    }
    @GetMapping("api/client/{name}")
    public ClientData getClient(@PathVariable("name") String name){
        return clientService.getClient(name);
    }
    @GetMapping("api/clients")
    public List<ClientData> getClients(){
        return clientService.getClients();
    }

    @PostMapping("client/buy")
    public void buyBook(@RequestBody PurchasesData purchasesData){
        clientService.buyBook(purchasesData);
    }
}
