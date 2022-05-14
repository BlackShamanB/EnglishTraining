package com.example.zubarev.englishtraining.englishtraining.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;
    private String role;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String gender;
    private int age;

    private Long idLevelOfEducation;
    private Long idFieldOfActivity;
    private Long idLanguageProficiency;


    public User(){
    }


    public User(String name) {
        this.name = name;
    }


    public User(String role, String email, String password, String name, String phoneNumber, String gender,
            int age, Long idLevelOfEducation, Long idFieldOfActivity, Long idLanguageProficiency) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
        this.idLevelOfEducation = idLevelOfEducation;
        this.idFieldOfActivity = idFieldOfActivity;
        this.idLanguageProficiency = idLanguageProficiency;
    }


    public Long getIdUser() {
        return idUser;
    }


    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
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


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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


    public Long getIdLevelOfEducation() {
        return idLevelOfEducation;
    }


    public void setIdLevelOfEducation(Long idLevelOfEducation) {
        this.idLevelOfEducation = idLevelOfEducation;
    }


    public Long getIdFieldOfActivity() {
        return idFieldOfActivity;
    }


    public void setIdFieldOfActivity(Long idFieldOfActivity) {
        this.idFieldOfActivity = idFieldOfActivity;
    }


    public Long getIdLanguageProficiency() {
        return idLanguageProficiency;
    }


    public void setIdLanguageProficiency(Long idLanguageProficiency) {
        this.idLanguageProficiency = idLanguageProficiency;
    }

    
}
