package br.com.app.colab.helper;

import java.util.Calendar;

public class SumHours {


    public static Calendar sumHours(Calendar register1, Calendar register2, Calendar register3, Calendar register4){
        Calendar total = Calendar.getInstance();
        int firtHourRegister = register1.getTime().getHours();
        int firtHourRegisterMinutes = register1.getTime().getMinutes();
        int secondHourRegister = register2.getTime().getHours();
        int secondHourRegisterMinutes = register2.getTime().getMinutes();
        int thirdHourRegister = register3.getTime().getHours();
        int thirdHourRegisterMinutes = register3.getTime().getMinutes();
        int fourthHourRegister = register4.getTime().getHours();
        int fourthHourRegisterMinutes = register4.getTime().getMinutes();
        int totalHours = (fourthHourRegister - thirdHourRegister) + (secondHourRegister - firtHourRegister);
        int totalMinutes = (fourthHourRegisterMinutes - thirdHourRegisterMinutes) + (secondHourRegisterMinutes - firtHourRegisterMinutes);
        total.set(0, 0, 0, 0,0);
        total.add(Calendar.HOUR, totalHours);
        total.add(Calendar.MINUTE, totalMinutes);

        return total;
    }

}
