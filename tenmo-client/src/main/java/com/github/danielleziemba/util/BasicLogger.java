package com.github.danielleziemba.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BasicLogger {
    private static PrintWriter pw = null;

    public static void log(String message) {
        try {
            if (pw == null) {
                String logFileName = "tenmo-client/logs/" + LocalDate.now().format(DateTimeFormatter.ISO_DATE) + ".log";
                pw = new PrintWriter(new FileOutputStream(logFileName, true));
            }
            pw.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " " + message);
            pw.flush();
        } catch (FileNotFoundException e) {
            throw new BasicLoggerException(e.getMessage());
        }
    }
}
