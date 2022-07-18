package com.example.springbootbackend.service.impl;
import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.repository.UserRepository;
import com.example.springbootbackend.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id) );
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }


}
