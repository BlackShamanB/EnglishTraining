package com.example.zubarev.englishtraining.englishtraining.repos;

import java.util.List;

import com.example.zubarev.englishtraining.englishtraining.model.Theme;

import org.springframework.data.repository.CrudRepository;

public interface ThemeRepos extends CrudRepository<Theme,Long> {
    List<Theme> findByIdDictionary(Long idDictionary);
}
