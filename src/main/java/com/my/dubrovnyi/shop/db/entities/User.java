package com.my.dubrovnyi.shop.db.entities;

/**
 * Class of User entity
 *
 * @author Bohdan Dubrovnyi
 */

public class User {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private int restrictId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestrictId() {
        return restrictId;
    }

    public void setRestrictId(int restrictId) {
        this.restrictId = restrictId;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", email='" + email + '\''
                + ", restrictId=" + restrictId
                + '}';
    }
}