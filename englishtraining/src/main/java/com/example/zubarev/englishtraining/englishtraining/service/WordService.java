package com.example.zubarev.englishtraining.englishtraining.service;

import com.example.zubarev.englishtraining.englishtraining.model.Word;

public interface WordService {
    public Iterable<Word> getAll();
    public Word addWord(Word word);
    public Word getWordById(long idWord);
    public void deleteWordById(long idWord);
}
