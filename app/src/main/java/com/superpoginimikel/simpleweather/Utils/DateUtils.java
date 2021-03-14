package com.superpoginimikel.simpleweather.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String ConvertLongToDateFormat(long dateTime)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d Y hh:mm a");
        return formatter.format(new Date(dateTime * 1000));
    }

    public static String ConvertLongToTimeFormat(long dateTime)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        return formatter.format(new Date(dateTime * 1000));
    }
}
