package com.example.ericsyoga.geocalc;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.location.Location;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;

public class CalcActivity extends AppCompatActivity {

    public static final int SETTINGS = 1;
    private String dists = "Kilometers";
    private String bears = "Degrees";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        EditText lat1 = (EditText) findViewById(R.id.Lat1);
        EditText lat2 = (EditText) findViewById(R.id.Lat2);
        EditText long1 = (EditText) findViewById(R.id.Long1);
        EditText long2 = (EditText) findViewById(R.id.Long2);
        Button calculate = (Button) findViewById(R.id.CalcButton);
        Button clear = (Button) findViewById(R.id.ClearButton);
        TextView distance = (TextView) findViewById(R.id.Distance);
        TextView bearing = (TextView) findViewById(R.id.Bearing);
        Button settings = (Button) findViewById(R.id.settingsButton);

        calculate.setOnClickListener(v -> {
            try {
                double lati1 = Double.parseDouble(lat1.getText().toString());
                double lati2 = Double.parseDouble(lat2.getText().toString());
                double longi1 = Double.parseDouble(long1.getText().toString());
                double longi2 = Double.parseDouble(long2.getText().toString());
                float[] results = new float[2];
                Location.distanceBetween(lati1, longi1, lati2, longi2, results);
                String Kilo = String.format("%.2f", results[0] / 1000);
                String Mile = String.format("%.2f", (results[0] * 0.621371) / 1000);
                String Deg = String.format("%.2f", results[1]);
                String Mils = String.format("%.2f", results[1] * 17.777777777778);
                if(dists.equals("Kilometers")) {
                    distance.setText("Distance: " + Kilo + " Kilometers");
                } else {
                    distance.setText("Distance: " + Mile + " Miles");
                }
                if(bears.equals("Degrees")) {
                    bearing.setText("Bearing: " + Deg + " Degrees");
                } else {
                    bearing.setText("Bearing: " + Mils + " Mils");
                }
            } catch (NumberFormatException e) {
                Snackbar.make(lat1, "At least one field was invalid", Snackbar.LENGTH_LONG).show();
            }
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        });

        clear.setOnClickListener(v -> {
            lat1.setText("");
            lat2.setText("");
            long1.setText("");
            long2.setText("");
            distance.setText("Distance: ");
            bearing.setText("Bearing: ");
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        });

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(CalcActivity.this, SettingsActivity.class);
            startActivityForResult(intent, SETTINGS);
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == SETTINGS) {
            String text = data.getStringExtra("distance");
            String[] split = text.split("\\s+");
            dists = split[0];
            bears = split[1];

            EditText lat1 = (EditText) findViewById(R.id.Lat1);
            EditText lat2 = (EditText) findViewById(R.id.Lat2);
            EditText long1 = (EditText) findViewById(R.id.Long1);
            EditText long2 = (EditText) findViewById(R.id.Long2);
            TextView distance = (TextView) findViewById(R.id.Distance);
            TextView bearing = (TextView) findViewById(R.id.Bearing);

            try {
                double lati1 = Double.parseDouble(lat1.getText().toString());
                double lati2 = Double.parseDouble(lat2.getText().toString());
                double longi1 = Double.parseDouble(long1.getText().toString());
                double longi2 = Double.parseDouble(long2.getText().toString());
                float[] results = new float[2];
                Location.distanceBetween(lati1, longi1, lati2, longi2, results);
                String Kilo = String.format("%.2f", results[0] / 1000);
                String Mile = String.format("%.2f", (results[0] * 0.621371) / 1000);
                String Deg = String.format("%.2f", results[1]);
                String Mils = String.format("%.2f", results[1] * 17.777777777778);
                if(dists.equals("Kilometers")) {
                    distance.setText("Distance: " + Kilo + " Kilometers");
                } else {
                    distance.setText("Distance: " + Mile + " Miles");
                }
                if(bears.equals("Degrees")) {
                    bearing.setText("Bearing: " + Deg + " Degrees");
                } else {
                    bearing.setText("Bearing: " + Mils + " Mils");
                }
            } catch (NumberFormatException e) {
                Snackbar.make(lat1, "At least one field was invalid", Snackbar.LENGTH_LONG).show();
            }
        }
    }
}