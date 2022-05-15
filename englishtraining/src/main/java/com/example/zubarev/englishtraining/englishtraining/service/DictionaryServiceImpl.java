package com.example.zubarev.englishtraining.englishtraining.service;

import java.util.Optional;

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

    @Override
    public Dictionary addDictionary(Dictionary dictionary) {
        return dictionaryRepos.save(dictionary);
    }

    @Override
    public Dictionary getDictionaryById(long dictionaryId) {
        Optional<Dictionary> dictionary = dictionaryRepos.findById(dictionaryId);
        return dictionary.orElse(null);
    }

    @Override
    public void deleteDictionaryById(long dictionaryId) {
        dictionaryRepos.deleteById(dictionaryId);
    }

    
}