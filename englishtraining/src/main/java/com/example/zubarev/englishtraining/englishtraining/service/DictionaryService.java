package com.example.zubarev.englishtraining.englishtraining.service;

import com.example.zubarev.englishtraining.englishtraining.model.Dictionary;

public interface DictionaryService {
    public Iterable<Dictionary> getAll();
    public Dictionary addDictionary(Dictionary dictionary);
}
