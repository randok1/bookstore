package com.playtech.bookstore.repository;

import com.playtech.bookstore.jpa.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersEntityRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findUsersEntitiesByUsername(String username);

}
