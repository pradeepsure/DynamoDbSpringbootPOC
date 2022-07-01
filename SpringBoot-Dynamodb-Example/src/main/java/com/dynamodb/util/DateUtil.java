package com.dynamodb.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateUtil {
    public static LocalDate getFirstDateOfCurrentYear(LocalDate date) {
        return convertToLocalDateViaInstant(toDate(date.with(
                TemporalAdjusters.firstDayOfYear())));
    }

    public static Date toDate(LocalDate date) {
        Instant instant = date.atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date toDate(LocalDateTime date) {
        Instant instant = date.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
