package com.example.zubarev.englishtraining.englishtraining.service;
import java.util.List;
import com.example.zubarev.englishtraining.englishtraining.model.LanguageProficiency;
public interface LanguageProficiencyService {
    public List<LanguageProficiency> findByIdLanguageProficiency(Long idLanguageProficiency);
}

