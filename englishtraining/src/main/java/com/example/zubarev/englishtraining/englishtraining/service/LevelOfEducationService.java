package com.example.zubarev.englishtraining.englishtraining.service;

import java.util.List;

import com.example.zubarev.englishtraining.englishtraining.model.LevelOfEducation;

public interface LevelOfEducationService {
    public List<LevelOfEducation> getAll();
    public List<LevelOfEducation> findByIdLevelOfEducation(Long idLevelOfEducation);
}
