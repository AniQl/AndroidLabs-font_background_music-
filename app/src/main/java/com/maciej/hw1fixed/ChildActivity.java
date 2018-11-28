package com.maciej.hw1fixed;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ChildActivity extends AppCompatActivity {
    Spinner spinner;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colorArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void confirmaction(View view) {
        Intent data = new Intent();
        String color = ((Spinner) (findViewById(R.id.spinner))).getSelectedItem().toString();
        data.putExtra("color", color);
        setResult(RESULT_OK, data);
        finish();
    }
    public void cancel(View view) {

        setResult(RESULT_CANCELED);
        finish();
    }
}
