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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // identyfikator notyfikacji
    // Zmienna, elementem sterowania "pola wprowadź"
    private EditText mInputEditText;

    // Stały konwersji walut
    private final double rateUsd  = 3.66;
    private final double rateEur  = 4.05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //otrzymać dostęp do elementu zarządzanie "pola wprowadź" (tutaj wprowadzony suma w złotych)
        mInputEditText = (EditText) findViewById(R.id.editText);
    }

    private float convertZlotyToEuro(float a) {
        return (float) (a / rateEur);
    }

    private float convertZlotyToUsd(float a) {
        return (float) (a / rateUsd);
    }

    // Przetwarzanie kliknięcia na przycisk Convert
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.buttonConverter:
                RadioButton euroRadioButton = (RadioButton) findViewById(R.id.radioButtonEuro);

                // sprawdzić pola wprowadzania na pustkę, jeśli długość 0, wyświetli komunikat
                if (mInputEditText.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Enter amount in zloty",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                // zadeklarować zmienną typu realnej liczby pojedynczej precyzji(float)
                // Float.parse - przekonwertować ciąg do liczby
                float inputValue = Float.parseFloat(mInputEditText.getText().toString());
                if (euroRadioButton.isChecked()) {
                    // konwersji liczb na łańcuchy i przypisać do pola wprowadzania,
                    // pokazać użytkownikowi wynik
                    mInputEditText.setText(String.valueOf(convertZlotyToEuro(inputValue)));
                } else {
                    mInputEditText.setText(String.valueOf(convertZlotyToUsd(inputValue)));
                }
                break;
        }
    }
}

