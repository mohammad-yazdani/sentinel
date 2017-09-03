package com.sentinel.authservice.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "user")
public class User implements Serializable {

    private static Logger log = LoggerFactory.getLogger(User.class);

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "dateRegistered")
    private Date dateRegistered;

    @Column(name = "auth")
    private String auth;

    public User (String username, String email, String password) {
        if (username != null) this.username = username;
        else this.username = email;
        this.email = email;
        // this.dateRegistered = new Timestamp(dateRegistered.getTime()); TODO : FIX LATER
        this.dateRegistered = new Date();
        log.warn("PRE CRYPT");
        this.auth = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
