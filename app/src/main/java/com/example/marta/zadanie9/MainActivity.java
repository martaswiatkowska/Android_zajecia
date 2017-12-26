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


public class MainActivity extends AppCompatActivity {
    private Menu menu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.add("menu1");
        menu.add("menu2");
        menu.add("menu3");
        menu.add("menu4");

        return super.onCreateOptionsMenu(menu);
        // zwraca wynik operacji tworzenia menu
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

        // 1) Tworzenie wiadomości wyskakujący                 2) pokazać go
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        // zwróci z metody, wywołujemy metodę rodzica onOptionsItemSelected i przekażemy mu item
        return super.onOptionsItemSelected(item);
    }
}

