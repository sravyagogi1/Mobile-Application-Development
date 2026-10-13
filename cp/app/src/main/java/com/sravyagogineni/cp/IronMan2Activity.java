package com.sravyagogineni.cp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class IronMan2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ironman2);

        // Back button functionality
        findViewById(R.id.back_button).setOnClickListener(v -> finish());
    }
}