package com.example.zubarev.englishtraining.englishtraining.service;

import com.example.zubarev.englishtraining.englishtraining.model.Dictionary;

public interface DictionaryService {
    public Iterable<Dictionary> getAll();
    public Dictionary addDictionary(Dictionary dictionary);
    public Dictionary getDictionaryById(long dictionaryId);
    public void deleteDictionaryById(long dictionaryId);
}
