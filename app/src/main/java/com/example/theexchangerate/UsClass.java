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

public class UsClass extends AppCompatActivity {

    Button countryBtn, convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("United States");
        setContentView(R.layout.activity_us_class);


        countryBtn = findViewById(R.id.showCountry);
        convert = findViewById(R.id.convert);

        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(getApplicationContext(), MapsActivityUs.class);
                startActivity(add_mem);
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = findViewById(R.id.input);
                double dollar= Double.parseDouble(input.getText().toString());
                TextView usRp = findViewById(R.id.us_rp);
                TextView usPound = findViewById(R.id.us_pound);
                TextView usWon = findViewById(R.id.us_won);
                TextView usBath = findViewById(R.id.us_bath);
                TextView usYen = findViewById(R.id.us_yen);

                double resultRupiah = dollar / 0.000067;
                double resultPound = dollar / 1.24;
                double resultWon = dollar / 0.00076;
                double resultBath = dollar / 0.029;
                double resultYen = dollar / 0.0075;

                Locale thai = new Locale("th", "TH");
                Locale indo = new Locale("in", "ID");

                usRp.setText(NumberFormat.getCurrencyInstance(indo).format(resultRupiah));
                usPound.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(resultPound));
                usWon.setText(NumberFormat.getCurrencyInstance(Locale.KOREA).format(resultWon));
                usBath.setText(NumberFormat.getCurrencyInstance(thai).format(resultBath));
                usYen.setText(NumberFormat.getCurrencyInstance(Locale.JAPAN).format(resultYen));
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