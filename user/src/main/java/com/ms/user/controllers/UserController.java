package com.ms.user.controllers;

import com.ms.user.dto.UserDTO;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Operations related to User")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserDTO userDTO){
        UserModel user = service.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
