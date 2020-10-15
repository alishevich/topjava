package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.Collection;

@Service
public class MealService {

    private final InMemoryMealRepository repository;

    @Autowired
    public MealService(InMemoryMealRepository repository) {
     this.repository = repository;
    }

    public Meal create(Meal meal, int userId){
        return repository.save(meal, userId);
    }

    public Meal get(int id, int userId){
       return  ValidationUtil.checkNotFoundWithId(repository.get(id, userId), id);

    }

    public Collection<Meal> getAll(int userId) {
       return repository.getAll(userId);
    }

    public void update(Meal meal, int id,  int userId) {
        ValidationUtil.checkNotFoundWithId(repository.save(meal, userId), id);
    }

    public void delete(int id, int userId){
         ValidationUtil.checkNotFoundWithId(repository.delete(id, userId), id);
    }



}