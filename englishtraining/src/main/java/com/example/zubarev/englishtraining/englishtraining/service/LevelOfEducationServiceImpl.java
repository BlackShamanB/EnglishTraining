package com.example.zubarev.englishtraining.englishtraining.service;

import java.util.List;

import com.example.zubarev.englishtraining.englishtraining.model.LevelOfEducation;
import com.example.zubarev.englishtraining.englishtraining.repos.LevelOfEducationRepos;

import org.springframework.stereotype.Service;

@Service
public class LevelOfEducationServiceImpl implements LevelOfEducationService{
    private final LevelOfEducationRepos levelOfEducationRepos; 
    public LevelOfEducationServiceImpl(LevelOfEducationRepos levelOfEducationRepos) {
        this.levelOfEducationRepos = levelOfEducationRepos;
    }
    @Override
    public List<LevelOfEducation> getAll() {
        return (List<LevelOfEducation>) levelOfEducationRepos.findAll();
    }
    @Override
    public List<LevelOfEducation> findByIdLevelOfEducation(Long idLevelOfEducation) {
        return (List<LevelOfEducation>) levelOfEducationRepos.findByIdLevelOfEducation(idLevelOfEducation);
    }
    
}
