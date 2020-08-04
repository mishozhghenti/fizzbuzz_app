package com.example.fizzbuzz.payload.response;

import java.util.List;

public class JwtResponse {
    List<String> roles;
    private String token;
    private String type = "Bearer";
    private Long id;
    private String fullName;
    private String userName;


    public JwtResponse() {
    }

    public JwtResponse(String token, Long id, String userName, String fullName, List<String> roles) {
        this.token = token;
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
