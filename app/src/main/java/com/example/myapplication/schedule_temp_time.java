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

public class schedule_temp_time extends AppCompatActivity {
private int scheduleTempSetting = 69;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule_temp_time);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText timeStart, timeEnd;

        timeStart = (EditText)findViewById(R.id.startTimeScheduleTemp);
        timeEnd = (EditText)findViewById(R.id.endTimeScheduleTemp);

        Button confirmScheduleKnob = findViewById(R.id.confirmScheduleTempTime);

        scheduleTempSetting = getIntent().getIntExtra("scheduleTempInt", 69);
        confirmScheduleKnob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringStart = timeStart.getText().toString();
                String stringEnd = timeEnd.getText().toString();

                Intent intent = new Intent(schedule_temp_time.this, confirmationActivity.class);

                intent.putExtra("tempStart", stringStart);
                intent.putExtra("tempEnd", stringEnd);
                intent.putExtra("tempSetting", scheduleTempSetting);

                intent.putExtra("mode", "scheduleTemp");

                startActivity(intent);
            }
        });
    }
}