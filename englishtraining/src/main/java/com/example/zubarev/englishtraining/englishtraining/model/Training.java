package com.example.zubarev.englishtraining.englishtraining.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTraining;
    private Long idUser;
    private Long idTrainingProfile;
    private Date startDateAndTime;
    private Date endDateAndTime;
    private int wordCount;
    private int numberOfMistakes;
    private int numberOfRussianEnglish;
    private int numberOfEnglishRussian;
    
    public Training() {
    }

    public Training(Long idUser, Long idTrainingProfile, Date startDateAndTime,
    Date endDateAndTime, int wordCount, int numberOfMistakes, int numberOfRussianEnglish,
            int numberOfEnglishRussian) {
        this.idUser = idUser;
        this.idTrainingProfile = idTrainingProfile;
        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
        this.wordCount = wordCount;
        this.numberOfMistakes = numberOfMistakes;
        this.numberOfRussianEnglish = numberOfRussianEnglish;
        this.numberOfEnglishRussian = numberOfEnglishRussian;
    }

    public Long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Long idTraining) {
        this.idTraining = idTraining;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdTrainingProfile() {
        return idTrainingProfile;
    }

    public void setIdTrainingProfile(Long idTrainingProfile) {
        this.idTrainingProfile = idTrainingProfile;
    }

    public Date getStartDateAndTime() {
        return startDateAndTime;
    }

    public void setStartDateAndTime(Date startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }

    public Date getEndDateAndTime() {
        return endDateAndTime;
    }

    public void setEndDateAndTime(Date endDateAndTime) {
        this.endDateAndTime = endDateAndTime;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getNumberOfMistakes() {
        return numberOfMistakes;
    }

    public void setNumberOfMistakes(int numberOfMistakes) {
        this.numberOfMistakes = numberOfMistakes;
    }

    public int getNumberOfRussianEnglish() {
        return numberOfRussianEnglish;
    }

    public void setNumberOfRussianEnglish(int numberOfRussianEnglish) {
        this.numberOfRussianEnglish = numberOfRussianEnglish;
    }

    public int getNumberOfEnglishRussian() {
        return numberOfEnglishRussian;
    }

    public void setNumberOfEnglishRussian(int numberOfEnglishRussian) {
        this.numberOfEnglishRussian = numberOfEnglishRussian;
    }
}
