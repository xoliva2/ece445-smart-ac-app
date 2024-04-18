package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class manual_knob extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manual_knob);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ValuesClass valuesClass;
        valuesClass = new ValuesClass(1);

        Button confirmManualKnob = findViewById(R.id.confirmManualKnob);
        Button hiCoolManual = findViewById(R.id.hiCoolManual);
        Button hiFanManual = findViewById(R.id.hiFanManual);
        Button loCoolManual = findViewById(R.id.loCoolManual);
        Button offACManual = findViewById(R.id.offManual);
        confirmManualKnob.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(manual_knob.this, MainActivity.class);
                startActivity(intent);
            }
        });
        hiCoolManual.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                valuesClass.setManualKnobSetting(1);

            }
        });

    }
}