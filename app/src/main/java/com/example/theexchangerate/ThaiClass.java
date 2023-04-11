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

public class ThaiClass extends AppCompatActivity {

    Button countryBtn, convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thailand");
        setContentView(R.layout.activity_thai_class);


        countryBtn = findViewById(R.id.showCountry);
        convert = findViewById(R.id.convert);

        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(getApplicationContext(), MapsActivityThai.class);
                startActivity(add_mem);
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = findViewById(R.id.input);
                double bath = Double.parseDouble(input.getText().toString());
                TextView thaiRp = findViewById(R.id.thai_rp);
                TextView thaiDollar= findViewById(R.id.thai_dollar);
                TextView thaiPound = findViewById(R.id.thai_pound);
                TextView thaiWon = findViewById(R.id.thait_won);
                TextView thaiYen = findViewById(R.id.thai_yen);

                double resultRupiah = bath / 0.0023;
                double resultDollar = bath / 34.37;
                double resultPound = bath / 42.56;
                double resultWon = bath / 0.026;
                double resultYen = bath / 0.26;

                Locale indo = new Locale("in", "ID");

                thaiRp.setText(NumberFormat.getCurrencyInstance(indo).format(resultRupiah));
                thaiDollar.setText(NumberFormat.getCurrencyInstance(Locale.US).format(resultDollar));
                thaiPound.setText(NumberFormat.getCurrencyInstance(Locale.UK).format(resultPound));
                thaiWon.setText(NumberFormat.getCurrencyInstance(Locale.KOREA).format(resultWon));
                thaiYen.setText(NumberFormat.getCurrencyInstance(Locale.JAPAN).format(resultYen));
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