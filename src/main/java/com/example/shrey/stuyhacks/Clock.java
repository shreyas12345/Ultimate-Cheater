package com.example.shrey.stuyhacks;


/**
 * Created by Brian on 5/28/16.
 */

import android.content.Context;
import android.os.Vibrator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class Clock {
    //static Vibrator v = (Vibrator) Config.context.getSystemService(Config.context.VIBRATOR_SERVICE);


    public static String getTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        return (sdf.format(cal.getTime()));

    }

    public static void timer(int length, Context context){  //length in miliseconds
        //final Vibrator v = (Vibrator) Config.context.getSystemService(Config.context.VIBRATOR_SERVICE);
        final Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               v.vibrate(5000);
            }
        },length);
    }

}