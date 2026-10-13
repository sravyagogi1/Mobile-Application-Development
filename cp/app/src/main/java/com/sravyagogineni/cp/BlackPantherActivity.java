package com.sravyagogineni.cp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class BlackPantherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackpanther);

        // Back button
        findViewById(R.id.back_button).setOnClickListener(v -> finish());
    }
}