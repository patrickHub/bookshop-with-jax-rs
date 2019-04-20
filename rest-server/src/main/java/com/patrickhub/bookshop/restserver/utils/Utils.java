/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

/**
 *
 * @author PatrickHub
 */
public class Utils {
     public static Date formatStringToDate(String str) {
        Date date = null;
        try {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
                date =  format.parse(str);
        } catch (ParseException e) {
            Logger.getLogger(Utils.class.getName()).severe(e.getMessage());
        }
        return date;
    }
     public static String formatDateToString(Date date) {
        String str = null;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        str =  format.format(str);
        return str;
    }
}
