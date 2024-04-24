package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    int knob, temperature;
    String start, end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Button manualButton = findViewById(R.id.manual);
        Button scheduleButton = findViewById(R.id.schedule);


        Button updateButton = findViewById(R.id.updateButton);

        TextView topText = (TextView) findViewById(R.id.topSetting);
        TextView botText = (TextView) findViewById(R.id.bottomSetting);

        String emptyText = "_____";
        String errorText = "ERROR";

        String setting = getIntent().getStringExtra("mode");

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (Objects.requireNonNull(setting)){
                    case "manualKnob":
                        knob = getIntent().getIntExtra("knobSetting", 0);
                        String knobString = "";

                        if (knob == 0) {
                            knobString = "Off";
                        } else if (knob == 1) {
                            knobString = "High Cool";
                        } else if (knob == 2) {
                            knobString = "High Fan";
                        } else if (knob == 3) {
                            knobString = "Low Cool";
                        } else {
                            knobString = "ERROR";
                        }

                        topText.setText(knobString);
                        botText.setText(emptyText);
                        break;
                    case "manualTemp":
                        temperature = getIntent().getIntExtra("manualTempInt", 69);
                        String tempString = String.valueOf(temperature);
                        String concatenatedString4 = tempString + " °F";
                        topText.setText(concatenatedString4);
                        botText.setText(emptyText);
                        break;
                    case "scheduleKnob":
                        knob = getIntent().getIntExtra("knobSetting", 0);
                        start =  getIntent().getStringExtra("knobStart");
                        end =  getIntent().getStringExtra("knobEnd");

                        String concatenatedString = start + " - " + end;

                        topText.setText(String.valueOf(knob));
                        botText.setText(concatenatedString);
                        break;
                    case "scheduleTemp":
                        temperature = getIntent().getIntExtra("tempSetting", 69);
                        start =  getIntent().getStringExtra("tempStart");
                        end =  getIntent().getStringExtra("tempEnd");

                        String tempStringSchedule = String.valueOf(temperature);

                        String concatenatedString2 = start + " - " + end;
                        String concatenatedString3 = tempStringSchedule + " °F";

                        topText.setText(concatenatedString3);
                        botText.setText(concatenatedString2);

                        break;
                    default:
                        // output an error of some sort
                        topText.setText(errorText);
                        botText.setText(errorText);
                        break;
                }

            }
        });

        manualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this, manualSet.class);
                startActivity(intent);
            }
        });
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this, schedule_set.class);
                startActivity(intent);
            }
        });

    }
}