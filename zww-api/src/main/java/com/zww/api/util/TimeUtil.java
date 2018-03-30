package com.zww.api.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.zww.api.model.MemberChargeCombo;

public class TimeUtil {
    public static Timestamp getTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        Timestamp newDate = Timestamp.valueOf(time);
        return newDate;
    }

    public static Timestamp getEndTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date afterDate = new Date(date.getTime() + 180000);
        String time = format.format(afterDate);
        Timestamp newDate = Timestamp.valueOf(time);
        return newDate;
    }

    /*
     * timeDelay 从当前时间计算到有效时间的时长，单位毫秒
     */
    public static Timestamp getEndTime(int timeDelay) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date afterDate = new Date(date.getTime() + timeDelay);
        String time = format.format(afterDate);
        Timestamp newDate = Timestamp.valueOf(time);
        return newDate;
    }

    /**
     * 当前日期加上天数后的日期
     *
     * @param num 为增加的天数
     * @return
     */
    public static Timestamp plusDay(int num) {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        Timestamp newDate = Timestamp.valueOf(enddate);
        return newDate;
    }

    public static Date plusDay(Date d, int num) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        //String enddate = format.format(d);
        //Timestamp newDate = Timestamp.valueOf(enddate);
        return d;
    }

    public static Timestamp stringToTimestamp(String dateStr) throws ParseException {
        Timestamp ts = Timestamp.valueOf(dateStr);
        System.out.println(ts);
        return ts;
    }

    public static Date stringToDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(dateStr);
        System.out.println(dt);
        return dt;
    }

    public static Timestamp getDayBeginTimestamp() {
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60
                * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)
                * 1000);
        return new Timestamp(date2.getTime());
    }

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDt = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDt;
    }

    public static String formate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    //获取剩余天数
    public static long getEndTime(MemberChargeCombo monthCombo) {
        if (monthCombo.getChargeDateStart() == null) {
            return -1;
        }
        Date now = new Date();
        long l = monthCombo.getChargeDateStart().getTime() - now.getTime();
        long day = l / (24 * 60 * 60 * 1000) + monthCombo.getChargeDateLimit() + 1;
        return day;
    }

    public static Object getdate(MemberChargeCombo weeksCombo) {
        if (weeksCombo.getChargeDateStart() == null) {
            weeksCombo.setChargeDateStart(new Date(0));
        }
        if (weeksCombo.getChargeDateLimit() == null) {
            weeksCombo.setChargeDateLimit(0);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String format1 = formatter.format(weeksCombo.getChargeDateStart());
        String format2 = formatter.format(new Date(weeksCombo.getChargeDateStart().getTime() + weeksCombo.getChargeDateLimit() * 24L * 60 * 60 * 1000));

        return format1 + "至" + format2;
    }
    
    /**
     * 计算两个日期的时间差
     * @param formatTime1
     * @param formatTime2
     * @return
     */
    public static int getTimeDifference(Timestamp formatTime1, Timestamp formatTime2) {
           SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
           long t1 = 0L;
           long t2 = 0L;
           try {
               t1 = timeformat.parse(getTimeStampNumberFormat(formatTime1)).getTime();
           } catch (ParseException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
           try {
               t2 = timeformat.parse(getTimeStampNumberFormat(formatTime2)).getTime();
           } catch (ParseException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
           //因为t1-t2得到的是毫秒级,所以要初3600000得出小时.算天数或秒同理
          // int hours=(int) ((t1 - t2)/3600000);
          // int minutes=(int) (((t1 - t2)/1000-hours*3600)/60);
          // int second=(int) ((t1 - t2)/1000-hours*3600-minutes*60);
          // return ""+hours+"小时"+minutes+"分"+second+"秒";
           int second=(int) ((t1 - t2)/1000);
           return second;
       }
    
    /**
     * 格式化时间
     * Locale是设置语言敏感操作
     * @param formatTime
     * @return
     */
    public static String getTimeStampNumberFormat(Timestamp formatTime) {
           SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", new Locale("zh", "cn"));
           return m_format.format(formatTime);
       }

//	public static void main(String[] args) {
//		System.out.println(new TimeUtil().getDayBeginTimestamp());
//	}
}












