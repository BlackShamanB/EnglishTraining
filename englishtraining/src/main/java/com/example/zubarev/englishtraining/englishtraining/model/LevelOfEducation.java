package com.example.zubarev.englishtraining.englishtraining.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LevelOfEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLevelOfEducation;
    private String levelOfEducation;
    
    public LevelOfEducation() {
    }

    public LevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }

    public Long getIdLevelOfEducation() {
        return idLevelOfEducation;
    }

    public void setIdLevelOfEducation(Long idLevelOfEducation) {
        this.idLevelOfEducation = idLevelOfEducation;
    }

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }
}
