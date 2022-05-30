package com.example.zubarev.englishtraining.englishtraining.service;

import java.util.List;

import com.example.zubarev.englishtraining.englishtraining.repos.FieldOfActivityRepos;

import org.springframework.stereotype.Service;

@Service
public class FieldOfActivityServiceImpl implements FieldOfActivityService{
    private final FieldOfActivityRepos fieldOfActivityRepos; 
    public FieldOfActivityServiceImpl(FieldOfActivityRepos fieldOfActivityRepos) {
        this.fieldOfActivityRepos = fieldOfActivityRepos;
    }
    @Override
    public List<FieldOfActivityService> findByIdFieldOfActivity(Long idFieldOfActivity) {
        return fieldOfActivityRepos.findByIdFieldOfActivity(idFieldOfActivity);
    }
    
}
