package ru.javawebinar.topjava.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Date;

public class DateTimeUtil {
    public static LocalDateTime convertToLocalDateTime(String dateToConvert) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}