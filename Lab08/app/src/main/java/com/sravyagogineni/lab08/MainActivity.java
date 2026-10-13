package com.sravyagogineni.lab08;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawView drawView = findViewById(R.id.drawView);

        findViewById(R.id.AddOrnament).setOnClickListener(v ->
                drawView.addOrnament()
        );

        findViewById(R.id.AddPresent).setOnClickListener(v ->
                drawView.addPresent()
        );

        findViewById(R.id.RemoveAll).setOnClickListener(v ->
                drawView.removeAll()
        );
    }
}
