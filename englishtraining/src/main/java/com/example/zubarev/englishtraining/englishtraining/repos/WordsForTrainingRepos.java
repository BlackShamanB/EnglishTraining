package com.example.zubarev.englishtraining.englishtraining.repos;

import com.example.zubarev.englishtraining.englishtraining.model.WordsForTraining;

import org.springframework.data.repository.CrudRepository;

public interface WordsForTrainingRepos extends CrudRepository<WordsForTraining,Long> {
    
}
