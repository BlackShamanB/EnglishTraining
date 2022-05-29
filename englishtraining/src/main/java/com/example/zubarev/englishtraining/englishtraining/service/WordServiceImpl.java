package com.example.zubarev.englishtraining.englishtraining.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.zubarev.englishtraining.englishtraining.model.Word;
import com.example.zubarev.englishtraining.englishtraining.repos.WordRepos;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public void addWordsFromXLS(MultipartFile file, Long idTheme) throws IOException {
        List<Word> list = new ArrayList<Word>();
        Workbook wb = null;
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        // Преобразовать файл во входной поток
        InputStream inputStream = file.getInputStream();
        // Определяем, является ли это суффиксом xlsx
        if (suffix.equals("xlsm")) {
            wb = new XSSFWorkbook(inputStream);
        } else {
            wb = new HSSFWorkbook(inputStream);
        }
        // Получить первый лист
        Sheet sheet = wb.getSheetAt(1);
        if (sheet != null) {
            // Данные начинаются с третьего ряда, поэтому здесь они пересекаются с третьего
            // ряда
            for (int line = 0; line <= 3; line++) {
                Word word = new Word();
                Row row = sheet.getRow(line);
                if (row == null)
                    continue;
                // Определяем, является ли тип ячейки текстовым
                // if (1 != row.getCell(1).getCellType()) {
                //     continue;
                // }

                word.setWord(row.getCell(1).getStringCellValue());
                // if (1 != row.getCell(2).getCellType()) {
                //     continue;
                // }
                word.setTranscription(row.getCell(2).getStringCellValue());

                // if (1 != row.getCell(3).getCellType()) {
                //     continue;
                // }
                word.setTranslate(row.getCell(3).getStringCellValue());
                word.setIdTheme(idTheme);
                list.add(word);
            }
        }
        // Здесь я использую mybatis для вставки полученного набора данных в базу данных
        for (Word word : list) {
            wordRepos.save(word);
        }
        // Возвращает сколько строк вставлено
    }

    @Override
    public Iterable<Word> getWordsByThemeId(Long idTheme) {
        // TODO Auto-generated method stub
        return wordRepos.findByIdTheme(idTheme);
    }

}
