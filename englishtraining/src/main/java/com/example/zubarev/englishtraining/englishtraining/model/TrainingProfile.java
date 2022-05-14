package com.example.zubarev.englishtraining.englishtraining.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TrainingProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTrainigProfile;
    private Long idDictionary;
    private Long idWordsForTraining;
    private String russianEnglish;

    public TrainingProfile() {
    }

    public TrainingProfile(Long idDictionary, Long idWordsForTraining, String russianEnglish) {
        this.idDictionary = idDictionary;
        this.idWordsForTraining = idWordsForTraining;
        this.russianEnglish = russianEnglish;
    }

    public Long getIdTrainigProfile() {
        return idTrainigProfile;
    }

    public void setIdTrainigProfile(Long idTrainigProfile) {
        this.idTrainigProfile = idTrainigProfile;
    }

    public Long getIdDictionary() {
        return idDictionary;
    }

    public void setIdDictionary(Long idDictionary) {
        this.idDictionary = idDictionary;
    }

    public Long getIdWordsForTraining() {
        return idWordsForTraining;
    }

    public void setIdWordsForTraining(Long idWordsForTraining) {
        this.idWordsForTraining = idWordsForTraining;
    }

    public String getRussianEnglish() {
        return russianEnglish;
    }

    public void setRussianEnglish(String russianEnglish) {
        this.russianEnglish = russianEnglish;
    }
}
