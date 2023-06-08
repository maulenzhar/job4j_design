package ru.job4j.ood.srp.hw.report.formatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XmlReportDateTimeParser implements DateTimeParser<Calendar> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @Override
    public String parse(Calendar calendar) {
        return DATE_FORMAT.format(calendar.getTime());
    }
}
