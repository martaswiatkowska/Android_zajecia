package com.example.marta.zadanie9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    // Elementy Ekranu
    TextView tv;
    CheckBox chb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // znajdziemy elementy
        tv = (TextView) findViewById(R.id.textView);
        chb = (CheckBox) findViewById(R.id.chbExtMenu);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // dodaj pozycji menu
        menu.add(0, 1, 0, "add");
        menu.add(0, 2, 0, "edit");
        menu.add(0, 3, 3, "delete");
        menu.add(1, 4, 1, "copy");
        menu.add(1, 5, 2, "paste");
        menu.add(1, 6, 4, "exit");

        // metoda N 2 poprzez xml- pliku
        //getMenuInflater().inflate(R.menu.my_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    // aktualizacja menu
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // elementy menu z grupy o ID = 1 będą widoczne
        menu.setGroupVisible(1, chb.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        StringBuilder sb = new StringBuilder();

        // Wpisanie do  TextView informacji o pozycji elementu w menu
        sb.append("Item Menu");
        sb.append("\r\n groupId: " + String.valueOf(item.getGroupId()));
        sb.append("\r\n itemId: " + String.valueOf(item.getItemId()));
        sb.append("\r\n order: " + String.valueOf(item.getOrder()));
        sb.append("\r\n title: " + item.getTitle());
        tv.setText(sb.toString());

        return super.onOptionsItemSelected(item);
    }
}

