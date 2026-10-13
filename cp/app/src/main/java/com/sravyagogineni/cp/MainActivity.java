package com.sravyagogineni.cp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.box1).setOnClickListener(v -> openMovie(IronManActivity.class));
        findViewById(R.id.box2).setOnClickListener(v -> openMovie(HulkActivity.class));
        findViewById(R.id.box3).setOnClickListener(v -> openMovie(IronMan2Activity.class));
        findViewById(R.id.box4).setOnClickListener(v -> openMovie(ThorActivity.class));
        findViewById(R.id.box5).setOnClickListener(v -> openMovie(CaptainAmericaActivity.class));
        findViewById(R.id.box6).setOnClickListener(v -> openMovie(AvengersActivity.class));
        findViewById(R.id.box7).setOnClickListener(v -> openMovie(IronMan3Activity.class));
        findViewById(R.id.box8).setOnClickListener(v -> openMovie(ThorDarkWorldActivity.class));
        findViewById(R.id.box9).setOnClickListener(v -> openMovie(WinterSoldierActivity.class));
        findViewById(R.id.box10).setOnClickListener(v -> openMovie(GuardiansActivity.class));
        findViewById(R.id.box11).setOnClickListener(v -> openMovie(UltronActivity.class));
        findViewById(R.id.box12).setOnClickListener(v -> openMovie(AntManActivity.class));
        findViewById(R.id.box13).setOnClickListener(v -> openMovie(CivilWarActivity.class));
        findViewById(R.id.box14).setOnClickListener(v -> openMovie(HomecomingActivity.class));
        findViewById(R.id.box15).setOnClickListener(v -> openMovie(BlackPantherActivity.class));
        findViewById(R.id.box16).setOnClickListener(v -> openMovie(InfinityWarActivity.class));
        findViewById(R.id.box17).setOnClickListener(v -> openMovie(CaptainMarvelActivity.class));
        findViewById(R.id.box18).setOnClickListener(v -> openMovie(EndgameActivity.class));
        findViewById(R.id.box19).setOnClickListener(v -> openMovie(FarFromHomeActivity.class));
        findViewById(R.id.box20).setOnClickListener(v -> openMovie(BlackWidowActivity.class));
    }

    private void openMovie(Class<?> activityClass) {
        Intent intent = new Intent(MainActivity.this,activityClass);
        startActivity(intent);
    }
}