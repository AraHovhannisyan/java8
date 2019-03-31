package com.java8.service;

import com.java8.model.Bank;
import com.java8.model.Client;
import com.java8.model.Loan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService {

    void saveClient(Client client);

    List<Client> findAllClients();

    boolean existsByName(String name);

    Optional<Client> findClient(Client client);

    void saveLoan(Loan loan);

    void saveBank(Bank bank);

    List<Bank> findAllBanks();

    boolean existsBankByName(String name);

    Optional<Bank> findBankByName(String name);
}
