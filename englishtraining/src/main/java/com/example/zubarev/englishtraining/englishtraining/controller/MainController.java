package com.example.zubarev.englishtraining.englishtraining.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.zubarev.englishtraining.englishtraining.model.Dictionary;
import com.example.zubarev.englishtraining.englishtraining.model.FieldOfActivity;
import com.example.zubarev.englishtraining.englishtraining.model.Role;
import com.example.zubarev.englishtraining.englishtraining.model.Theme;
import com.example.zubarev.englishtraining.englishtraining.model.Training;
import com.example.zubarev.englishtraining.englishtraining.model.User;
import com.example.zubarev.englishtraining.englishtraining.model.Word;
import com.example.zubarev.englishtraining.englishtraining.repos.TrainingRepos;
import com.example.zubarev.englishtraining.englishtraining.repos.UserRepo;
import com.example.zubarev.englishtraining.englishtraining.service.DictionaryService;
import com.example.zubarev.englishtraining.englishtraining.service.FieldOfActivityService;
import com.example.zubarev.englishtraining.englishtraining.service.LanguageProficiencyService;
import com.example.zubarev.englishtraining.englishtraining.service.LevelOfEducationService;
import com.example.zubarev.englishtraining.englishtraining.service.ThemeService;
import com.example.zubarev.englishtraining.englishtraining.service.TrainingService;
import com.example.zubarev.englishtraining.englishtraining.service.WordService;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private DictionaryService dictionaryService;
    private ThemeService themeService;
    private WordService wordService;
    private UserRepo userRepo;
    private TrainingService trainingService;
    private LevelOfEducationService levelOfEducationService;
    private LanguageProficiencyService languageProficiencyService;
    private FieldOfActivityService fieldOfActivityService;

    
    

    public MainController(DictionaryService dictionaryService, ThemeService themeService, WordService wordService,
            UserRepo userRepo, TrainingService trainingService, LevelOfEducationService levelOfEducationService,
            LanguageProficiencyService languageProficiencyService, FieldOfActivityService fieldOfActivityService) {
        this.dictionaryService = dictionaryService;
        this.themeService = themeService;
        this.wordService = wordService;
        this.userRepo = userRepo;
        this.trainingService = trainingService;
        this.levelOfEducationService = levelOfEducationService;
        this.languageProficiencyService = languageProficiencyService;
        this.fieldOfActivityService = fieldOfActivityService;
    }

    public void getUser(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();// get logged in username
            User user = (User) userRepo.findByUsername(name);
            model.addAttribute("user", user);
            if (user != null) {
                if (user.getRoles().toString().equals(Collections.singleton(Role.USER).toString())) {
                    model.addAttribute("role_user", true);
                } else {
                    model.addAttribute("role_admin", true);
                }
            }
            String role = auth.getAuthorities().toString();
            model.addAttribute("role", role);
        }
    }

    @GetMapping("/dictionaryList")
    public String dictionaryList(Model model) {
        getUser(model);
        model.addAttribute("dictionaryList", true);
        model.addAttribute("dictionarys", dictionaryService.getAll());
        return "list";
    }

    @GetMapping("/dictionaryList/add")
    public String dictionaryListAdd(Model model) {
        getUser(model);
        Dictionary dictionary = new Dictionary();
        model.addAttribute("add", true);
        model.addAttribute("dictionaryEdit", "true");
        model.addAttribute("dictionary", dictionary);
        return "edit";
    }

    @PostMapping("/dictionaryList/add")
    public String addTask(Model model, @ModelAttribute("dictionary") Dictionary dictionary) {
        getUser(model);
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
        getUser(model);
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
        getUser(model);
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
        getUser(model);
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

    @GetMapping(value = { "/dictionaryList/{dictionaryId}/delete" })
    public String showDeleteTask(
            Model model, @PathVariable long dictionaryId) {
        getUser(model);
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

    @PostMapping(value = { "/dictionaryList/{dictionayId}/delete" })
    public String deleteDictionaryById(
            Model model, @PathVariable long dictionayId) {
        getUser(model);
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
    public String themeAdd(Model model, @PathVariable long dictionaryId) {
        getUser(model);
        Theme theme = new Theme();
        model.addAttribute("add", true);
        model.addAttribute("themeEdit", "true");
        model.addAttribute("dictionaryId", dictionaryId);
        model.addAttribute("theme", theme);
        return "edit";
    }

    @PostMapping("/dictionaryList/{dictionaryId}/add")
    public String addTheme(Model model, @ModelAttribute("newTheme") Theme theme) {
        getUser(model);
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
        getUser(model);
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
        model.addAttribute("words", wordService.getWordsByThemeId(themeId));
        return "list";
    }

    @GetMapping("/dictionaryList/{dictionaryId}/{idTheme}/edit")
    public String editTheme(Model model, @PathVariable long dictionaryId, @PathVariable long idTheme) {
        getUser(model);
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
        getUser(model);
        try {
            Theme newTheme = themeService.addTheme(theme);
            return "redirect:/dictionaryList/" + idDictionary + "/" + newTheme.getIdTheme();
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

    @GetMapping(value = { "/dictionaryList/{dictionaryId}/{idTheme}/delete" })
    public String deleteTheme(
            Model model, @PathVariable long dictionaryId, @PathVariable long idTheme) {
        getUser(model);
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

    @PostMapping(value = { "/dictionaryList/{dictionaryId}/{idTheme}/delete" })
    public String deleteThemeById(
            Model model, @PathVariable long dictionaryId, @PathVariable long idTheme) {
        getUser(model);
        try {
            themeService.deleteThemeById(idTheme);
            return "redirect:/dictionaryList/" + dictionaryId;
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("dictionary", dictionaryService.getDictionaryById(dictionaryId));
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("themeElement", true);
            return "list";
        }
    }

    @GetMapping("/dictionaryList/{idDictionary}/{idTheme}/{idWord}")
    public String wordById(Model model, @PathVariable long idDictionary, @PathVariable long idTheme,
            @PathVariable long idWord) {
        getUser(model);
        Word word = null;
        try {
            word = wordService.getWordById(idWord);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("word", word);
        model.addAttribute("wordElement", "true");
        model.addAttribute("dictionary", dictionaryService.getDictionaryById(idDictionary));
        model.addAttribute("theme", themeService.getThemeById(idTheme));
        return "list";
    }

    @GetMapping("/dictionaryList/{idDictionary}/{idTheme}/add")
    public String wordAdd(Model model, @PathVariable long idDictionary, @PathVariable long idTheme) {
        getUser(model);
        Word word = new Word(idTheme, "", "", "");
        model.addAttribute("add", true);
        model.addAttribute("wordEdit", "true");
        model.addAttribute("idDictionary", idDictionary);
        model.addAttribute("idTheme", idTheme);
        model.addAttribute("word", word);
        return "edit";
    }

    @PostMapping("/dictionaryList/{dictionaryId}/{idTheme}/add")
    public String addWord(Model model, @ModelAttribute("newWord") Word word) {
        getUser(model);
        try {
            Word newWord = wordService.addWord(word);
            return "redirect:/dictionaryList/{dictionaryId}/{idTheme}/" + newWord.getIdWord();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("dictionaryEdit", "true");
            model.addAttribute("add", true);
            return "edit";
        }
    }

    @RequestMapping("/dictionaryList/{idDictionary}/{idTheme}/addExcel")
    public String import2db(MultipartFile file, @PathVariable long idDictionary, @PathVariable long idTheme)
            throws Exception {
        wordService.addWordsFromXLS(file, idTheme);
        return "redirect:/dictionaryList/{idDictionary}/{idTheme}/";
    }

    @GetMapping("/dictionaryList/{idDictionary}/{idTheme}/{idWord}/edit")
    public String editWord(Model model, @PathVariable long idDictionary, @PathVariable long idTheme,
            @PathVariable long idWord) {
        getUser(model);
        Dictionary dictionary = null;
        Theme theme = null;
        Word word = null;
        try {
            dictionary = dictionaryService.getDictionaryById(idDictionary);
            theme = themeService.getThemeById(idTheme);
            word = wordService.getWordById(idWord);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("wordEdit", true);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("theme", theme);
        model.addAttribute("word", word);
        return "edit";
    }

    @PostMapping("/dictionaryList/{idDictionary}/{idTheme}/{idWord}/edit")
    public String editWord(Model model, @PathVariable long idDictionary, @PathVariable long idTheme,
            @ModelAttribute("newWord") Word word) {
        getUser(model);
        try {
            Word newWord = wordService.addWord(word);
            return "redirect:/dictionaryList/" + idDictionary + "/" + idTheme + "/" + newWord.getIdWord();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("wordEdit", true);
            model.addAttribute("add", true);
            model.addAttribute("dictionary", dictionaryService.getDictionaryById(idDictionary));
            return "list";
        }
    }

    @GetMapping(value = { "/dictionaryList/{idDictionary}/{idTheme}/{idWord}/delete" })
    public String deleteWord(
            Model model, @PathVariable long idDictionary, @PathVariable long idTheme, @PathVariable long idWord) {
        getUser(model);
        Word word = null;
        try {
            word = wordService.getWordById(idWord);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("dictionary", dictionaryService.getDictionaryById(idDictionary));
        model.addAttribute("theme", themeService.getThemeById(idTheme));
        model.addAttribute("wordElement", true);
        model.addAttribute("allowDelete", true);
        model.addAttribute("word", word);
        return "list";
    }

    @PostMapping(value = { "/dictionaryList/{idDictionary}/{idTheme}/{idWord}/delete" })
    public String deleteWordById(
            Model model, @PathVariable long idDictionary, @PathVariable long idTheme, @PathVariable long idWord) {
        getUser(model);
        try {
            wordService.deleteWordById(idWord);
            return "redirect:/dictionaryList/" + idDictionary + "/" + idTheme;
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("dictionary", dictionaryService.getDictionaryById(idDictionary));
            model.addAttribute("theme", themeService.getThemeById(idTheme));
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("wordElement", true);

            return "list";
        }
    }

    @GetMapping(value = { "/training" })
    public String training(Model model) {
        getUser(model);

        model.addAttribute("theme", themeService.getAll());
        model.addAttribute("dictionary", dictionaryService.getAll());
        model.addAttribute("changeTraining", true);

        return "training";
    }

    @PostMapping(value = { "/saveTraining/" })
    public String saveTraining(
            Model model, @ModelAttribute("result") Training training) {
        getUser(model);
        Training newTraining= trainingService.addTraining(training);
    
            return "redirect:/stats";
        
    }

    @GetMapping(value = { "/stats" })
    public String trainingStats(Model model) {
        getUser(model);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();// get logged in username
        User user = (User) userRepo.findByUsername(name);
        model.addAttribute("user", user);
        if (user != null) {
            if (user.getRoles().toString().equals(Collections.singleton(Role.USER).toString())) {
                model.addAttribute("allTraining",  trainingService.getByIdUser(user.getId()));
            } else {
                model.addAttribute("allTraining", trainingService.getAll());
            }
        }
        model.addAttribute("dictionary", dictionaryService.getAll());
        model.addAttribute("trainingResult", true);

        return "training";
    }

    @GetMapping(value = { "/training/{idTheme}" })
    public String trainingTheme(Model model, @PathVariable long idTheme) {
        getUser(model);
        model.addAttribute("theme", themeService.getAll());
        model.addAttribute("words", wordService.getWordsByThemeId(idTheme));
        model.addAttribute("training", true);
        return "training";
    }

    @GetMapping("/")
    public String greeting(Model model) {
        getUser(model);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();// get logged in username
        User user = (User) userRepo.findByUsername(name);
        model.addAttribute("user", user);
        return "personalArea";
    }
    @GetMapping("/themes")
    public String themesUser(Model model) {
        getUser(model);
        model.addAttribute("themeElementUser", true);
        model.addAttribute("theme", themeService.getAll());
        model.addAttribute("dictionary", dictionaryService.getAll());
        return "list";
    }
    @GetMapping("/themes/{idTheme}")
    public String themeUser(Model model, @PathVariable long idTheme) {
        getUser(model);
        model.addAttribute("themElementUser", true);
        model.addAttribute("theme", themeService.findByIdTheme(idTheme));
        model.addAttribute("dictionary", dictionaryService.getAll());
        model.addAttribute("words", wordService.getWordsByThemeId(idTheme));


        return "list";
    }
}
