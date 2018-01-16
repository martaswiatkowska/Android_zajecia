package com.example.marta.zadanie9;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    final String LOG_TAG = "myLogs";

    String name[] = { " Chiny ", " USA ", " Brazylia ", " Rosja ", " Japonia ",
            "Niemcy", "Egipt", "Włochy", "Francja", "Kanada" };
    int people[] = {1400, 311, 195, 142, 128, 82, 80, 60, 66, 35};
    String region[] = { " Azja ", " Ameryka ", " Ameryka ", " Europa ", " Azja ",
            "Europa", "Afryka", "Europa", "Europa", "Ameryka " };

    Button btnAll, btnFunc, btnPeople, btnSort, btnGroup, btnHaving;
    EditText etFunc, etPeople, etRegionPeople;
    RadioGroup rgSort;

    DBHelper dbHelper;
    SQLiteDatabase db;

    /**
     * Called when the activity is first created.
     */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = (Button) findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);

        btnFunc = (Button) findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(this);

        btnPeople = (Button) findViewById(R.id.btnPeople);
        btnPeople.setOnClickListener(this);

        btnSort = (Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(this);

        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(this);

        btnHaving = (Button) findViewById(R.id.btnHaving);
        btnHaving.setOnClickListener(this);

        etFunc = (EditText) findViewById(R.id.etFunc);
        etPeople = (EditText) findViewById(R.id.etPeople);
        etRegionPeople = (EditText) findViewById(R.id.etRegionPeople);

        rgSort = (RadioGroup) findViewById(R.id.rgSort);

        dbHelper = new DBHelper(this);
        // podłączamy się do bazy i ją tworzymy
        db = dbHelper.getWritableDatabase();

        // weryfikacja istnienia rekordów
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        //Jeśli baza danych jest pusta to dodaj
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            // wypełni tabelę
            for (int i = 0; i < 10; i++) {
                cv.put("name", name[i]);
                cv.put("people", people[i]);
                cv.put("region", region[i]);
                Log.d(LOG_TAG, "id = " + db.insert("mytable", null, cv));
            }
        }
        c.close();
        dbHelper.close();
        // emulować naciśnięcie przycisku  btnAll
        onClick(btnAll);

    }

    public void onClick(View v) {

        // podłączamy się do bazy
        db = dbHelper.getWritableDatabase();

        // Dane z ekranu
        String sFunc = etFunc.getText().toString();
        String sPeople = etPeople.getText().toString();
        String sRegionPeople = etRegionPeople.getText().toString();

        // zmienne dla  query
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        // kursor
        Cursor c = null;

        // zdefiniować wciśnięty przycisk
        switch (v.getId()) {
            // wszystkie rekordy
            case R.id.btnAll:
                Log.d(LOG_TAG, "--- Wszystkie elementy ---");
                c = db.query("mytable", null, null, null, null, null, null);
                break;
            // pobranie wszystkich elementów z danej kolumny
            case R.id.btnFunc:
                Log.d(LOG_TAG, "--- Funkcja " + sFunc + " ---");
                columns = new String[]{sFunc};
                c = db.query("mytable", columns, null, null, null, null, null);
                break;
            // populacja więcej niż
            case R.id.btnPeople:
                Log.d(LOG_TAG, "--- populacja więcej " + sPeople + " ---");
                selection = "people > ?";
                selectionArgs = new String[]{sPeople};
                c = db.query("mytable", null, selection, selectionArgs, null, null,
                        null);
                break;
            // Populacja wedłóg regionu
            case R.id.btnGroup:
                Log.d(LOG_TAG, "--- Populacja regionu ---");
                columns = new String[]{"region", "sum(people) as people"};
                groupBy = "region";
                c = db.query("mytable", columns, null, null, groupBy, null, null);
                break;
            // Pobiera populację wedłóg regionu więkrzą od podanej wartości
            case R.id.btnHaving:
                Log.d(LOG_TAG, "--- regiony z populacje więcej " + sRegionPeople
                        + " ---");
                columns = new String[]{"region", "sum(people) as people"};
                groupBy = "region";
                having = "sum(people) > " + sRegionPeople;
                c = db.query("mytable", columns, null, null, groupBy, having, null);
                break;

            // sortowanie

            case R.id.btnSort:
                // sortowanie według
                switch (rgSort.getCheckedRadioButtonId()) {
                    // tytuł
                    case R.id.rName:
                        Log.d(LOG_TAG, "--- Sortuj według nazwy ---");
                        orderBy = "name";
                        break;
                    // Populacja
                    case R.id.rPeople:
                        Log.d(LOG_TAG, "--- Sortuj według ludności ---");
                        orderBy = "people";
                        break;
                    // region
                    case R.id.rRegion:
                        Log.d(LOG_TAG, "--- Sortuj według regionu ---");
                        orderBy = "region";
                        break;
                }
                c = db.query("mytable", null, null, null, null, null, orderBy);
                break;
        }

        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);

                } while (c.moveToNext());
            }
            c.close();
        } else
            Log.d(LOG_TAG, "Cursor is null");

        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // konstruktor superklasy
            super(context, "myDatabase", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // tworzenia tabeli z polami
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement," + "name text,"
                    + "people integer," + "region text" + ");");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}