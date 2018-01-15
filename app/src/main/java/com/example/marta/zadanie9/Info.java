package com.example.marta.zadanie9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by marta on 1/15/18.
 */

public class Info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // dostać Intent, który spowodował ten Activity
        Intent intent = getIntent();
        // czytać z go akcja
        String action = intent.getAction();

        String format = "", textInfo = "";

        // w zależności od action wypełnić zmienne
        if (action.equals("edu.android.intent.action.showtime")) {
            format = "HH:mm:ss";
            textInfo = "Time: ";
        }
        else if (action.equals("edu.android.intent.action.showdate")) {
            format = "dd.MM.yyyy";
            textInfo = "Date: ";
        }
        // W zależności od zawartości zmiennej Format
        // Pobierz datę lub godzinę w zmiennej datetime
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String datetime = sdf.format(new Date(System.currentTimeMillis()));

        TextView tvDate = (TextView) findViewById(R.id.tvInfo);
        tvDate.setText(textInfo + datetime);
    }
}
