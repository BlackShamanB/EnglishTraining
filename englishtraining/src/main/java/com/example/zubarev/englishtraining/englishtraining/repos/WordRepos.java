package com.example.zubarev.englishtraining.englishtraining.repos;

import com.example.zubarev.englishtraining.englishtraining.model.Word;

import org.springframework.data.repository.CrudRepository;

public interface WordRepos extends CrudRepository<Word,Long> {
    
}
