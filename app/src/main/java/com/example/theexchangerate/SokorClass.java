package com.example.theexchangerate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class SokorClass extends AppCompatActivity {

    Button countryBtn, convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("South Korean");
        setContentView(R.layout.activity_sokor_class);


        countryBtn = findViewById(R.id.showCountry);
        convert = findViewById(R.id.convert);

        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(getApplicationContext(), MapsActivitySokor.class);
                startActivity(add_mem);
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = findViewById(R.id.input);
                double won = Double.parseDouble(input.getText().toString());
                TextView sokorRp = findViewById(R.id.sokor_rp);
                TextView sokorDollar= findViewById(R.id.sokor_dollar);
                TextView sokorPound = findViewById(R.id.sokor_pound);
                TextView sokorBath = findViewById(R.id.sokor_bath);
                TextView sokorYen = findViewById(R.id.sokor_yen);

                double resultRupiah = won / 0.088;
                double resultDollar = won / 1322.53;
                double resultPound = won / 1634.86;
                double resultBath = won / 38.44;
                double resultYen = won / 9.88;

                Locale thai = new Locale("th", "TH");
                Locale indo = new Locale("in", "ID");

                sokorRp.setText(NumberFormat.getCurrencyInstance(indo).format(resultRupiah));
                sokorDollar.setText(NumberFormat.getCurrencyInstance(Locale.US).format(resultDollar));
                sokorPound.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(resultPound));
                sokorBath.setText(NumberFormat.getCurrencyInstance(thai).format(resultBath));
                sokorYen.setText(NumberFormat.getCurrencyInstance(Locale.JAPAN).format(resultYen));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle options menu item clicks here.
        int id = item.getItemId();
        if (id == R.id.action_change_language){
            Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(languageIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}