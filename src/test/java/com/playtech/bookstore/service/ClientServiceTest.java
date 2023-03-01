package com.playtech.bookstore.service;

import com.playtech.bookstore.data.ClientData;
import com.playtech.bookstore.jpa.ClientEntity;
import com.playtech.bookstore.repository.ClientEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientEntityRepository repository;

    @Spy
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    ClientService service;

    @Test
    void changeAccountPW(){
        //given
        ClientEntity clientEntity = new ClientEntity("aa", "bb", "cc", "dd", passwordEncoder.encode("ee"));
        given(repository.findClientEntityByEmail("bb")).willReturn(clientEntity);

        //when
        ClientData clientData = new ClientData(clientEntity.getName(), clientEntity.getEmail(), clientEntity.getPhone(), clientEntity.getAddress(), "ee");
        service.changePassword(clientData, "ff");

        //then
        then(repository).should().findClientEntityByEmail("bb");
    }



}