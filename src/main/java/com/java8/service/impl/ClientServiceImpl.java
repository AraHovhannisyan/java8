package com.java8.service.impl;

import com.java8.model.Bank;
import com.java8.model.Client;
import com.java8.model.Loan;
import com.java8.repository.BankRepository;
import com.java8.repository.ClientRepository;
import com.java8.repository.LoanRepository;
import com.java8.service.ClientService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    LoanRepository loanRepository;
    BankRepository bankRepository;

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public boolean existsByName(String name) {
        return clientRepository.existsByName(name);
    }

    @Override
    public Optional<Client> findClient(Client client) {
        return clientRepository.findById(client.getId());
    }

    @Override
    public void saveLoan(Loan loan) {
        loanRepository.save(loan);
    }

    @Override
    public void saveBank(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public List<Bank> findAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public boolean existsBankByName(String name) {
        return bankRepository.existsByName(name);
    }

    @Override
    public Optional<Bank> findBankByName(String name) {
        return bankRepository.findByName(name);
    }

}
