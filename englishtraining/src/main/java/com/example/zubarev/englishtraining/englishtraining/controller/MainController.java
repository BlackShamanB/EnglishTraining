package com.example.zubarev.englishtraining.englishtraining.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.zubarev.englishtraining.englishtraining.model.Dictionary;
import com.example.zubarev.englishtraining.englishtraining.model.Theme;
import com.example.zubarev.englishtraining.englishtraining.service.DictionaryService;
import com.example.zubarev.englishtraining.englishtraining.service.LevelOfEducationService;
import com.example.zubarev.englishtraining.englishtraining.service.ThemeService;
import com.example.zubarev.englishtraining.englishtraining.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserService userService;
    private DictionaryService dictionaryService;
    private ThemeService themeService;
    private LevelOfEducationService levelOfEducationService;

    
    static {
        // users.add(new User("Bill"));
        // users.add(new User("Steve"));
    }

    public MainController(UserService userService, DictionaryService dictionaryService, ThemeService themeService,
            LevelOfEducationService levelOfEducationService) {
        this.userService = userService;
        this.dictionaryService = dictionaryService;
        this.themeService = themeService;
        this.levelOfEducationService = levelOfEducationService;
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/dictionaryList")
    public String dictionaryList(Model model) {
        model.addAttribute("dictionarys", dictionaryService.getAll());
        return "dictionaryList";
    }

    @PostMapping("/themesInDictionaries/{idDictionary}/add")
    public String addDictionary(Model model, @ModelAttribute("dictionary") Dictionary dictionary) {
        try {
            Dictionary newDictionary = dictionaryService.addDictionary(dictionary);
            return "redirect:/themesInDictionaries/" + newDictionary.getIdDictionary();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "themesInDictionaries";
        }
    }

    @GetMapping("/themesInDictionaries/{idDictionary}")
    public String themesInDictionaries(Model model, @PathVariable long idDictionary) {
        model.addAttribute("themesInDictionaries", themeService.getByIdDictionary(idDictionary));
        
        return "themesInDictionaries";
    }
    
    @GetMapping("/editingDictionaries")
    public String editingDictionaries(Model model) {
        model.addAttribute("dictionarys", dictionaryService.getAll());
        
        return "editingDictionaries";
    }
    @PostMapping("theme/add")
    public String addTheme(Model model, @ModelAttribute("tasks") Theme theme) {
        try {
            Theme newTheme = themeService.addTheme(theme);
            return "redirect:/themesInDictionaries/" + newTheme.getIdTheme();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "themesInDictionaries";
        }
    }

    @GetMapping(value = {"/theme/add"})
    public String showAddTheme(Model model) {
        Theme task = new Theme();
        model.addAttribute("add", true);
        model.addAttribute("theme", task);

        return "taskEdit";
    }

    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String viewPersonList(Model model) {
        // users.add(new User("Bill"));
        // users.add(new User("Steve"));
        // model.addAttribute("persons", users);


        return "personList";
    }
}
