package com.example.zubarev.englishtraining.englishtraining.service;

import com.example.zubarev.englishtraining.englishtraining.model.Training;

public interface TrainingService {
    public Iterable<Training> getAll();
    public Training addTraining(Training training);
    public Iterable<Training> getByIdUser(Long idUser);
}
