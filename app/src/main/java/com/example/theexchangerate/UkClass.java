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

public class UkClass extends AppCompatActivity {

    Button countryBtn, convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("United Kingdom");
        setContentView(R.layout.activity_uk_class);


        countryBtn = findViewById(R.id.showCountry);
        convert = findViewById(R.id.convert);

        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(getApplicationContext(), MapsActivityUk.class);
                startActivity(add_mem);
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = findViewById(R.id.input);
                double pound= Double.parseDouble(input.getText().toString());
                TextView ukRp = findViewById(R.id.uk_rp);
                TextView ukDollar= findViewById(R.id.uk_dollar);
                TextView ukWon = findViewById(R.id.uk_won);
                TextView ukBath = findViewById(R.id.uk_bath);
                TextView ukYen = findViewById(R.id.uk_yen);

                double resultRupiah = pound / 0.000054;
                double resultDollar = pound / 0.81;
                double resultWon = pound / 0.00061;
                double resultBath = pound / 0.024;
                double resultYen = pound / 0.0060;

                Locale thai = new Locale("th", "TH");
                Locale indo = new Locale("in", "ID");

                ukRp.setText(NumberFormat.getCurrencyInstance(indo).format(resultRupiah));
                ukDollar.setText(NumberFormat.getCurrencyInstance(Locale.US).format(resultDollar));
                ukWon.setText(NumberFormat.getCurrencyInstance(Locale.KOREA).format(resultWon));
                ukBath.setText(NumberFormat.getCurrencyInstance(thai).format(resultBath));
                ukYen.setText(NumberFormat.getCurrencyInstance(Locale.JAPAN).format(resultYen));
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