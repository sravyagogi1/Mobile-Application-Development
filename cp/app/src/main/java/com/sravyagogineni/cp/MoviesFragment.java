package com.sravyagogineni.cp;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import androidx.fragment.app.Fragment;

public class MoviesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);

        Vibrator vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);

        view.findViewById(R.id.box1).setOnClickListener(v -> openMovie(vibrator, IronManActivity.class));
        view.findViewById(R.id.box2).setOnClickListener(v -> openMovie(vibrator, HulkActivity.class));
        view.findViewById(R.id.box3).setOnClickListener(v -> openMovie(vibrator, IronMan2Activity.class));
        view.findViewById(R.id.box4).setOnClickListener(v -> openMovie(vibrator, ThorActivity.class));
        view.findViewById(R.id.box5).setOnClickListener(v -> openMovie(vibrator, CaptainAmericaActivity.class));
        view.findViewById(R.id.box6).setOnClickListener(v -> openMovie(vibrator, AvengersActivity.class));
        view.findViewById(R.id.box7).setOnClickListener(v -> openMovie(vibrator, IronMan3Activity.class));
        view.findViewById(R.id.box8).setOnClickListener(v -> openMovie(vibrator, ThorDarkWorldActivity.class));
        view.findViewById(R.id.box9).setOnClickListener(v -> openMovie(vibrator, WinterSoldierActivity.class));
        view.findViewById(R.id.box10).setOnClickListener(v -> openMovie(vibrator, GuardiansActivity.class));
        view.findViewById(R.id.box11).setOnClickListener(v -> openMovie(vibrator, UltronActivity.class));
        view.findViewById(R.id.box12).setOnClickListener(v -> openMovie(vibrator, AntManActivity.class));
        view.findViewById(R.id.box13).setOnClickListener(v -> openMovie(vibrator, CivilWarActivity.class));
        view.findViewById(R.id.box14).setOnClickListener(v -> openMovie(vibrator, HomecomingActivity.class));
        view.findViewById(R.id.box15).setOnClickListener(v -> openMovie(vibrator, BlackPantherActivity.class));
        view.findViewById(R.id.box16).setOnClickListener(v -> openMovie(vibrator, InfinityWarActivity.class));
        view.findViewById(R.id.box17).setOnClickListener(v -> openMovie(vibrator, CaptainMarvelActivity.class));
        view.findViewById(R.id.box18).setOnClickListener(v -> openMovie(vibrator, EndgameActivity.class));
        view.findViewById(R.id.box19).setOnClickListener(v -> openMovie(vibrator, FarFromHomeActivity.class));
        view.findViewById(R.id.box20).setOnClickListener(v -> openMovie(vibrator, BlackWidowActivity.class));

        return view;
    }

    private void openMovie(Vibrator vibrator, Class<?> activityClass) {
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        Intent intent = new Intent(requireContext(), activityClass);
        startActivity(intent);
    }
}
