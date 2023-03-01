package com.playtech.bookstore.repository;

import com.playtech.bookstore.jpa.PurchasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasesEntityRepository extends JpaRepository<PurchasesEntity, Long> {
    List<PurchasesEntity> findAllByClient(Integer client);
}
