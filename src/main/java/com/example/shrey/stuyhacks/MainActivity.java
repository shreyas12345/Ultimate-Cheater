package com.example.shrey.stuyhacks;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.*;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RelativeLayout parentlayout;
    ArrayList<Integer> number = new ArrayList<Integer>();
    int counter;
    int operationcounter = 0;
    int mode = 0;
    double send;
    int questionNumber;
    int multiplechoice;
    Button myButton;
    EditText myEditText;
    String phoneNumber;
    ArrayList<Integer> timerList = new ArrayList<Integer>();
    int timeCounter = 0;

  //  ArrayList<Integer> response = new ArrayList<Integer>();


    String timeString;

    /*
    Mode 0: Calculator
    Mode 1: Clock
    Mode 2: Multiple Choice
     */
    double firstNumber;
        double secondNumber;

        @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            myButton = (Button)findViewById(R.id.button);
            myEditText = (EditText)findViewById(R.id.editText);

            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    phoneNumber = myEditText.getText().toString();
                }
            });

            parentlayout = (RelativeLayout) findViewById(R.id.layout);

            parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v3.vibrate(50);
                    counter = counter + 1;
                    if (counter >= 10) {
                        counter = 0;
                    }
                }
            });

            parentlayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Vibrator v4 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v4.vibrate(100);
                    number.add(counter);
                    counter = 0;
                    return true;
                }
            });
        }

    public int createNumber(ArrayList number) {

      int finalValue = 0;
        int input = 0;
        int x = 0;

        while(x<number.size()){
            input = (Integer)number.get(x);
            finalValue = finalValue * 10 + input;
            x++;
        }
        return finalValue;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //how to increase counter effectively
        Vibrator v5 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            mode++;
            if (mode > 2) {
                mode = 0;
            }
            Toast.makeText(getApplicationContext(), "Mode " + mode, Toast.LENGTH_SHORT).show();
        }

        if (mode == 0) {

            if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
                if (number.size() != 0) {
                    if (firstNumber == 0) {
                        firstNumber = createNumber(number);
                        operationcounter = 1;
                        counter = 0;
                        number.clear();
                    } else {
                        secondNumber = createNumber(number);
                        counter = 0;
                        number.clear();

                        if (operationcounter == 1) {
                            addNumbers(firstNumber, secondNumber);
                            send = addNumbers(firstNumber, secondNumber);
                            Toast.makeText(getApplicationContext(), Double.toString(addNumbers(firstNumber, secondNumber)), Toast.LENGTH_SHORT).show();
                            try {
                                Thread.sleep(600);                 //1000 milliseconds is one second.
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            digitsToVibrate(digits(addNumbers(firstNumber, secondNumber)));
                            number.clear();
                            firstNumber = 0;
                        }

                        if (operationcounter == 2) {
                            subNumbers(firstNumber, secondNumber);
                            send = subNumbers(firstNumber, secondNumber);
                            Toast.makeText(getApplicationContext(), Double.toString(subNumbers(firstNumber, secondNumber)), Toast.LENGTH_SHORT).show();
                            try {
                                Thread.sleep(600);                 //1000 milliseconds is one second.
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            digitsToVibrate(digits(subNumbers(firstNumber, secondNumber)));
                            number.clear();
                            firstNumber = 0;
                        }

                        if (operationcounter == 3) {
                            multiplyNumbers(firstNumber, secondNumber);
                            send = multiplyNumbers(firstNumber, secondNumber);
                            Toast.makeText(getApplicationContext(), Double.toString(multiplyNumbers(firstNumber, secondNumber)), Toast.LENGTH_SHORT).show();
                            try {
                                Thread.sleep(600);                 //1000 milliseconds is one second.
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            digitsToVibrate(digits(multiplyNumbers(firstNumber, secondNumber)));
                            number.clear();
                            firstNumber = 0;
                        }

                        if (operationcounter == 4) {
                            divideNumbers(firstNumber, secondNumber);
                            send = divideNumbers(firstNumber, secondNumber);
                            Toast.makeText(getApplicationContext(), Double.toString(divideNumbers(firstNumber, secondNumber)), Toast.LENGTH_SHORT).show();
                            try {
                                Thread.sleep(600);                 //1000 milliseconds is one second.
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            digitsToVibrate(digits(divideNumbers(firstNumber, secondNumber)));
                            number.clear();
                            firstNumber = 0;
                        }
                    }
                } else {
                    operationcounter++;
                }
            }

            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

                number.clear();
                firstNumber = 0;
                secondNumber = 0;
                operationcounter = 0;
            }
            return true;
        }

        if (mode == 2) {
            parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v3.vibrate(50);
                    timeCounter = timeCounter + 1;
                    if (timeCounter >= 10) {
                        timeCounter = 0;
                    }
                }
            });

            parentlayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Vibrator v4 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v4.vibrate(100);
                    timerList.add(timeCounter);
                    return true;
                }
            });

        if (timerList.size() != 0) {

                if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
                    createNumber(timerList);
                    Clock.timer(createNumber(timerList)*1000, getApplicationContext()); //makes the timer in minutes
                    timerList.clear();
                }
            } else {
                if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
                    Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    timeString = Clock.getTime();
                    vibrateTime(v2);
                }
                return true;
            }

            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                timerList.clear();
            }
        }
        if (mode == 1) { //SMS
            parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v3.vibrate(50);
                    counter = counter + 1;
                    if (counter >= 10) {
                        counter = 0;
                    }
                }
            });

            parentlayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Vibrator v4 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v4.vibrate(100);
                    number.add(counter);
                    counter = 0;
                    return true;
                }
            });

            if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
                questionNumber = createNumber(number);
                sendSMSMessage();
                number.clear();
                questionNumber = 0;

            }

            if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0) {
                multiplechoice++;
                if(multiplechoice>5){
                    multiplechoice = 1;
                }
                return true;
            }

        }

        return true;

    }

    public ArrayList<Integer> digits(double number){
        String numberString = Double.toString(number);
        ArrayList<Integer> response = new ArrayList<Integer>();
        for (int i = 0; i < numberString.length(); i++){
            if (numberString.substring(i, i+1).equals("-")){
                response.add(-1);
            }
            if (numberString.substring(i, i+1).equals(".")){
                response.add(-2);
            }
            else{
                response.add(Integer.parseInt(numberString.substring(i, i+1)));
            }
        }
        return response;
    }

    public void digitsToVibrate(ArrayList response){
        Vibrator v5 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        for (int i = 0; i < response.size(); i++){
            if ((int)response.get(i)==-1){
                //Vibrate for negative sign
                v5.vibrate(1000);
                try {
                    Thread.sleep(500);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            if((int)response.get(i)==-2){
                v5.vibrate(50);
                try {
                    Thread.sleep(500);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            else{
                for (int k = 0; k < (int)response.get(i); k++){
                    v5.vibrate(300);
                    try {
                        Thread.sleep(500);                 //1000 milliseconds is one second.
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            try {
                Thread.sleep(1000);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }



    public double addNumbers(double a, double b){
        return a+b;
    }

    public double subNumbers(double a, double b){
        return a - b;
    }

    public double multiplyNumbers(double a, double b) {
        return a * b;
    }

    public double divideNumbers(double a, double b){
        return a/b;
    }


    public void vibrateTime(Vibrator v){
        //"04:51"


        String time = timeString;

        System.out.println(Integer.parseInt(time.substring(0, 1)));

        ArrayList<Integer>timeList = new ArrayList<Integer>();


        for (int i = 0; i < time.length(); i++){
            System.out.println(time.substring(i, i+1));
            if (i==2){
                timeList.add(-1);
            }
            else{
                timeList.add(Integer.parseInt(time.substring(i, i+1)));
            }
        }

        for (int i = 0; i < 5; i++){
            System.out.println(timeList.get(i));
            if (timeList.get(i)==-1){
                v.vibrate(250);
                try {
                    Thread.sleep(500);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            else{
                for (int k = 0; k < timeList.get(i); k++){
                    v.vibrate(100);
                    try {
                        Thread.sleep(500);                 //1000 milliseconds is one second.
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            try {
                Thread.sleep(500);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }




    }

    protected void sendSMSMessage() {
        String message = "";
        try {

            String phoneNo = phoneNumber;
            if (multiplechoice == 0) {
                 message = Integer.toString(questionNumber) + "-- " + Double.toString(send);
            } else if (multiplechoice == 1) {
                 message = Integer.toString(questionNumber) + "--" + "A";
                multiplechoice = 0;
            }
            if (multiplechoice == 2) {
                 message = Integer.toString(questionNumber) + "--" + "B";
                multiplechoice = 0;
            }
            if (multiplechoice == 3) {
                 message = Integer.toString(questionNumber) + "--" + "C";
                multiplechoice = 0;
            }
            if (multiplechoice == 4) {
                 message = Integer.toString(questionNumber) + "--" + "D";
                multiplechoice = 0;
            }
            if (multiplechoice == 5) {
                 message = Integer.toString(questionNumber) + "--" + "E";
                multiplechoice = 0;
            }






            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}



