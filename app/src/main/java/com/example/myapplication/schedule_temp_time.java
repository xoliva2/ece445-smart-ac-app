package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class schedule_temp_time extends AppCompatActivity {
    int scheduleTempSetting = 69;
    private static final int PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule_temp_time);

        // Check for Internet permission for SDK 23 or higher
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.INTERNET)) {

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
                String[] startParts = stringStart.split(":");  // Split the string by ":"
                String[] endParts = stringEnd.split(":");  // Split the string by ":"
                String startHour = startParts[0];  // Convert the hour to an integer
                String startMinute = startParts[1];  // Convert the minute to an integer
                String endHour = endParts[0];  // Convert the hour to an integer
                String endMinute = endParts[1];  // Convert the minute to an integer

                if (startHour.length() == 1){
                    startHour = "0" + startHour;
                }
                if (endHour.length() == 1){
                    endHour = "0" + endHour;
                }

                String timeConcat = startHour + startMinute + endHour + endMinute;
                // TODO: check if format is 0921 or 921

                Intent intent = new Intent(schedule_temp_time.this, MainActivity.class);

                intent.putExtra("tempStart", stringStart);
                intent.putExtra("tempEnd", stringEnd);
                intent.putExtra("tempSetting", scheduleTempSetting);

                intent.putExtra("mode", "scheduleTemp");

                sendData(String.valueOf(scheduleTempSetting), timeConcat);
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
    private void sendData(String temperatureString, String timeString) {
        // Set up a thread to send the data in the background
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Connect to the ESP32's IP address and port
                    Log.d("Sending Data", "Button Pressed, connecting to ESP32.....");
                    Socket socket = new Socket("192.168.4.1", 80);
                    PrintWriter out = new PrintWriter(socket.getOutputStream());

                    // Send the data
                    String dataString = "11" + temperatureString + timeString;
                    out.println("Hello, ESP32!");
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