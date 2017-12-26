package com.example.marta.zadanie9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // znajdziemy View-elementy
        Log.d(TAG, "znajdziemy View-elementy");
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        // przypisać procedurę obsługi do przycisków
        Log.d(TAG, " przypisać procedurę obsługi do przycisków");
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // przez id zdefiniować przycisk, który spowodował procedurę obsługi
        Log.d(TAG, "przez id zdefiniować przycisk, który spowodował procedurę obsługi");
        switch (v.getId()) {
            case R.id.btnOk:
                // przycisk ОК
                Log.d(TAG, "przycisk ОК");
                Toast.makeText(this, "jest naciśnięty przycisk ОК", Toast.LENGTH_LONG).show();
                tvOut.setText("jest naciśnięty przycisk ОК");
                break;
            case R.id.btnCancel:
                // przycisk Cancel
                Log.d(TAG, "przycisk Cancel");
                Toast.makeText(this, "jest naciśnięty przycisk Cancel", Toast.LENGTH_LONG).show();
                tvOut.setText("jest naciśnięty przycisk Cancel");
                break;
        }
    }
}
