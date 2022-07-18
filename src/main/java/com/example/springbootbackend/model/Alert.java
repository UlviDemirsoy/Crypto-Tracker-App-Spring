package com.example.springbootbackend.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="Alert")
public class Alert {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="currencyId", nullable = false )
    private long currencyId;

    @Column(name="userId", nullable = false )
    private long userId;

    @Column(name="targetPrice")
    private float targetPrice;

    @Column(name="createdAt")
    private java.util.Date createdAt;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;


}

