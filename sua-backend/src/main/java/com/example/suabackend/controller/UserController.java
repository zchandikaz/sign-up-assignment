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

        user.setVerifyCode(Utils.sendVerifyEmail(user.getUsername(), user.getEmail()));
        userService.add(user);

        System.out.println(user);
        return new ResponseText("Please check your email.", true);
    }

    @GetMapping(
        value = "/status/{username}"
    )
    public int getStatus(@PathVariable("username") String username) {
        try{
            User user = userService.getUser(username);
            return user.getStatus();
        }catch (Exception e){

        }
        return -1;
    }

    @GetMapping(
        value = "/verify/{username}/{verifyCode}"
    )
    public ResponseText verify(@PathVariable("username") String username, @PathVariable("verifyCode") String verifyCode){
        try{
            User user = userService.getUser(username);
            if(user.getStatus()==0 && user.getVerifyCode().equals(verifyCode)) {
                user.setStatus(1);
                userService.update(user);
                return new ResponseText("Account verified", true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseText("Something wrong", false);
    }
}
