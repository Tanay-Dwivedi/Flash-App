package com.example.flashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton switching;    // Toggle button for turning the flashlight on/off
    ImageView bulboning, bulboffing;   // Image views for representing the bulb's on/off state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        switching = findViewById(R.id.toggleButton);
        bulboffing = findViewById(R.id.bulbOff);
        bulboning = findViewById(R.id.bulbOn);

        // Set an OnClickListener for the toggle button
        switching.setOnClickListener(v -> {
            if (switching.getText().toString().equals("Turn ON")) {
                // If the button text is "Turn ON," turn off the flashlight
                showToast("Bulb now off");
                bulboning.setVisibility(View.INVISIBLE);
                changeLightState(false);
            } else {
                // If the button text is not "Turn ON," turn on the flashlight
                showToast("Bulb now on");
                bulboning.setVisibility(View.VISIBLE);
                changeLightState(true);
            }
        });
    }

    // Method to change the state of the flashlight (on/off)
    private void changeLightState(boolean state) {
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        String camID = null;
        try {
            camID = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(camID, state);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to show a toast message
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
