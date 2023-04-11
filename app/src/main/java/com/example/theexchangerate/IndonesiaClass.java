package com.example.theexchangerate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.os.LocaleListCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class IndonesiaClass extends AppCompatActivity {

    Button countryBtn, convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Indonesian");
        setContentView(R.layout.activity_indonesia_class);


        countryBtn = findViewById(R.id.showCountry);
        convert = findViewById(R.id.convert);

        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(getApplicationContext(), MapsActivityIndo.class);
                startActivity(add_mem);
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = findViewById(R.id.input);
                double rupiah = Double.parseDouble(input.getText().toString());
                TextView indoDollar = findViewById(R.id.indo_dollar);
                TextView indoPound = findViewById(R.id.indo_pound);
                TextView indoWon = findViewById(R.id.indo_won);
                TextView indoBath = findViewById(R.id.indo_bath);
                TextView indoYen = findViewById(R.id.indo_yen);

                double resultDollar = rupiah / 14912.45;
                double resultPound = rupiah / 18531.03;
                double resultWon = rupiah / 11.31;
                double resultBath = rupiah / 434.65;
                double resultYen = rupiah / 112.90;

                Locale thai = new Locale("th", "TH");

                indoDollar.setText(NumberFormat.getCurrencyInstance(Locale.US).format(resultDollar));
                indoPound.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(resultPound));
                indoWon.setText(NumberFormat.getCurrencyInstance(Locale.KOREA).format(resultWon));
                indoBath.setText(NumberFormat.getCurrencyInstance(thai).format(resultBath));
                indoYen.setText(NumberFormat.getCurrencyInstance(Locale.JAPAN).format(resultYen));
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