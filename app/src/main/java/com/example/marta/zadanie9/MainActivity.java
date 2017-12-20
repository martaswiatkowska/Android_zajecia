package com.example.marta.zadanie9;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

// Nasza Klasa MainActivity odziedziczy od ActionBarActivity i interfejsu OnClickListener
public class MainActivity extends AppCompatActivity implements OnClickListener {

    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    // realizujemy metody onClick interfejsu OnClickListener
    public void onClick(View v) {
        // przez id zdefiniujemy przycisk, który spowodował moduł obsługi
        switch (v.getId()) {
            case R.id.btnOk:
                // przycisk ОК
                tvOut.setText("jest wciśnięty przycisk ОК");
                break;
            case R.id.btnCancel:
                // przycisk Cancel
                tvOut.setText("jest wciśnięty przycisk Cancel");
                break;
            case R.id.btnStart:
                // przycisk Start
                tvOut.setText("jest wciśnięty przycisk Start");
                break;
        }
    }

    public void onClickStart(View v) {
        //działania poprzez naciśnięcie przycisku
        tvOut = (TextView) findViewById(R.id.tvOut);
        tvOut.setText("jest wciśnięty przycisk Start!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //znajdziemy View-elementy
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        // this - to faktycznie nasz obiekt activity typu MainActivity
        // on jest procedurę obsługi zdarzenia naciśnięcie

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
}