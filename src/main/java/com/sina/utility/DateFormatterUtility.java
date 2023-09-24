package com.sina.utility;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class DateFormatterUtility {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public LocalDate convertDate(String date) {
        if (date == null || date.isBlank()) {
            return null;
        }
        try {
            return dateFormat.parse(date).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } catch (ParseException e) {
            return null;
        }
    }
}
