package com.fastcampus.jpapractice;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User {
    @Id // Designate as PK
    private String id;
    private String password;
    private String name;
    private String email;

    // FetchType.EAGER: Bringing information from two entities together (join)
    // FetchType.LAZY: separately. getList() - default
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) // One User, multiple Boards
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // One User, multiple Boards
    List<Board> list = new ArrayList<>();

    private Date inDate; // Date entered
    private Date upDate; // Change Date

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", list=" + list +
                ", inDate=" + inDate +
                ", upDate=" + upDate +
                '}';
    }

    public List<Board> getList() {
        return list;
    }

    public void setList(List<Board> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }
}
