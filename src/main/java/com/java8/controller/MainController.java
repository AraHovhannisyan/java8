package com.java8.controller;

import com.java8.model.Bank;
import com.java8.model.Client;
import com.java8.model.Loan;
import com.java8.service.ClientService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MainController {

    ClientService clientService;

    @PostMapping("save_client")
    public String saveClient(@Valid Client client) {
        clientService.saveClient(client);
        return "redirect:save_client";
    }

    @GetMapping("save_client")
    public String saveClient(ModelMap map) {

        List<Client> clients = clientService.findAllClients();
        clients.sort(Comparator.comparing(Client::getName));
        map.addAttribute("clients", clients);
        return "save_client";

    }

    @GetMapping("filters")
    public String getClientsByFilter(ModelMap map) {

        Client clientPositive = Client.builder().name("Positive").email("positive@mail.com").age(33).build();
        if (!clientService.existsByName(clientPositive.getName())) {
            clientService.saveClient(clientPositive);
        }

        Client clientNegative = Client.builder().name("NegativeClient").email("negative@mail.com").age(77).build();
        if (!clientService.existsByName(clientNegative.getName())) {
            clientService.saveClient(clientNegative);
        }

        List<Client> clients = clientService.findAllClients();

        Stream<Client> negativeClient = clients.stream().filter(client -> !client.getName().equals("NegativeClient"));

        ArrayList<Client> collect = negativeClient.collect(Collectors.toCollection(ArrayList::new));

        map.addAttribute("filteredClients", collect);
        return "filters";
    }


    @GetMapping("sorting_with_lambda")
    public String sortWithLambda(ModelMap map) {
        List<Client> allClients = clientService.findAllClients();
        Collections.sort(allClients, (o1, o2) -> o1.getAge() - o2.getAge());
        map.addAttribute("allClients", allClients);
        return "sorting_with_lambda";
    }

    @PostMapping("save_loan")
    public String saveLoan(@Valid Loan loan) {
        Client client = loan.getClient();
        if (clientService.existsByName(client.getName())) {
            Optional<Client> clientById = clientService.findClient(client);
            clientById.ifPresent(val -> clientService.saveLoan(loan));
        }
        return "redirect:save_loan";
    }

    @GetMapping("save_loan")
    public String saveLoan(ModelMap map) {
        List<Client> allClients = clientService.findAllClients();
        map.addAttribute("allClients", allClients);
        return "save_loan";
    }


    @PostMapping("save_bank")
    public String saveBank(@Valid Bank bank) {
        clientService.saveBank(bank);
        return "redirect:save_bank";
    }

    @GetMapping("save_bank")
    public String saveBank(ModelMap map){
        List<Bank> allBanks = clientService.findAllBanks();
        map.addAttribute("allBanks", allBanks);
        return "save_bank";
    }

    @GetMapping("find_bank_information")
    public String findBankInfo(@RequestParam String name, ModelMap map){

        if(clientService.existsBankByName(name)){
            List<Bank> allBanks = clientService.findAllBanks();
            List<String> bankNames = new ArrayList<>();
            for (Bank allBank : allBanks) {
                bankNames.add(allBank.getName());
            }
            Optional<String> any = bankNames.stream()
                    .filter(s -> s.contains(name))
                    .findAny();

            any.ifPresent(val ->
                    map.addAttribute("bankInfo",clientService.findBankByName(any.get())));
        }else{
            map.addAttribute("bankInfo", "No Information");
        }
        return "find_bank_information";
    }



}
