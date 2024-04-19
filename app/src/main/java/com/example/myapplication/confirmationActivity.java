package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class confirmationActivity extends AppCompatActivity {
    int temperature, knob;
    String start, end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String setting = getIntent().getStringExtra("mode");

        switch (Objects.requireNonNull(setting)){
            case "manualKnob":
                knob = getIntent().getIntExtra("knobSetting", 0);
                break;
            case "manualTemp":
                temperature = getIntent().getIntExtra("manualTempInt", 69);
                break;
            case "scheduleKnob":
                knob = getIntent().getIntExtra("knobSetting", 0);
                start =  getIntent().getStringExtra("knobStart");
                end =  getIntent().getStringExtra("knobEnd");
                break;
            case "scheduleTemp":
                temperature = getIntent().getIntExtra("tempSetting", 69);
                start =  getIntent().getStringExtra("tempStart");
                end =  getIntent().getStringExtra("tempEnd");
                break;
            default:
                // output an error of some sort
                break;
        }
    }
}