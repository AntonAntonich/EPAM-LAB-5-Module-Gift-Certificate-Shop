package com.epam.esm.domain.model.entity.dto;

import com.epam.esm.domain.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends AbstractUserDto {
    private int id;
    private String email;
    private String userName;
    private String password;
    private Boolean active;
    private Role role;

    public UserDto() {
    }

    public UserDto(int id, String email, String userName, String password, Boolean active, Role role) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
