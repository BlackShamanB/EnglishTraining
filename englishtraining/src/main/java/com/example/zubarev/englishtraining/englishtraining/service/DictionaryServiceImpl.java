package com.example.zubarev.englishtraining.englishtraining.service;

import com.example.zubarev.englishtraining.englishtraining.model.Dictionary;
import com.example.zubarev.englishtraining.englishtraining.repos.DictionaryRepos;

import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService{
    private final DictionaryRepos dictionaryRepos;

    public DictionaryServiceImpl(DictionaryRepos dictionaryRepos) {
        this.dictionaryRepos = dictionaryRepos;
    }

    @Override
    public Iterable<Dictionary> getAll() {
        return dictionaryRepos.findAll();
    }

}