package com.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class DateFormatter {

   public final static ZoneId ZONE = ZoneId.systemDefault();

    public static Date localDateTimeToDate(LocalDateTime ldt) {
        Instant instant = ldt.atZone(ZONE).toInstant();
        return Date.from(instant);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZONE);
    }

}