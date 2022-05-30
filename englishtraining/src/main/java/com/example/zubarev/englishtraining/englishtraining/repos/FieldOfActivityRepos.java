package com.example.zubarev.englishtraining.englishtraining.repos;
import com.example.zubarev.englishtraining.englishtraining.model.FieldOfActivity;

import org.springframework.data.repository.CrudRepository;

public interface FieldOfActivityRepos extends CrudRepository<FieldOfActivity,Long> {
    public Iterable<FieldOfActivityRepos> findByIdFieldOfActivityRepos(Long idFieldOfActivityRepos);
}
