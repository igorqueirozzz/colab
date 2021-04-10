package br.com.centralit.citcolab.helper;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class DateHelper {

    public static String getRef(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/yyyy");
        return new String(dateFormat.format(date));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formatForDataBase(Date date) {
        ZoneId zoneId = ZoneId.systemDefault();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        simpleDateFormat.setTimeZone(timeZone);
        return new String(simpleDateFormat.format(date));
    }

    public static String formatSimple(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return new String(simpleDateFormat.format(date));
    }
}


