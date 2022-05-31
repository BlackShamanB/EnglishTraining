package com.example.zubarev.englishtraining.englishtraining.service;
import java.util.List;

import com.example.zubarev.englishtraining.englishtraining.model.LanguageProficiency;
import com.example.zubarev.englishtraining.englishtraining.repos.LanguageProficiencyRepos;
import org.springframework.stereotype.Service;

@Service
public class LanguageProficiencyServiceImpl implements LanguageProficiencyService{
    private final LanguageProficiencyRepos languageProficiencyRepos; 
    public LanguageProficiencyServiceImpl(LanguageProficiencyRepos languageProficiencyRepos) {
        this.languageProficiencyRepos = languageProficiencyRepos;
    }
    @Override
    public List<LanguageProficiency> findByIdLanguageProficiency(Long idLanguageProficiency) {
        return (List<LanguageProficiency>) languageProficiencyRepos.findByIdLanguageProficiency(idLanguageProficiency);
    }
    @Override
    public List<LanguageProficiency> getAll() {
        return (List<LanguageProficiency>) languageProficiencyRepos.findAll();
    }
    @Override
    public LanguageProficiency add(LanguageProficiency languageProficiency) {
        return languageProficiencyRepos.save(languageProficiency);
    }
}

