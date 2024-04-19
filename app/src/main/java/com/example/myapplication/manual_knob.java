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
    private int knobSettingInt = 0;
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



        Button confirmManualKnob = findViewById(R.id.confirmManualKnob);
        Button hiCoolManual = findViewById(R.id.hiCoolManual);
        Button hiFanManual = findViewById(R.id.hiFanManual);
        Button loCoolManual = findViewById(R.id.loCoolManual);
        Button offACManual = findViewById(R.id.offManual);

        hiCoolManual.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                knobSettingInt = 1;
            }
        });
        hiFanManual.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                knobSettingInt = 2;

            }
        });
        loCoolManual.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                knobSettingInt = 3;

            }
        });
        offACManual.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                knobSettingInt = 3;

            }
        });
        confirmManualKnob.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(manual_knob.this, confirmationActivity.class);
                intent.putExtra("manualKnobInt",knobSettingInt);
                intent.putExtra("mode", "manualKnob");
                startActivity(intent);
            }
        });
    }
}