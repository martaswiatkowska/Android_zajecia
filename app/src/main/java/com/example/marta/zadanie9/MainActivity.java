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


public class MainActivity extends AppCompatActivity {
    // identyfikator notyfikacji
    private static final int NOTIFY_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void onClick(View view) {// funkcja zwraca kontekst aplikacji uruchomionej
            Context context = getApplicationContext();// pobrać referencję do procesu uruchomionego
            // https://developer.android.com/reference/android/content/Intent.html
            Intent notificationIntent = new Intent(Intent.ACTION_VIEW,//przygotowanie się do przejścia do strony internetowej
                    Uri.parse("http://www.agh.edu.pl"));
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            Resources res = context.getResources();//uzyskanie zasobów naszej aplikacji
            final Notification.Builder builder = new Notification.Builder(context);

            builder.setContentIntent(contentIntent)//konstruujemy naszej notyfikacji(zawartość)
                    .setSmallIcon(R.drawable.kitty)
                    // duży obraz
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.kitty))
                    .setTicker("Uwaga! kot spożywa swoje jedzenie - prośba mu nie przeszkadzać.")
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentTitle("przypomnienie")
                    .setContentText("Karmienie swego kici! Prosze poczekać... "); // tekst notyfikacji

            Notification notification = builder.build();// stworzymy obiekt typu notyfikacja

            long[] vibrate = new long[] { 1000, 1000, 1000, 1000, 1000 };

            notification.vibrate = vibrate;
            notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
            notification.ledARGB = Color.RED;
            notification.ledOffMS = 0;
            notification.ledOnMS = 1;
            notification.flags = notification.flags | Notification.FLAG_SHOW_LIGHTS;
            // otrzymać dostęp do usługi zarządzania powiadomieniaми
            final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            // pokaz użytkowniku notyfikacje
            notificationManager.notify(NOTIFY_ID, notification);

            // --- pokazuje postęp realizacji jakiegoś zadania
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i <= 100; i += 1) {
                                builder.setProgress(100, i, false);
                                notificationManager.notify(NOTIFY_ID, builder.build());
                                try { // próbować
                                    // Tworzymy wygląd pracy  (zmuszają strumień spać 250ms)
                                    Thread.sleep(250);
                                } catch (InterruptedException e) {
                                    //złapać błąd, jeśli będzie to przerwać i usunąć
                                }
                            }
                            builder.setContentText("Karmienie kota zakończone!")
                                    .setProgress(0, 0, false);
                            notificationManager.notify(NOTIFY_ID, builder.build());
                        }
                    }
            ).start();
            // ----
        }

    }

