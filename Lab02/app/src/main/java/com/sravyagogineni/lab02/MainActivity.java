package com.sravyagogineni.lab02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.Math;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button incrementButton;
    TextView greetingDisplay;
    TextView helpDisplay;
    int count = 0;
    String[] planets;
    EditText editText;
    Button submitButton;
    RadioButton leftRadBut, rightRadBut;
    ImageView myImageView;

    /**
     * @noinspection Convert2Lambda
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        incrementButton = findViewById(R.id.increment_button);
        greetingDisplay = findViewById(R.id.greeting_textview);
        helpDisplay = findViewById(R.id.help_textview);
        planets = getResources().getStringArray(R.array.planets_array);
        leftRadBut = findViewById(R.id.radio_left);
        rightRadBut = findViewById(R.id.radio_right);
        editText = findViewById(R.id.editText);
        submitButton = findViewById(R.id.submitButton);
        ImageView myImageView = findViewById(R.id.myImage);

        myImageView.setImageResource(R.drawable.icecream);

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightRadBut.isChecked())
                    count = ++count % planets.length;
                greetingDisplay.setText(planets[count]);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name = editText.getText().toString().trim();
                if(!name.isEmpty()) {
                    greetingDisplay.setText("Hello " + name + "!");
                    helpDisplay.setText("");
                }
                else {
                    int num = (int)(Math.random()*3);
                    String[] responses = {"Trying to ghost me?", "An anonymous hero, I see.", "You think your cool ignoring me?"};
                    greetingDisplay.setText(responses[num]);
                    helpDisplay.setText("You may not enter a empty submission, enter something in the text box before submitting.");
                }
            }
        });
    }

    public void decrement(View view){
        if(leftRadBut.isChecked())
            if(--count<0) count = planets.length-1;
        greetingDisplay.setText(planets[count]);
    }
}