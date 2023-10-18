package com.ms.user.services;

import com.ms.user.dto.UserDTO;
import com.ms.user.models.UserModel;
import com.ms.user.produces.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    UserProducer userProducer;

    @Transactional
    public UserModel saveUser(UserDTO userDTO){

        UserModel user = new UserModel();
        user.setEmail(userDTO.email());
        user.setName(userDTO.name());
        repository.save(user);
        userProducer.publishMessageEmail(user);
        return user;
    }
}
