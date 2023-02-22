package com.playtech.bookstore.repository;

import com.playtech.bookstore.jpa.PurchasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesEntityRepository extends JpaRepository<PurchasesEntity, Long> {
    PurchasesEntity findPurchasesEntitiesByClient();
}
