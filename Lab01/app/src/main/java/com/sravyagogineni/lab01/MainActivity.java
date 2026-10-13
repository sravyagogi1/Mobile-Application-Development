package com.sravyagogineni.lab01;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button incrementButton;
    TextView greetingDisplay;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        incrementButton = findViewById(R.id.increment_button);
        greetingDisplay = findViewById(R.id.greeting_textview);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("incrementing: " + ++count);
                Log.i("incrementing", "" + count);
                greetingDisplay.setText("" + count);
                DisplayColor();
            }
        });
    }

    public void decrement(View view) {
        System.out.println("decrementing: " + --count);
        greetingDisplay.setText("" + count);
        DisplayColor();
    }
    private void DisplayColor() {
        greetingDisplay.setText(String.valueOf(count));
        if(count<0)
            greetingDisplay.setTextColor(Color.parseColor("#E02424"));
        else if(count>=10)
            greetingDisplay.setTextColor(Color.parseColor("#5CDB40"));
        else
            greetingDisplay.setTextColor(Color.BLACK);
    }
}