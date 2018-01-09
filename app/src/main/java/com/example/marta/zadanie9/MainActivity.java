package com.example.marta.zadanie9;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    // identyfikator notyfikacji
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dostać linka do słońca
        ImageView sun = (ImageView) findViewById(R.id.sun);
        // Animacje dla wschodu słońca
        Animation sunRise = AnimationUtils.loadAnimation(this, R.anim.sun_rise);
        // Podłączanie animacji do pożądanego View
        sun.startAnimation(sunRise);
    }
}

