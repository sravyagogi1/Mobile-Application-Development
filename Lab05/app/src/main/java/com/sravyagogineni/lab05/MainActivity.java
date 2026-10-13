package com.sravyagogineni.lab05;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String TAG = "com.example.lab05temp.sharedprefs";
    LifecycleData currentRun, lifeTime;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView currentRunTV, lifeTimeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        currentRun = new LifecycleData();
        currentRun.duration="Current Run";

        String lifecycleDataAsString = sharedPreferences.getString("lifetime", "");

        if (lifecycleDataAsString.equals("")){
            lifeTime = new LifecycleData();
            lifeTime.duration = "Lifetime Data";
        } else {
            lifeTime = LifecycleData.parseJSON(lifecycleDataAsString);
        }

        currentRunTV = findViewById(R.id.current);
        lifeTimeTV = findViewById(R.id.lifetime);

        String currentEnclosingMethod = new Throwable().getStackTrace()[0].getMethodName();
        updateCount(currentEnclosingMethod);
    }

    public void storeData(){
        editor.putString("lifetime", lifeTime.toJSON()).apply();
    }

    public void displayData(){
        currentRunTV.setText(currentRun.toString());
        lifeTimeTV.setText(lifeTime.toString());
    }
    public void updateCount(String currentEnclosingMethod){
        lifeTime.updateEvent(currentEnclosingMethod);
        currentRun.updateEvent(currentEnclosingMethod);
        displayData();
        storeData();
    }
    @Override
    protected void onStart() {
        super.onStart();
        updateCount(new Throwable().getStackTrace()[0].getMethodName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCount(new Throwable().getStackTrace()[0].getMethodName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateCount(new Throwable().getStackTrace()[0].getMethodName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        updateCount(new Throwable().getStackTrace()[0].getMethodName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateCount(new Throwable().getStackTrace()[0].getMethodName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateCount(new Throwable().getStackTrace()[0].getMethodName());
    }
}
