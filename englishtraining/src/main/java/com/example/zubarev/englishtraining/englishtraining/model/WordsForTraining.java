package com.example.zubarev.englishtraining.englishtraining.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WordsForTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idWordsForTraining;
    private Long idWord;

    public WordsForTraining() {
    }

    public WordsForTraining(Long idWord) {
        this.idWord = idWord;
    }

    public Long getIdWordsForTraining() {
        return idWordsForTraining;
    }

    public void setIdWordsForTraining(Long idWordsForTraining) {
        this.idWordsForTraining = idWordsForTraining;
    }

    public Long getIdWord() {
        return idWord;
    }

    public void setIdWord(Long idWord) {
        this.idWord = idWord;
    }
}
