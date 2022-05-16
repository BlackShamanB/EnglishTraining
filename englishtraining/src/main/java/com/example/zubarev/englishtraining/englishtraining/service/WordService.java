package com.example.zubarev.englishtraining.englishtraining.service;

import java.io.IOException;

import com.example.zubarev.englishtraining.englishtraining.model.Word;

import org.springframework.web.multipart.MultipartFile;

public interface WordService {
    public Iterable<Word> getAll();
    public Word addWord(Word word);
    public Word getWordById(long idWord);
    public void deleteWordById(long idWord);
    public void addWordsFromXLS(MultipartFile file, Long idTheme) throws IOException;
    public Iterable<Word> getWordsByThemeId(Long idTheme);
}
