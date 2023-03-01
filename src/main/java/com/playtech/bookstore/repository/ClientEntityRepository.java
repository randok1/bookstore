package com.playtech.bookstore.repository;

import com.playtech.bookstore.jpa.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, Long> {
    ClientEntity findClientEntitiesByName(String name);
    ClientEntity findClientEntityByEmail(String email);
    ClientEntity findClientEntityById(int id);
}
