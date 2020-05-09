package com.example.suabackend.controller;

import com.example.suabackend.entity.User;
import com.example.suabackend.service.UserService;
import com.example.suabackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.suabackend.utils.Utils.BASE_URL;


@CrossOrigin(origins = Utils.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(BASE_URL + "users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
