package com.example.theexchangerate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Visibility;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView showContries;
    private boolean isFragmentDisplayed = false;
    Button showMaps;

    ImageView imageStyle;
    SwitchCompat switchCompat;
    SharedPreferences sharedPreferences = null;
    LinearLayout mLayout;

    private SensorManager mSensorManager;
    private Sensor mSensorLight;
    private TextView mTextSensorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = findViewById(R.id.style);
        imageStyle = findViewById(R.id.image_style);
        switchCompat = findViewById(R.id.switchCompat);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mTextSensorLight = findViewById(R.id.light_sensor);
        String sensor_error = "No Sensor";
        if (mSensorLight == null){
            mTextSensorLight.setText(sensor_error);
        }

        sharedPreferences = getSharedPreferences("night", 0);
        Boolean booleanValue = sharedPreferences.getBoolean("night_mode",false);

        if (booleanValue){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switchCompat.setChecked(true);
            imageStyle.setImageResource(R.drawable.night);
        }

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mLayout.setVisibility(View.VISIBLE);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switchCompat.setChecked(true);
                    imageStyle.setImageResource(R.drawable.night);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode",true);
                    editor.commit();
                }
                else{
                    mLayout.setVisibility(View.INVISIBLE);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switchCompat.setChecked(false);
                    imageStyle.setImageResource(R.drawable.day);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode",false);
                    editor.commit();
                }
            }
        });

        showContries = findViewById(R.id.showCountry);
        showContries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFragmentDisplayed){
                    displayFragment();
                } else{
                    closeFragment();
                }
            }
        });

        showMaps = findViewById(R.id.showMaps);
        showMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(getApplicationContext(), MapsActivityHome.class);
                startActivity(add_mem);
            }
        });
    }

    public void onStart() {
        super.onStart();
        //Untuk memberi informasi bahwa sensor di update
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        float currentValue = event.values[0];

        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                mTextSensorLight.setText(
                        String.format("Light sensor : %1$.2f", currentValue));
                changeStyle(currentValue);
                break;
            default:
        }
    }

    /**
     * Called when the accuracy of the registered sensor has changed.  Unlike
     * onSensorChanged(), this is only called when this accuracy value changes.
     *
     * <p>See the SENSOR_STATUS_* constants in
     * {@link SensorManager SensorManager} for details.
     *
     * @param sensor
     * @param accuracy The new accuracy of this sensor, one of
     *                 {@code SensorManager.SENSOR_STATUS_*}
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void changeStyle (float currentValue){
        if(currentValue <= 30 ) {
            mLayout.setVisibility(View.VISIBLE);
        }
        else {
            mLayout.setVisibility(View.INVISIBLE);
        }
    }

    public void onStop(){
        super.onStop();
        //Menghentikan event, dengan mengunregister listenernya
        mSensorManager.unregisterListener(this);
    }

    public void displayFragment(){
        CountriesFragment showMoreFragment = CountriesFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, showMoreFragment).addToBackStack(null).commit();
        showContries.setText(R.string.show_less);
        isFragmentDisplayed = true;
    }

    public void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        CountriesFragment showMoreFragment = (CountriesFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (showMoreFragment != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(showMoreFragment).commit();
        }
        showContries.setText(R.string.show_countries);
        isFragmentDisplayed = false;
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