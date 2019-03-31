package com.java8.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@Table
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private LoanType loanType;

    @Column
    private Double percentage;

    @Column
    private int duration;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Bank bank;

}
