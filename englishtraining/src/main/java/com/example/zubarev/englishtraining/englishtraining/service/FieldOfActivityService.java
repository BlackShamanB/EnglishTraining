package com.example.zubarev.englishtraining.englishtraining.service;
import java.util.List;

import com.example.zubarev.englishtraining.englishtraining.model.FieldOfActivity;
public interface FieldOfActivityService {
    public List<FieldOfActivity> findByIdFieldOfActivity(Long idFieldOfActivity);
    public List<FieldOfActivity> getAll();
    public FieldOfActivity add(FieldOfActivity fieldOfActivity);
}

