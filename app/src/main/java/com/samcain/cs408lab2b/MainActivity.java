package com.samcain.cs408lab2b;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.samcain.cs408lab2b.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private EditText fahrenheitInput, celsiusInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Initialization
        celsiusInput = findViewById(R.id.celsiusInput);
        fahrenheitInput = findViewById(R.id.fahrenheitInput);
        Button convertButton = findViewById(R.id.convertButton);

        // Click listeners to clear the fields when clicking on text input
        fahrenheitInput.setOnClickListener(v -> celsiusInput.setText(""));
        celsiusInput.setOnClickListener(v -> fahrenheitInput.setText(""));

        // Conversion Listener/Logic
        convertButton.setOnClickListener(v -> {
            double celsius, fahrenheit;

            String fahrenheitText = fahrenheitInput.getText().toString().trim();
            String celsiusText = celsiusInput.getText().toString().trim();


            if (!fahrenheitText.isEmpty() && celsiusText.isEmpty()) {
                // Fahrenheit to Celsius
                try {
                    fahrenheit = Double.parseDouble(fahrenheitText);
                    celsius = (fahrenheit - 32) * 5/9;
                    celsiusInput.setText(String.format("%.2f", celsius));
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid Fahrenheit Input", Toast.LENGTH_SHORT).show();
                }
            } else if (!celsiusText.isEmpty() && fahrenheitText.isEmpty()) {
                // Celsius to Fahrenheit
                try {
                    celsius = Double.parseDouble(celsiusText);
                    fahrenheit = (celsius * 9/5) + 32;
                    fahrenheitInput.setText(String.format("%.2f", fahrenheit));
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid Celsius Input", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter a value in only one field", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
