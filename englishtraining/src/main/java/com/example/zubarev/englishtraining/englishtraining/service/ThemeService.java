package com.example.zubarev.englishtraining.englishtraining.service;

import java.util.List;

import com.example.zubarev.englishtraining.englishtraining.model.Theme;

public interface ThemeService {
    public Iterable<Theme> getAll();
    public Theme getThemeById(Long idTheme);
    public List<Theme> getByIdDictionary(Long idDictionary);
    public Theme addTheme(Theme theme);
    public void deleteThemeById(Long idTheme);
    public Theme findByIdTheme(Long idTheme);
}
