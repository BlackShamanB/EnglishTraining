package com.example.zubarev.englishtraining.englishtraining.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FieldOfActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFieldOfActivity;
    private String fieldOfActivity;
    
    public FieldOfActivity() {
    }

    public FieldOfActivity(String fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public Long getIdFieldOfActivity() {
        return idFieldOfActivity;
    }

    public void setIdFieldOfActivity(Long idFieldOfActivity) {
        this.idFieldOfActivity = idFieldOfActivity;
    }

    public String getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(String fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    
}
