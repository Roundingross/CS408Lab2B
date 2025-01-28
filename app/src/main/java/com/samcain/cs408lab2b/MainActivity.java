package com.samcain.cs408lab2b;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

        // Clear field when clicked and it contains text
        fahrenheitInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !fahrenheitInput.getText().toString().isEmpty()) {
                fahrenheitInput.setText("");
            }
        });

        celsiusInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !celsiusInput.getText().toString().isEmpty()) {
                celsiusInput.setText("");
            }
        });

        // Add TextWatchers to clear the other input field when typing
        fahrenheitInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Clear Celsius input when typing in Fahrenheit input
                if (s.length() > 0) {
                    celsiusInput.setText("");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        celsiusInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Clear Fahrenheit input when typing in Celsius input
                if (s.length() > 0) {
                    fahrenheitInput.setText("");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Conversion logic
        convertButton.setOnClickListener(v -> {
            double celsius, fahrenheit;
            String fahrenheitText = fahrenheitInput.getText().toString().trim();
            String celsiusText = celsiusInput.getText().toString().trim();

            if (!fahrenheitText.isEmpty() && celsiusText.isEmpty()) {
                // Fahrenheit to Celsius
                try {
                    fahrenheit = Double.parseDouble(fahrenheitText);
                    celsius = (fahrenheit - 32) * 5 / 9;
                    celsiusInput.setText(String.format("%.2f", celsius));
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid Fahrenheit Input", Toast.LENGTH_SHORT).show();
                }
            } else if (!celsiusText.isEmpty() && fahrenheitText.isEmpty()) {
                // Celsius to Fahrenheit
                try {
                    celsius = Double.parseDouble(celsiusText);
                    fahrenheit = (celsius * 9 / 5) + 32;
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
