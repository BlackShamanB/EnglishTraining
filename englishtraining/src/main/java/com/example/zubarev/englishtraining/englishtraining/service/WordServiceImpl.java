package com.example.zubarev.englishtraining.englishtraining.service;

import java.util.Optional;

import com.example.zubarev.englishtraining.englishtraining.model.Word;
import com.example.zubarev.englishtraining.englishtraining.repos.WordRepos;

import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService{
    private final WordRepos wordRepos;

    public WordServiceImpl(WordRepos wordRepos) {
        this.wordRepos = wordRepos;
    }

    @Override
    public Iterable<Word> getAll() {
        return wordRepos.findAll();
    }

    @Override
    public Word addWord(Word word) {
        return wordRepos.save(word);
    }

    @Override
    public Word getWordById(long idWord) {
        Optional<Word> word = wordRepos.findById(idWord);
        return word.orElse(null);
    }

    @Override
    public void deleteWordById(long idWord) {
        wordRepos.deleteById(idWord);
    }

}
