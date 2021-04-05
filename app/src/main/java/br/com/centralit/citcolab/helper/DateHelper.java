package br.com.centralit.citcolab.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static String getRef(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        return new String(dateFormat.format(date));
    }

    public static Date formatDateDatabase(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return new Date(dateFormat.format(date));
    }
}
