package com.example.springbootbackend.model;


import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name="Currency")

public class Currency {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    @Column(name="currency_name", nullable = false)
    private String currencyName;

    @Column(name="symbol", unique = true)
    private String symbol;

    @Column(name="current_price")
    private float currentPrice;

    @Column(name="created_date")
    private java.util.Date createdDate;

    @Column(name = "enabled", columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean enabled;


}
