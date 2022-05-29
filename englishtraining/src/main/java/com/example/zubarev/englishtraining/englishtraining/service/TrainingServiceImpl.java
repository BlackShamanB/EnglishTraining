package com.example.zubarev.englishtraining.englishtraining.service;

import java.util.Optional;

import com.example.zubarev.englishtraining.englishtraining.model.Dictionary;
import com.example.zubarev.englishtraining.englishtraining.model.Training;
import com.example.zubarev.englishtraining.englishtraining.repos.DictionaryRepos;
import com.example.zubarev.englishtraining.englishtraining.repos.TrainingRepos;

import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService{
    private final TrainingRepos trainingRepos;

    

    public TrainingServiceImpl(TrainingRepos trainingRepos) {
        this.trainingRepos = trainingRepos;
    }
    @Override
    public Iterable<Training> getAll() {
        return trainingRepos.findAll();
    }
    @Override
    public Training addTraining(Training training) {
        return trainingRepos.save(training);
    }
    @Override
    public Iterable<Training> getByIdUser(Long idUser) {
        return trainingRepos.findAllByIdUser(idUser);
    }
}