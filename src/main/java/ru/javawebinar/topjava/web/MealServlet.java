package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private static final MealDAOImpl dao = new MealDAOImpl();
    private static final LocalTime TIME_MIN = LocalTime.of(7, 0);
    private static final LocalTime TIME_MAX = LocalTime.of(12, 0);
    private static final int CALORIES_MAX = 2000;
    private static final String MEALS = "/meals.jsp";
    private static final String EDIT_PAGE = "/formEditMeal.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("forward to meal");

        String forward = "";
        String action = req.getParameter("action");
        List<MealTo> meals = MealsUtil.filteredByStreams(dao.getAllMeals(), TIME_MIN, TIME_MAX, CALORIES_MAX);
        if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            forward = MEALS;
            req.setAttribute("meals", meals);
        } else if (action.equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Meal meal = dao.getById(id);
            forward = EDIT_PAGE;
            req.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeals")) {
            forward = MEALS;
            req.setAttribute("meals", meals);
        } else {
            forward = EDIT_PAGE;
        }
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("post");

        req.setCharacterEncoding("UTF-8");

        LocalDateTime ldt = DateTimeUtil.convertToLocalDateTime(req.getParameter("date"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        String id = req.getParameter("id");

        Meal meal = new Meal(ldt, description, calories);

        if (id == null || id.isEmpty()) {
            dao.add(meal);
        } else {
            meal.setId(Integer.parseInt(id));
            dao.update(meal);
        }

        List<MealTo> meals = MealsUtil.filteredByStreams(dao.getAllMeals(), TIME_MIN, TIME_MAX, 2000);
        req.setAttribute("meals", meals);
        req.getRequestDispatcher(MEALS).forward(req, resp);
    }
}
