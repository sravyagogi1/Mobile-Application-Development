package com.sravyagogineni.lab03;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String TAG = "com.goginenisravya.lab03.sharedprefs";
    Button bRight,bLeft;
    TextView tLeft, tRight;
    SeekBar seekBar;
    TextView[] views;
    ConstraintLayout layout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    long startTime, clicks;
    float cPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bRight =findViewById(R.id.bottomRight_button);
        bLeft = findViewById(R.id.bottomleft_button);
        tLeft = findViewById(R.id.topleft_textview);
        tRight = findViewById(R.id.topright_textview);
        seekBar = findViewById(R.id.seekbar);
        views = new TextView[]{bRight, bLeft, tLeft, tRight};
        layout = findViewById(R.id.main);
        bRight.setOnClickListener(this);
        bLeft.setOnClickListener(this);
        tLeft.setOnClickListener(this);
        tRight.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (TextView x:views) {
                    x.setTextSize(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                lastProgress = seekBar.getProgress();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Snackbar snackbar = Snackbar.make(layout, "Font Size Changed to " + seekBar.getProgress()+"sp", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                seekBar.setProgress(lastProgress);
                                for (TextView x:views) {
                                    x.setTextSize(lastProgress);
                                }
                                Snackbar.make(layout,"Font Size Reverted Back to " + lastProgress+"sp", Snackbar.LENGTH_LONG).show();
                            }
                        }
                );
                snackbar.setActionTextColor(Color.BLUE);
                View snackBarView = snackbar.getView();
                TextView textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);

                textView.setTextColor(Color.WHITE);
                snackbar.show();
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                editor.clear().apply();;
                setInitialValues();
                return false;
            }
        });
        setInitialValues();
        startTime = System.currentTimeMillis();
    }

    private void setInitialValues() {
        for (TextView x:views) {
            x.setText(sharedPreferences.getString(x.getTag().toString(),"0"));
        }
        seekBar.setProgress(30);
    }

    @Override
    public void onClick(View v) {
        TextView x = (TextView) v;
        x.setText(""+(Integer.parseInt(x.getText().toString())+1));
        editor.putString(x.getTag().toString(), x.getText().toString());
        editor.apply();
        cPS=++clicks/((System.currentTimeMillis() - startTime)/1000f);
        Toast.makeText(this, ""+cPS, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setInitialValues();
    }
}