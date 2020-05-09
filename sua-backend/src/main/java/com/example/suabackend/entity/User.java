package com.example.suabackend.entity;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "sua_user")
public class User implements UserDetails, Serializable {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    @ColumnDefault("0")
    private int type = 0;

    /**
     * 0 - not verified
     * 1 - verified
     * 2 - cancelled
     */
    @Column(name = "status")
    @ColumnDefault("0")
    private int status = 0;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private int gender;

    @Column(name = "email")
    private String email;

    @Column(name = "verifyCode")
    private String verifyCode;

    public User() {
    }

    public User(String username, String password, int type, int status) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.status = status;
    }

    public User(String username, String password, String firstName, String lastName, String address, String email, int gender) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.email = email;
    }

    public User(String username, String password, int type, int status, String firstName, String lastName, String email, String address, int gender) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getType() == user.getType() &&
                getStatus() == user.getStatus() &&
                getGender() == user.getGender() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getAddress(), user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getType(), getStatus(), getFirstName(), getLastName(), getAddress(), getGender());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        switch (getType()){
            case 0:
                grantedAuthorities.add(()->"read");
                grantedAuthorities.add(()->"write");
                break;
        }

        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return getStatus()==1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getStatus()==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getStatus()==1;
    }

    @Override
    public boolean isEnabled() {
        return getStatus()==1;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
