//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

/**
 * A logging utility to replace the frequent use of System.out and System.err
 * 
 * Unlike the standard java output classes (which it will use) this will not 
 * only send message to the standard output and standard error but also record
 * them in log files for future examination.
 * 
 * I should really learn to use the java.util.logger, but that could be 
 * connected.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class Logger {
    private boolean concatinate;
    private boolean preserve;
    
    private final String dir = "logs";
    private final String namebase = "gamelog";
    private final String errorbase = "errorlog";
    private final String extension = ".log";
    
    private File gamelog;
    private File errorlog;
    
    private BufferedWriter logwriter;
    private BufferedWriter errorwriter;
    
    
    protected Logger() {
        concatinate = false;
        preserve = false;
        //TODO: Implement the preserve feature by finding next valid name
        gamelog  = new File(dir + File.separator + namebase + extension);
        errorlog = new File(dir + File.separator + errorbase + extension);
        try {
            File directory = new File(dir);
            if(!directory.exists()) directory.mkdir();
            if(directory.exists()) 
                if(!directory.isDirectory()) {
                    System.err.println("CRITICAL ERROR: Error with log directory!");
                    System.exit(1);
                }
            if(gamelog.exists()) {
                if(gamelog.isDirectory()) {
                    System.err.println("CRITICAL ERROR: Log file is directory!");
                    System.exit(1);
                }
                else if(!concatinate) gamelog.delete();
            }
            logwriter = new BufferedWriter(new FileWriter(gamelog));
            if(errorlog.exists()) {
                if(errorlog.isDirectory()) {
                    System.err.println("CRITICAL ERROR: Erro log is directory!");
                    System.exit(1);                
                }
                else if(!concatinate) errorlog.delete();
            }
            errorwriter = new BufferedWriter(new FileWriter(errorlog));
        } catch(Exception e) {
                    System.err.println("CRITICAL ERROR: failed to create logs!");
                    System.exit(1);
        } finally {/*Do nothing; close log with finalizer*/}
    }
    
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        logwriter.flush();
        logwriter.close();
        errorwriter.flush();
        errorwriter.close();
    }
    
    
    public synchronized void report(String text) {
        try {
            logwriter.write(text);
            logwriter.newLine();
            System.out.println(text);
            logwriter.flush();
        } catch (IOException ex) {
            System.err.println("CRITICAL ERROR: failed to write logs!");
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
    
    public synchronized void reportError(String text) {
        try {
            logwriter.write(text);
            logwriter.newLine();
            errorwriter.write(text);
            errorwriter.newLine();
            System.err.println(text);
            logwriter.flush();
            errorwriter.flush();
        } catch (IOException ex) {
            System.err.println("CRITICAL ERROR: failed to write logs!");
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
}
