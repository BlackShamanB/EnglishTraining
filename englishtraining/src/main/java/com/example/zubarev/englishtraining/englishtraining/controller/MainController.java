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
import com.example.zubarev.englishtraining.englishtraining.model.Role;
import com.example.zubarev.englishtraining.englishtraining.model.Theme;
import com.example.zubarev.englishtraining.englishtraining.model.User;
import com.example.zubarev.englishtraining.englishtraining.model.Word;
import com.example.zubarev.englishtraining.englishtraining.repos.UserRepo;
import com.example.zubarev.englishtraining.englishtraining.service.DictionaryService;
import com.example.zubarev.englishtraining.englishtraining.service.LevelOfEducationService;
import com.example.zubarev.englishtraining.englishtraining.service.ThemeService;
import com.example.zubarev.englishtraining.englishtraining.service.WordService;

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
    private LevelOfEducationService levelOfEducationService;
    private UserRepo userRepo;


    public MainController(DictionaryService dictionaryService, ThemeService themeService,
            WordService wordSercive,
            LevelOfEducationService levelOfEducationService,  UserRepo userRepo) {
        this.dictionaryService = dictionaryService;
        this.themeService = themeService;
        this.wordService = wordSercive;
        this.levelOfEducationService = levelOfEducationService;
        this.userRepo = userRepo;
    }


    @GetMapping("/admin/dictionaryList")
    public String dictionaryList(Model model) {
        model.addAttribute("dictionaryList", true);
        model.addAttribute("dictionarys", dictionaryService.getAll());
        return "list";
    }

    @GetMapping("/admin/dictionaryList/add")
    public String dictionaryListAdd(Model model) {
        Dictionary dictionary = new Dictionary();
        model.addAttribute("add", true);
        model.addAttribute("dictionaryEdit", "true");
        model.addAttribute("dictionary", dictionary);
        return "edit";
    }

    @PostMapping("/admindictionaryList/add")
    public String addTask(Model model, @ModelAttribute("dictionary") Dictionary dictionary) {
        try {
            Dictionary newDictionary = dictionaryService.addDictionary(dictionary);
            return "redirect:/admin/dictionaryList/" + newDictionary.getIdDictionary();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("dictionaryEdit", "true");
            model.addAttribute("add", true);
            return "edit";
        }
    }

    @GetMapping("/admin/dictionaryList/{dictionaryId}")
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

    @GetMapping("/admin/dictionaryList/{dictionaryId}/edit")
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

    @PostMapping("/admin/dictionaryList/{idDictionary}/edit")
    public String editDictionary(Model model, @ModelAttribute("dictionary") Dictionary dictionary) {
        try {
            Dictionary newDictionary = dictionaryService.addDictionary(dictionary);
            return "redirect:/admin/dictionaryList/" + newDictionary.getIdDictionary();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("dictionaryEdit", true);
            model.addAttribute("add", true);
            return "list";
        }
    }

    @GetMapping(value = { "/admin/dictionaryList/{dictionaryId}/delete" })
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

    @PostMapping(value = { "/admin/dictionaryList/{dictionayId}/delete" })
    public String deleteDictionaryById(
            Model model, @PathVariable long dictionayId) {
        try {
            dictionaryService.deleteDictionaryById(dictionayId);
            return "redirect:/admin/dictionaryList";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "list";
        }
    }

    @GetMapping("/admin/dictionaryList/{dictionaryId}/add")
    public String themeAdd(Model model, @PathVariable long dictionaryId) {
        Theme theme = new Theme();
        model.addAttribute("add", true);
        model.addAttribute("themeEdit", "true");
        model.addAttribute("dictionaryId", dictionaryId);
        model.addAttribute("theme", theme);
        return "edit";
    }

    @PostMapping("/admin/dictionaryList/{dictionaryId}/add")
    public String addTheme(Model model, @ModelAttribute("newTheme") Theme theme) {
        try {
            Theme newTheme = themeService.addTheme(theme);
            return "redirect:/admin/dictionaryList/{dictionaryId}/" + newTheme.getIdTheme();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("dictionaryEdit", "true");
            model.addAttribute("add", true);
            return "edit";
        }
    }

    @GetMapping("/admin/dictionaryList/{dictionaryId}/{themeId}")
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
        model.addAttribute("words", wordService.getWordsByThemeId(themeId));
        return "list";
    }

    @GetMapping("/admin/dictionaryList/{dictionaryId}/{idTheme}/edit")
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

    @PostMapping("/admin/dictionaryList/{idDictionary}/{idTheme}/edit")
    public String editTheme(Model model, @PathVariable long idDictionary, @ModelAttribute("newTheme") Theme theme) {
        try {
            Theme newTheme = themeService.addTheme(theme);
            return "redirect:/admin/dictionaryList/" + idDictionary + "/" + newTheme.getIdTheme();
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

    @GetMapping(value = { "/admin/dictionaryList/{dictionaryId}/{idTheme}/delete" })
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

    @PostMapping(value = { "/admin/dictionaryList/{dictionaryId}/{idTheme}/delete" })
    public String deleteThemeById(
            Model model, @PathVariable long dictionaryId, @PathVariable long idTheme) {
        try {
            themeService.deleteThemeById(idTheme);
            return "redirect:/admin/dictionaryList/" + dictionaryId;
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("dictionary", dictionaryService.getDictionaryById(dictionaryId));
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("themeElement", true);
            return "list";
        }
    }

    @GetMapping("/admin/dictionaryList/{idDictionary}/{idTheme}/{idWord}")
    public String wordById(Model model, @PathVariable long idDictionary, @PathVariable long idTheme,
            @PathVariable long idWord) {
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

    @GetMapping("/admin/dictionaryList/{idDictionary}/{idTheme}/add")
    public String wordAdd(Model model, @PathVariable long idDictionary, @PathVariable long idTheme) {
        Word word = new Word(idTheme, "", "", "");
        model.addAttribute("add", true);
        model.addAttribute("wordEdit", "true");
        model.addAttribute("idDictionary", idDictionary);
        model.addAttribute("idTheme", idTheme);
        model.addAttribute("word", word);
        return "edit";
    }

    @PostMapping("/admin/dictionaryList/{dictionaryId}/{idTheme}/add")
    public String addWord(Model model, @ModelAttribute("newWord") Word word) {
        try {
            Word newWord = wordService.addWord(word);
            return "redirect:/admin/dictionaryList/{dictionaryId}/{idTheme}/" + newWord.getIdWord();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("dictionaryEdit", "true");
            model.addAttribute("add", true);
            return "edit";
        }
    }

    @RequestMapping("/admin/dictionaryList/{idDictionary}/{idTheme}/addExcel")
    public String import2db(MultipartFile file, @PathVariable long idDictionary, @PathVariable long idTheme) throws Exception {
        wordService.addWordsFromXLS(file, idTheme);
        return "redirect:/admin/dictionaryList/{idDictionary}/{idTheme}/";
    }

    @GetMapping("/admin/dictionaryList/{idDictionary}/{idTheme}/{idWord}/edit")
    public String editWord(Model model, @PathVariable long idDictionary, @PathVariable long idTheme,
            @PathVariable long idWord) {
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

    @PostMapping("/admin/dictionaryList/{idDictionary}/{idTheme}/{idWord}/edit")
    public String editWord(Model model, @PathVariable long idDictionary, @PathVariable long idTheme,
            @ModelAttribute("newWord") Word word) {
        try {
            Word newWord = wordService.addWord(word);
            return "redirect:/admin/dictionaryList/" + idDictionary + "/" + idTheme + "/" + newWord.getIdWord();
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

    @GetMapping(value = { "/admin/dictionaryList/{idDictionary}/{idTheme}/{idWord}/delete" })
    public String deleteWord(
            Model model, @PathVariable long idDictionary, @PathVariable long idTheme, @PathVariable long idWord) {
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

    @PostMapping(value = { "/admin/dictionaryList/{idDictionary}/{idTheme}/{idWord}/delete" })
    public String deleteWordById(
            Model model, @PathVariable long idDictionary, @PathVariable long idTheme, @PathVariable long idWord) {
        try {
            wordService.deleteWordById(idWord);
            return "redirect:/admin/dictionaryList/" + idDictionary + "/" + idTheme;
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
        
        model.addAttribute("theme", themeService.getAll());
        model.addAttribute("dictionary", dictionaryService.getAll());
        model.addAttribute("changeTraining", true);
        return "training";
    }
    @GetMapping(value = { "/training/{idTheme}" })
    public String trainingTheme(Model model, @PathVariable long idTheme) {
        
        model.addAttribute("theme", themeService.getAll());
        model.addAttribute("words", wordService.getWordsByThemeId(idTheme));
        model.addAttribute("training", true);
        return "training";
    }

    @GetMapping("/")
    public String greeting(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUsername( auth.getName());
        if(user.getRoles().iterator() == Collections.singleton(Role.ADMIN)){
            model.addAttribute("admin", true);
            return "redirect:/admin";
        }
        else{
            model.addAttribute("user", true);
            return "redirect:/user";
        }
    }
    @GetMapping("/admin")
    public String greetingAdmin(Model model) {
        return "index";
    }

    @GetMapping("/user")
    public String greetingUser(Model model) {
        return "index";
    }


    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Dictionary> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = dictionaryService.getAll();
        } else {
            messages = dictionaryService.getAll();
        }

        model.put("messages", messages);

        return "main";
    }
}
