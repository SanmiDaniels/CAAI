/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 *
 * @author MoFoLuWaSo
 */
public class DateFormaterUtil {

    public static String todaysDate() {

        Date today = new Date();
        Long day = today.getTime();
        Formatter fmt = new Formatter();
        Formatter f = fmt.format("%tY-%tm-%td %tT", day, day, day, day);

        return f.toString();
    }

    public static String checkYear(String theDate) {

        String theDateNow = theDate;

        return theDateNow.substring(theDateNow.length() - 4, theDateNow.length()).trim();
    }

    public static Date convertToYear(String sDate) {

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy");

        try {

            Date theDate = fmt.parse(sDate);
            
            return theDate;
            
        } catch (Exception e) {
            
            return null;
        }

    }
    
    public static Date todaySQLDate(){
        Date today = new Date();
        try {
            
         today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todaysDate());
        return today;
        } catch (Exception e) {
        }
        return today;
    }
    
    public static Date sqlDate(String date) throws ParseException{
         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}
