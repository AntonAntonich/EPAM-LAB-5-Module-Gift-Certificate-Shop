package com.epam.esm.domain.model.entity.dto;

public class UsernamePasswordDto {
    private String userName;
    private String password;

    public UsernamePasswordDto() {
    }

    public UsernamePasswordDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
