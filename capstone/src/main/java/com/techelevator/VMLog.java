package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class VMLog {
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    static LocalDateTime now = LocalDateTime.now();
    public static void log(String message) {
        File vendorLog = new File("logs/log.txt");
        try (PrintWriter logAppend = new PrintWriter(
                new FileOutputStream(vendorLog, true)
        )) {
            logAppend.println(">"+dtf.format(now)+" "+ message);
        } catch (FileNotFoundException e) {
            System.err.println("Log file not found. Machine cannot start without log file. " + e.getMessage());
        }
    }


}
