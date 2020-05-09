package com.example.suabackend.controller;

import com.example.suabackend.entity.User;
import com.example.suabackend.service.UserService;
import com.example.suabackend.utils.ResponseText;
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
@RequestMapping(BASE_URL + "user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @PostMapping(
            value = "/register"
    )
    public ResponseText register(@RequestBody User user){
        if(userService.isExist(user))
            return new ResponseText("Username already exist.", false);

        userService.add(user);


        return new ResponseText("Please check your email.", true);
    }
}
