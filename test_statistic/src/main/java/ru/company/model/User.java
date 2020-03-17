package ru.company.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int id;
    private String name;
    private String dateUser;
    private String idPage;

    public User() {
    }

    public User(int id, String name, String date, String idPage) {
        this.id = id;
        this.name = name;
        this.dateUser = date;
        this.idPage = idPage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateUser() {
        return dateUser;
    }

    public void setDateUser(String dateUser) {
        this.dateUser = dateUser;
    }

    public String getIdPage() {
        return idPage;
    }

    public void setIdPage(String idPage) {
        this.idPage = idPage;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + dateUser +
                ", idPage='" + idPage + '\'' +
                '}';
    }
}
