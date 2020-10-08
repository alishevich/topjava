package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDAOImpl implements MealDAO {
    private static final AtomicInteger MEAL_ID = new AtomicInteger(0);
    private static final Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    static {
        for (Meal meal : MealList.getMealList()) {
            meal.setId(MEAL_ID.incrementAndGet());
            meals.put(meal.getId(), meal);
        }
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public void add(Meal meal) {
        meal.setId(MEAL_ID.incrementAndGet());
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }
}
