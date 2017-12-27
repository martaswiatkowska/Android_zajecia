package com.example.marta.zadanie9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout llMain;
    RadioGroup rgGravity;
    EditText etName;
    Button btnCreate;
    Button btnClear;

    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llMain = (LinearLayout) findViewById(R.id.llMain);
        rgGravity = (RadioGroup) findViewById(R.id.rgGravity);
        etName = (EditText) findViewById(R.id.etName);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        //dodanie Lisenera do elementu
        btnCreate.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.btnCreate:
                // Tworzenie LayoutParams z szerokości i wysokości ze zawartości

                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                        wrapContent, wrapContent);
                // Zmienna przechowywania wartości wyrównania
                // Domyślną niech będzie LEFT
                int btnGravity = Gravity.LEFT;
                // definiuemy, jaki RadioButton " pole wyboru wykożysta " i
          // odpowiednio wypełnić btnGravity
                switch (rgGravity.getCheckedRadioButtonId()) {
                    case R.id.rbLeft:
                        btnGravity = Gravity.LEFT;
                        break;
                    case R.id.rbCenter:
                        btnGravity = Gravity.CENTER_HORIZONTAL;
                        break;
                    case R.id.rbRight:
                        btnGravity = Gravity.RIGHT;
                        break;
                }
                // przenieść otrzymaną wartość  wyrównania w LayoutParams
                lParams.gravity = btnGravity;

                // srworzymy Button, napiszemy tekst i dodajmy w LinearLayout
                Button btnNew = new Button(this);
                //ustaw tekst wpisany do elementu etName
                btnNew.setText(etName.getText().toString());
                //dodanie nowoutworzonego elementu do głównego layoutu
                llMain.addView(btnNew, lParams);

                break;
            case R.id.btnClear:
                //usunęcie wszystkich elementów
                llMain.removeAllViews();
                //Wyświetl tekst o usuniętych elementach
                Toast.makeText(this, " Usunięto ", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

