package com.alipay.sofa.common.dal.tables;

import javax.persistence.*;
import java.util.*;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(
        name = "users"
        //uniqueConstraints = {@UniqueConstraint(columnNames = {"auth_service", "auth_id"})
)
public class User {
    //自增ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",updatable = false, nullable = false)
    private Long id;

    //var(50)
    @Column(name = "name",unique = true,length = 50)
    private String name="";

    //datetime
    @Column(name = "created_at", nullable = false, updatable=false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    //var(50)
    //oauth service eg.gmail,facebook,github
    @JsonIgnore
    @Column(name = "auth_service",length = 50)
    private String authService;

    //var(50)
    //oauth id
    @JsonIgnore
    @Column(name = "auth_id",length = 50)
    private String authId;

    //tinyint(1)
    @Column(name = "blocked",nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean blocked=false;

    //tinyint(1)
    @Column(name = "admin",nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean isAdmin=false;

    //var(50)
    @Column(name = "avatar",nullable = true,length = 50)
    private String avatar;

    //oauth user don't have password
    @JsonIgnore
    @Column(name = "password",nullable = false,length = 50)
    private String password="";

    //phone number
    @Column(name="phone",updatable = true,unique = true,nullable = true)
    private Long phone;

    //var(50)
    @Column(name="email",updatable = true,unique = true,nullable = true,length = 50)
    private String email;


    public User(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getAuthService() {
        return authService;
    }

    public void setAuthService(String authService) {
        this.authService = authService;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
