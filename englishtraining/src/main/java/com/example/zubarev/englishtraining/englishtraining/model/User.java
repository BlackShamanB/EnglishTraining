package com.example.zubarev.englishtraining.englishtraining.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;
    private String email;
    private String phone;
    private String gender;
    private int age;

    @OneToOne(fetch = FetchType.EAGER)
    private LevelOfEducation idLevelOfEducation;
    @OneToOne(fetch = FetchType.EAGER)
    private FieldOfActivity idFieldOfActivity;
    @OneToOne(fetch = FetchType.EAGER)
    private LanguageProficiency idLanguageProficiency;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LevelOfEducation getIdLevelOfEducation() {
        return idLevelOfEducation;
    }

    public void setIdLevelOfEducation(LevelOfEducation idLevelOfEducation) {
        this.idLevelOfEducation = idLevelOfEducation;
    }

    public FieldOfActivity getIdFieldOfActivity() {
        return idFieldOfActivity;
    }

    public void setIdFieldOfActivity(FieldOfActivity idFieldOfActivity) {
        this.idFieldOfActivity = idFieldOfActivity;
    }

    public LanguageProficiency getIdLanguageProficiency() {
        return idLanguageProficiency;
    }

    public void setIdLanguageProficiency(LanguageProficiency idLanguageProficiency) {
        this.idLanguageProficiency = idLanguageProficiency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
