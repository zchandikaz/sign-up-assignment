package com.example.suabackend.controller;

import com.example.suabackend.entity.User;
import com.example.suabackend.service.UserService;
import com.example.suabackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @GetMapping("/register")
    public String register(){
        return "dsf";
    }
}
