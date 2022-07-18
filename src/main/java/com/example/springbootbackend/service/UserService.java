package com.example.springbootbackend.service;

import com.example.springbootbackend.model.Alert;
import com.example.springbootbackend.model.Currency;
import com.example.springbootbackend.model.User;

public interface UserService {

    User getUserById(long id);
    User saveUser(User user);
}
