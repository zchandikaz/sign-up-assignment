package com.example.suabackend.service;

import com.example.suabackend.entity.User;
import com.example.suabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach( user -> users.add(user) );
        return users;
    }

    public User getUser(String username){
        try {
            return userRepository.findById(username).get();
        }catch (java.util.NoSuchElementException | NullPointerException e){
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = getUser(s);
        if(user==null)
            throw new UsernameNotFoundException("Username not found");
        return user;
    }

    public boolean add(User user){
        if(userRepository.existsById(user.getUsername()))
            return false;
        try {
            userRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean delete(User user){
        if(!userRepository.existsById(user.getUsername()))
            return false;
        userRepository.delete(user);
        return true;
    }

    public boolean update(User user){
        if(!userRepository.existsById(user.getUsername()))
            return false;
        userRepository.save(user);
        return true;
    }
}
