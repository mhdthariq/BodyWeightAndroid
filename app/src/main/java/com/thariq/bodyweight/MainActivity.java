package com.thariq.bodyweight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText inputHeight, inputWeight;
    private TextView outputResultStatus, outputResultWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        inputHeight = findViewById(R.id.inputHeight);
        inputWeight = findViewById(R.id.inputWeight);
        Button calculateButton = findViewById(R.id.calculateButton);
        outputResultStatus = findViewById(R.id.outputResultStatus);
        outputResultWeight = findViewById(R.id.outputResultWeight);

        // Set action for the calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateIdealWeight();
            }
        });
    }

    // Function to calculate ideal body weight
    private void calculateIdealWeight() {
        // Get values from EditText
        String heightStr = inputHeight.getText().toString();
        String weightStr = inputWeight.getText().toString();

        // Check if the input is empty
        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert string to float
        float height = Float.parseFloat(heightStr);
        float weight = Float.parseFloat(weightStr);

        // Calculate ideal body weight using the formula: (Height - 100) - 10%
        float idealWeight = (height - 100) - (0.1f * (height - 100));

        // Determine if the user's weight is ideal, underweight, or overweight
        String statusMessage;
        String weightMessage = "Your ideal body weight is " + idealWeight + " kg.";

        if (weight < idealWeight * 0.9) {
            statusMessage = "You're Underweight.";
        } else if (weight > idealWeight * 1.1) {
            statusMessage = "You're Overweight.";
        } else {
            statusMessage = "You're at an Ideal Weight.";
        }

        // Display the results in the respective TextViews
        outputResultStatus.setText(statusMessage);
        outputResultWeight.setText(weightMessage);
    }
}
