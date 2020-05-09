package com.example.suabackend.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Utils {
    public static final String CROSS_ORIGIN_URL = "http://localhost:4200";
    public static final String BASE_URL = "/api/";


    /**
     * Write text to file
     * @param fileName file path
     * @param content contrct string
     * @return true if file wrote successfully
     */
    public static boolean writeFile(String fileName, String content){
        try {
            Files.write(Paths.get(fileName), content.getBytes());
        }catch (IOException e){
            return false;
        }
        return true;
    }

    /**
     * Read text file
     * @param fileName file path
     * @return return content string of file
     */
    public static String readFile(String fileName){
        try {
            return String.join("\n",Files.readAllLines(Paths.get(fileName)));
        }catch (IOException ignored){
        }
        return null;
    }

    /**
     * Check given date in range of given two dates
     * @param date date which should be checked
     * @param from date which range begins
     * @param to date which range ends
     * @return returns true if date in range else false
     */
    public static boolean inDateRange(Date date, Date from, Date to){
        return from.equals(date) || to.equals(date) || (from.after(date) && to.before(date));
    }

    /**
     * Check given date strings in range of given two dates
     * @param date date string which should be checked
     * @param from date string which range begins
     * @param to date string which range ends
     * @return returns true if date in range else false
     */
    public static boolean inDateRange(String date, String from, String to){
        return inDateRange(Date.valueOf(date), Date.valueOf(from), Date.valueOf(to));
    }

    /**
     * Returns today
     * @return today java.sql.Date
     */
    public static Date getToday(){
        return Date.valueOf(LocalDate.now());
    }

    /**
     * Print important message inside a rectangle of characters
     * @param msg String
     */
    public static void printImportantMessage(String msg){
        int horizontalPadding = 10;
        int width = horizontalPadding*2 + msg.length();
        String line1 = "", line2 = "", line3 = "";
        for(int i=0; i<width; i++){
            line1 += "-";
            line2 += " ";
        }
        line1 = "+" + line1 + "+";
        line2 = "|" + line2 + "|";
        for(int i=0; i<horizontalPadding; i++)
            line3 += " ";
        line3 = "|" + line3 + msg + line3 + "|";
        System.out.println();
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line2);
        System.out.println(line1);
        System.out.println();
    }

    /**
     * If date string has unnecessary characters remove them
     * Ex:- Ex:- "2020-02-10T09:08:50.482Z" -{@literal >} "2020-02-10"
     * @param dateStr data with extra characters
     * @return fixed date string
     * @throws Exception Invalid date string exception ( if length {@literal <} 10 )
     */
    public static String fixDateString(String dateStr) throws Exception {
        if(dateStr.length()>=10)
            dateStr = dateStr.substring(0, 10);
        else
            throw new Exception("Invalid date string");
        return dateStr;
    }

    /**
     * Save given string in clipboard
     * @param s string
     */
    public static void setClipboard(String s){
        StringSelection stringSelection = new StringSelection(s);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     * Get string in the clipboard
     * @return text in clipboard, returns empty if error occurred
     */
    public static String getClipboard(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        try {
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        }catch (IOException | UnsupportedFlavorException e) {
            return "";
        }
    }

    /**
     * Returns true if two lists are quals
     * @param listA ArrayList
     * @param listB ArrayList
     * @return boolean
     */
    public static boolean isEqualLists (ArrayList listA, ArrayList listB){
        return listA.size() == listB.size() && listA.containsAll(listB);
    }

    public static boolean isEqualLists (List listA, List listB){
        return isEqualLists(new ArrayList(listA), new ArrayList(listB));
    }


    public static void sendmail(String email, String subject, String htmlContent) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("noreply.generated.email@gmail.com", "abcd#1234");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("noreply.generated.email@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("zchandikaz@gmail.com"));
        msg.setSubject(subject);
        msg.setContent(htmlContent, "text/html");
        msg.setSentDate(Date.valueOf(LocalDateTime.now().toLocalDate()));

        Transport.send(msg);
    }

    public static String sendVerifyEmail(String email){
        String otp = getRandomString();

        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get("files/email-verify-template.html")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        content = String.format(content, otp, otp);

        try {
            Utils.sendmail(email, "Test", content);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return otp;
    }

    public static String getRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
