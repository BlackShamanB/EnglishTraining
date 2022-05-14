package com.example.zubarev.englishtraining.englishtraining.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LanguageProficiency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLanguageProficiency;
    private String languageProficiency;
    
    public LanguageProficiency() {
    }

    public LanguageProficiency(String languageProficiency) {
        this.languageProficiency = languageProficiency;
    }

    public Long getIdLanguageProficiency() {
        return idLanguageProficiency;
    }

    public void setIdLanguageProficiency(Long idLanguageProficiency) {
        this.idLanguageProficiency = idLanguageProficiency;
    }

    public String getLanguageProficiency() {
        return languageProficiency;
    }

    public void setLanguageProficiency(String languageProficiency) {
        this.languageProficiency = languageProficiency;
    }
}
