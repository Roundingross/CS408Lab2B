package com.samcain.cs408lab2b;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.samcain.cs408lab2b.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void onClick(View view) {
        // Values
        EditText celsiusInput = findViewById(R.id.celsiusInput);
        EditText fahrenheitInput = findViewById(R.id.fahrenheitInput);
        // Convert to String
        String celsiusValue = celsiusInput.getText().toString();
        String fahrenheitValue = fahrenheitInput.getText().toString();
        // Doubles
        double fahrenheit = 0, celsius = 0;

        // Conversion Logic
        try {
            if (!celsiusValue.isEmpty()) {
                celsius = Double.parseDouble(celsiusValue);
                fahrenheit = (celsius * 9/5) + 32;
                fahrenheitInput.setText(String.valueOf(fahrenheit));
                celsiusInput.setText("");
            } else if (!fahrenheitValue.isEmpty()) {
                fahrenheit = Double.parseDouble(fahrenheitValue);
                celsius = (fahrenheit - 32) * 5/9;
                celsiusInput.setText(String.valueOf(celsius));
                fahrenheitInput.setText("");
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numerical values", Toast.LENGTH_SHORT).show();
        }
    }


}
