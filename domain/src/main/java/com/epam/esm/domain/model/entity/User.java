package com.epam.esm.domain.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends CustomEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int id;
    @Column(name = "u_email")
    private String email;
    @Column(name = "u_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private Boolean active;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(int id, String email, String userName, String password, Boolean active, Role role) {
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
