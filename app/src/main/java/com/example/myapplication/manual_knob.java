package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class manual_knob extends AppCompatActivity {
    int knobSettingInt = 0;
    private static final int PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manual_knob);
        // Check for Internet permission for SDK 23 or higher
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {

                //rationale to display why the permission is needed
                Toast.makeText(this, "The app needs access to the Internet to send data to the ESP32", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_CODE);
        }

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
                knobSettingInt = 0;

            }
        });
        confirmManualKnob.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(manual_knob.this, MainActivity.class);
                intent.putExtra("knobSetting",knobSettingInt);
                intent.putExtra("mode", "manualKnob");
                sendData(String.valueOf(knobSettingInt));

                startActivity(intent);
            }
        });
    }
    //Check for result of permissions and feedback accordingly
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can perform the network operation here
                Log.d("Permission", "Granted");
            } else {
                // Permission denied, show a message to the user
                Log.d("Permission", "Denied");
                Toast.makeText(this, "Permission denied, you cannot perform network operations", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //  the way data transfer will work is that the first item character of the string will
    //  dictate if the action is manual or schedule ( 0 for manual, 1 for schedule)
    //  the next character will dictate whether the action is changing by way of the knob or
    //  setting a temperature ( 0 for knob, 1 for temperature)
    //  thus the string will look like so...
    //  [manual or schedule] [knob or temperature] [knob setting num. or degrees fahrenheit] [time frame]
    //          1                       1                       72                              02121109
    //  the above string, all together, will look like "117202121109", which means that the
    //  instruction is a scheduled temperature instruction from 2:12 to 11:09 that is set to 72 degrees fahrenheit
    private void sendData(String knobSettingString) {
        // Set up a thread to send the data in the background
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Connect to the ESP32's IP address and port
                    Log.d("Sending Data", "Button Pressed, connecting to ESP32.....");
                    Socket socket = new Socket("192.168.4.1", 80);
                    PrintWriter out = new PrintWriter(socket.getOutputStream());

                    String dataString = "0,0" + "," + knobSettingString;
                    // Send the data
                    out.println(dataString);
                    out.flush();
                    Log.d("Sending Data", "Data Sent!");

                    // Close the connection
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Sending Data", "Error Sending Data: " + e.getMessage());
                }
            }
        }).start();
    }
}