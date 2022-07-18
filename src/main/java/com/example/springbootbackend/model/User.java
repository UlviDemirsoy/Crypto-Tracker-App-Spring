package com.example.springbootbackend.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="isAdmin", columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean isAdmin;


}
