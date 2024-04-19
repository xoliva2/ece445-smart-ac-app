package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class manual_temp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manual_temp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText text;
        String tempValue;
        text = (EditText)findViewById(R.id.manualTempText);


        Button confirmManualTemp = findViewById(R.id.confirmManualTemp);

        confirmManualTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempString = text.getText().toString();

                Intent intent = new Intent(manual_temp.this, confirmationActivity.class);
                intent.putExtra("manualTempInt",Integer.parseInt(tempString));
                intent.putExtra("mode", "manualTemp");
                startActivity(intent);
            }
        });
    }
}