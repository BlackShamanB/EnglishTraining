package com.example.zubarev.englishtraining.englishtraining.controller;



import com.example.zubarev.englishtraining.englishtraining.model.Theme;
import com.example.zubarev.englishtraining.englishtraining.service.DictionaryService;
import com.example.zubarev.englishtraining.englishtraining.service.ThemeService;
import com.example.zubarev.englishtraining.englishtraining.service.UserService;

import org.hibernate.annotations.common.util.impl.Log_.logger;
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
    private UserService userService;
    private DictionaryService dictionaryService;
    private ThemeService themeService;

    public MainController(UserService userService, DictionaryService dictionaryService, ThemeService themeService) {
        this.userService = userService;
        this.dictionaryService = dictionaryService;
        this.themeService = themeService;
    }
    // static {
    //     users.add(new User("Bill"));
    //     users.add(new User("Steve"));
    // }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/dictionaryList")
    public String dictionarys(Model model) {
        model.addAttribute("dictionarys", dictionaryService.getAll());
        
        return "dictionaryList";
    }

    @GetMapping(value = {"/dictionaryList/add"})
    public String showAddDictionary(Model model) {
        Dictionary task = new Task("", "", LocalDate.now(), LocalTime.now(), "");
        model.addAttribute("add", true);
        model.addAttribute("tasks", task);

        return "taskEdit";
    }

    @GetMapping("/dictionariesByThemes/{idDictionary}")
    public String dictionariesByThemes(Model model, @PathVariable long idDictionary) {
        model.addAttribute("dictionariesByThemes", themeService.getByIdDictionary(idDictionary));
        
        return "dictionariesByThemes";
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
            return "redirect:/dictionariesByThemes/" + newTheme.getIdTheme();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "dictionariesByThemes";
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
