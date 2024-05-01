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

public class schedule_knob extends AppCompatActivity {
private int scheduleKnobSettingInt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule_knob);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Button confirmScheduleKnob = findViewById(R.id.confirmScheduleKnob);
        Button hiCoolSchedule = findViewById(R.id.hiCoolSchedule);
        Button hiFanSchedule = findViewById(R.id.hiFanSchedule);
        Button loCoolSchedule = findViewById(R.id.loCoolSchedule);
        Button offACSchedule = findViewById(R.id.offSchedule);

        hiCoolSchedule.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                scheduleKnobSettingInt = 1;

            }
        });
        hiFanSchedule.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                scheduleKnobSettingInt = 2;

            }
        });
        loCoolSchedule.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                scheduleKnobSettingInt = 3;

            }
        });
        offACSchedule.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                scheduleKnobSettingInt = 3;

            }
        });
        confirmScheduleKnob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(schedule_knob.this, schedule_knob_time.class);
                intent.putExtra("scheduleKnobInt", scheduleKnobSettingInt);
                startActivity(intent);
            }
        });
    }
}