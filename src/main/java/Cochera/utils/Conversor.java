package Cochera.utils;

import javafx.scene.control.DatePicker;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Conversor {

    public static String sha256(String pass) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    public static Date stringToDate(String fecha) {
        try { return format.parse(fecha); }
        catch (ParseException e) { return null; }
    }

    public static Date datePickerToDate(DatePicker datePicker) {
        return Date.from(Instant.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault())));
    }

    public static LocalDate dateToDatePicker(Date fecha) {
        return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
