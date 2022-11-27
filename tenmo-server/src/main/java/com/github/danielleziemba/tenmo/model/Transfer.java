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
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Decimal(10,2)")
    private BigDecimal amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    private TransferType transferType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id")
    private TransferStatus transferStatus;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "user_id",
            insertable = false,
            updatable = false)
    private Account accountFrom;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "user_id",
            insertable = false,
            updatable = false)
    private Account accountTo;
}
