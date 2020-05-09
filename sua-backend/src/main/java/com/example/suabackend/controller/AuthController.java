package com.example.suabackend.controller;

import com.example.suabackend.auth.AuthenticationBean;
import com.example.suabackend.service.UserService;
import com.example.suabackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.suabackend.utils.Utils.BASE_URL;

/**
 * Controller which using for authentications
 */
@CrossOrigin(origins = Utils.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(BASE_URL + "auth")
public class AuthController {
    @Autowired
    UserService userService;

    @GetMapping(path = "/")
    public AuthenticationBean auth() {
        return new AuthenticationBean("Success");
    }

}
