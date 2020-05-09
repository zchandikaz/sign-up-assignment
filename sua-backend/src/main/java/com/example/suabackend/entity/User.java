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
@Table(name = "T506_user")
public class User implements UserDetails, Serializable {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    @ColumnDefault("0")
    private int type;

    @Column(name = "status")
    @ColumnDefault("0")
    private int status;

    public User() {
    }

    public User(String username, String password, int type, int status) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getType() == user.getType() &&
                getStatus() == user.getStatus() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getType(), getStatus());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        switch (getType()){
            case 0:
                grantedAuthorities.add(()->"search");
                grantedAuthorities.add(()->"reservation");
                break;
            case 1:
                grantedAuthorities.add(()->"insert");
                grantedAuthorities.add(()->"update");
                grantedAuthorities.add(()->"delete");
                break;
        }

        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return getStatus()==0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getStatus()==0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getStatus()==0;
    }

    @Override
    public boolean isEnabled() {
        return getStatus()==0;
    }
}
