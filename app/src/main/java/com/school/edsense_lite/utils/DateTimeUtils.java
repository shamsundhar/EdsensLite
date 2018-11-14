package com.school.edsense_lite.utils;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.school.edsense_lite.utils.Constants.DATE_FORMAT1;


public class DateTimeUtils {
    public static String getCurrentDateInString(String dateFormat){
       return new SimpleDateFormat(dateFormat).format(new Date());
    }

    public static String parseDateTime(String dateString, String originalFormat, String outputFromat){

        SimpleDateFormat formatter = new SimpleDateFormat(originalFormat, Locale.US);
        Date date = null;
        try {
            date = formatter.parse(dateString);

            SimpleDateFormat dateFormat=new SimpleDateFormat(outputFromat, new Locale("US"));

            return dateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getRelativeTimeSpan(String dateString, String originalFormat){

        SimpleDateFormat formatter = new SimpleDateFormat(originalFormat, Locale.US);
        Date date = null;
        try {
            date = formatter.parse(dateString);

            return DateUtils.getRelativeTimeSpanString(date.getTime()).toString();

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String parseDate(String dateString, String inputFormat, String outputFormat){
        SimpleDateFormat dateFormat = new SimpleDateFormat(inputFormat);
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat targetFormat = new SimpleDateFormat(outputFormat);
        return targetFormat.format(sourceDate);
    }
}
