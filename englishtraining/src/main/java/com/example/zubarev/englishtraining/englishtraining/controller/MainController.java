package com.example.zubarev.englishtraining.englishtraining.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

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
        model.addAttribute("dictionaryList", true);
        model.addAttribute("dictionarys", dictionaryService.getAll());
        return "list";
    }

    @GetMapping("/dictionaryList/add")
    public String dictionaryListAdd(Model model) {
        Dictionary dictionary = new Dictionary();
        model.addAttribute("add", true);
        model.addAttribute("dictionaryEdit", "true");
        model.addAttribute("dictionary", dictionary);
        return "edit";
    }

    @PostMapping("dictionaryList/add")
    public String addTask(Model model, @ModelAttribute("dictionary") Dictionary dictionary) {
        try {
            Dictionary newDictionary = dictionaryService.addDictionary(dictionary);
            return "redirect:/dictionaryList/" + newDictionary.getIdDictionary();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("dictionaryEdit", "true");
            model.addAttribute("add", true);
            return "edit";
        }
    }

    @GetMapping("/dictionaryList/{dictionaryId}")
    public String dictionaryBuId(Model model, @PathVariable long dictionaryId) {
        Dictionary dictionary = null;
        try {
            dictionary = dictionaryService.getDictionaryById(dictionaryId);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("dictionaryElement", true);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("themes", themeService.getByIdDictionary(dictionaryId));
        return "list";
    }

    @GetMapping("/dictionaryList/{dictionaryId}/edit")
    public String showEditDictionary(Model model, @PathVariable long dictionaryId) {
        Dictionary dictionary = null;
        try {
            dictionary = dictionaryService.getDictionaryById(dictionaryId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("dictionaryEdit", true);
        model.addAttribute("dictionary", dictionary);
        return "edit";
    }

    @PostMapping("/dictionaryList/{idDictionary}/edit")
    public String editDictionary(Model model, @ModelAttribute("dictionary") Dictionary dictionary) {
        try {
            Dictionary newDictionary = dictionaryService.addDictionary(dictionary);
            return "redirect:/dictionaryList/" + newDictionary.getIdDictionary();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("dictionaryEdit", true);
            model.addAttribute("add", true);
            return "list";
        }
    }

    @GetMapping(value = {"/dictionaryList/{dictionaryId}/delete"})
    public String showDeleteTask(
            Model model, @PathVariable long dictionaryId) {
        Dictionary dictionary = null;
        try {
            dictionary = dictionaryService.getDictionaryById(dictionaryId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("dictionaryElement", true);
        model.addAttribute("allowDelete", true);
        model.addAttribute("dictionary", dictionary);
        return "list";
    }

    @PostMapping(value = {"/dictionaryList/{dictionayId}/delete"})
    public String deleteTaskById(
            Model model, @PathVariable long dictionayId) {
        try {
            dictionaryService.deleteDictionaryById(dictionayId);
            return "redirect:/dictionaryList";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "list";
        }
    }


    @GetMapping("/dictionaryList/{dictionaryId}/add")
    public String themeAdd(Model model,@PathVariable long dictionaryId) {
        Theme theme = new Theme();
        model.addAttribute("add", true);
        model.addAttribute("themeEdit", "true");
        model.addAttribute("dictionaryId", dictionaryId);
        model.addAttribute("theme", theme);
        return "edit";
    }

    @PostMapping("/dictionaryList/{dictionaryId}/add")
    public String addTheme(Model model, @ModelAttribute("newTheme") Theme theme) {
        try {
            Theme newTheme = themeService.addTheme(theme);
            return "redirect:/dictionaryList/{dictionaryId}/" + newTheme.getIdTheme();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("dictionaryEdit", "true");
            model.addAttribute("add", true);
            return "edit";
        }
    }

    @GetMapping("/dictionaryList/{dictionaryId}/{themeId}")
    public String themeById(Model model, @PathVariable long dictionaryId, @PathVariable long themeId) {
        Theme theme = null;
        try {
            theme = themeService.getThemeById(themeId);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("theme", theme);
        model.addAttribute("themeElement", "true");
        model.addAttribute("dictionary", dictionaryService.getDictionaryById(dictionaryId));
        // model.addAttribute("themes", themeService.getAll());
        return "list";
    }

    @GetMapping("/dictionaryList/{dictionaryId}/{idTheme}/edit")
    public String editTheme(Model model, @PathVariable long dictionaryId, @PathVariable long idTheme) {
        Dictionary dictionary = null;
        Theme theme = null;
        try {
            dictionary = dictionaryService.getDictionaryById(dictionaryId);
            theme = themeService.getThemeById(idTheme);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("themeEdit", true);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("theme", theme);
        return "edit";
    }

    @PostMapping("/dictionaryList/{idDictionary}/{idTheme}/edit")
    public String editTheme(Model model, @PathVariable long idDictionary, @ModelAttribute("newTheme") Theme theme) {
        try {
            Theme newTheme = themeService.addTheme(theme);
            return "redirect:/dictionaryList/" +idDictionary+"/"+ newTheme.getIdTheme();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("themeEdit", true);
            model.addAttribute("add", true);
            model.addAttribute("dictionary", dictionaryService.getDictionaryById(idDictionary));
            return "list";
        }
    }

    @GetMapping(value = {"/dictionaryList/{dictionaryId}/{idTheme}/delete"})
    public String deleteTheme(
            Model model, @PathVariable long dictionaryId, @PathVariable long idTheme) {
        Theme theme = null;
        try {
            theme = themeService.getThemeById(idTheme);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("dictionary", dictionaryService.getDictionaryById(dictionaryId));
        model.addAttribute("themeElement", true);
        model.addAttribute("allowDelete", true);
        model.addAttribute("theme", theme);
        return "list";
    }

    @PostMapping(value = {"/dictionaryList/{dictionaryId}/{idTheme}/delete"})
    public String deleteThemeById(
            Model model, @PathVariable long dictionaryId, @PathVariable long idTheme) {
        try {
            themeService.deleteThemeById(idTheme);
            return "redirect:/dictionaryList/"+dictionaryId;
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("dictionary", dictionaryService.getDictionaryById(dictionaryId));
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("themeElement", true);
            return "list";
        }
    }
}
