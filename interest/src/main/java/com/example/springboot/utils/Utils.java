package com.example.springboot.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    public static boolean beyondSevenDays(Date from){
        LocalDate fromDate = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(fromDate, LocalDate.now());
        return period.getYears()>0 || period.getMonths()>0 || period.getDays()>7;
    }

    public static double sub(double a, double b){
        return BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b)).doubleValue();
    }

    public static double sum(double a, double b){
        return BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).doubleValue();
    }

    public static double mul(double a, double b){
        return BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b)).doubleValue();
    }

    public static double div(double a, double b){
        return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b)).doubleValue();
    }

    public static double sum2(double a, double b){
        long c = Math.round(a*100) + Math.round(b*100);
        return to(String.valueOf(c));
    }

    public static double to(String a){
        int l = a.length();
        String r;
        if(l==1){
            r = "0.0"+a;
        }else if(l ==2){
            r="0."+a;
        }else{
            r = a.substring(0,l-2)+"."+a.substring(l-2,l);
        }
        return Double.valueOf(r);
    }

}
