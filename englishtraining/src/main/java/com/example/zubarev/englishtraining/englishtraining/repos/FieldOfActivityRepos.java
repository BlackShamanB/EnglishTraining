package com.example.zubarev.englishtraining.englishtraining.repos;
import java.util.List;

import com.example.zubarev.englishtraining.englishtraining.model.FieldOfActivity;
import com.example.zubarev.englishtraining.englishtraining.service.FieldOfActivityService;

import org.springframework.data.repository.CrudRepository;

public interface FieldOfActivityRepos extends CrudRepository<FieldOfActivity,Long> {
    List<FieldOfActivity> findByIdFieldOfActivity(Long idFieldOfActivity);
}
