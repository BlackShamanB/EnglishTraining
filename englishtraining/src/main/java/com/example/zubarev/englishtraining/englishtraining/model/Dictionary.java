package com.example.zubarev.englishtraining.englishtraining.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dictionary {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long idDictionary;
private String difficultyLevel;

public Dictionary() {
}

public Dictionary(String difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
}

public Long getIdDictionary() {
    return idDictionary;
}

public void setIdDictionary(Long idDictionary) {
    this.idDictionary = idDictionary;
}

public String getDifficultyLevel() {
    return difficultyLevel;
}

public void setDifficultyLevel(String difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
}
}
