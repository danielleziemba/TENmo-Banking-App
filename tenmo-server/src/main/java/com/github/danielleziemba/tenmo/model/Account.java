package com.github.danielleziemba.tenmo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(columnDefinition = "Decimal(10,2)")
    private BigDecimal balance = BigDecimal.valueOf(1000.00);

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private User user;

}
