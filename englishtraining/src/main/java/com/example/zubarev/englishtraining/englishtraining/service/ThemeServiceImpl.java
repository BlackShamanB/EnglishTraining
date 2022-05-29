package com.example.zubarev.englishtraining.englishtraining.service;

import java.util.List;
import java.util.Optional;

import com.example.zubarev.englishtraining.englishtraining.model.Theme;
import com.example.zubarev.englishtraining.englishtraining.repos.ThemeRepos;

import org.springframework.stereotype.Service;

@Service
public class ThemeServiceImpl implements ThemeService{
    private final ThemeRepos themeRepos;
    
    public ThemeServiceImpl(ThemeRepos themeRepos) {
        this.themeRepos = themeRepos;
    }

    @Override
    public Iterable<Theme> getAll() {
        return themeRepos.findAll();
    }

    @Override
    public List<Theme> getByIdDictionary(Long idDictionary) {
        return themeRepos.findByIdDictionary(idDictionary);
    }

    @Override
    public Theme addTheme(Theme theme) {
        return themeRepos.save(theme);
    }

    @Override
    public Theme getThemeById(Long idTheme) {
        Optional<Theme> theme =themeRepos.findById(idTheme);
        return theme.orElse(null);
    }

    @Override
    public void deleteThemeById(Long idTheme) {
        themeRepos.deleteById(idTheme);
    }

    @Override
    public Theme findByIdTheme(Long idTheme) {
        Optional<Theme> theme = themeRepos.findById(idTheme);
        return theme.orElse(null);
        
    }
}