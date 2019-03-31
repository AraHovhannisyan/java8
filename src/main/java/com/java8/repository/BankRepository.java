package com.java8.repository;

import com.java8.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {
    boolean existsByName(String name);

    Optional<Bank> findByName(String name);
}
