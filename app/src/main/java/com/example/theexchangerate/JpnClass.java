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

public class JpnClass extends AppCompatActivity {

    Button countryBtn, convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Japan");
        setContentView(R.layout.activity_jpn_class);


        countryBtn = findViewById(R.id.showCountry);
        convert = findViewById(R.id.convert);

        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(getApplicationContext(), MapsActivityJpn.class);
                startActivity(add_mem);
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = findViewById(R.id.input);
                double yen = Double.parseDouble(input.getText().toString());
                TextView jpnRp = findViewById(R.id.jpn_rp);
                TextView jpnDollar= findViewById(R.id.jpn_dollar);
                TextView jpnPound = findViewById(R.id.jpn_pound);
                TextView jpnWon = findViewById(R.id.jpn_won);
                TextView jpnBath = findViewById(R.id.jpn_bath);

                double resultRupiah = yen / 0.0089;
                double resultDollar = yen / 133.68;
                double resultPound = yen / 165.42;
                double resultWon = yen / 0.10;
                double resultBath = yen / 3.89;

                Locale indo = new Locale("in", "ID");
                Locale thai = new Locale("th", "TH");

                jpnRp.setText(NumberFormat.getCurrencyInstance(indo).format(resultRupiah));
                jpnDollar.setText(NumberFormat.getCurrencyInstance(Locale.US).format(resultDollar));
                jpnPound.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(resultPound));
                jpnWon.setText(NumberFormat.getCurrencyInstance(Locale.KOREA).format(resultWon));
                jpnBath.setText(NumberFormat.getCurrencyInstance(thai).format(resultBath));
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