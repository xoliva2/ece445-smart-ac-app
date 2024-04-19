package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class schedule_knob_time extends AppCompatActivity {
    private int scheduleKnobSetting = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule_knob_time);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText timeStart, timeEnd;

        timeStart = (EditText)findViewById(R.id.startTimeScheduleKnob);
        timeEnd = (EditText)findViewById(R.id.endTimeScheduleKnob);

        Button confirmScheduleKnobTime = findViewById(R.id.confirmScheduleKnobTime);


        scheduleKnobSetting = getIntent().getIntExtra("scheduleKnobInt", 0);
        confirmScheduleKnobTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringStart = timeStart.getText().toString();
                String stringEnd = timeEnd.getText().toString();

                Intent intent = new Intent(schedule_knob_time.this, confirmationActivity.class);

                intent.putExtra("knobStart", stringStart);
                intent.putExtra("knobEnd", stringEnd);
                intent.putExtra("knobSetting", scheduleKnobSetting);

                intent.putExtra("mode", "scheduleKnob");

                startActivity(intent);
            }
        });
    }
}