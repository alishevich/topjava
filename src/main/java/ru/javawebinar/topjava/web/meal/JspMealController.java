package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
      int id = getId(request);
      super.delete(id);
      return "redirect:/meals";
    }

    @GetMapping("/create")
    public String create(Model model) {
      Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
      model.addAttribute("meal", meal);
      return "mealForm";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
      int id= Integer.parseInt(request.getParameter("id"));
      Meal meal = super.get(id);
      model.addAttribute("meal", meal);
      return "mealForm";
    }

    @GetMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = DateTimeUtil.parseLocalDate(request.getParameter("startDate"));
        LocalTime startTime = DateTimeUtil.parseLocalTime(request.getParameter("startTime"));
        LocalDate endDate = DateTimeUtil.parseLocalDate(request.getParameter("endDate"));
        LocalTime endTime = DateTimeUtil.parseLocalTime(request.getParameter("endTime"));

        model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));

        return "meals";
    }

    @PostMapping()
    public String createAndUpdate(HttpServletRequest request) throws UnsupportedEncodingException {
       Meal meal = new Meal(
            LocalDateTime.parse(request.getParameter("dateTime")),
               request.getParameter("description"),
            Integer.parseInt(request.getParameter("calories")));

       if (!StringUtils.hasText(request.getParameter("id"))) {
           super.create(meal);
       } else {
           super.update(meal, getId(request));
       }
       return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
