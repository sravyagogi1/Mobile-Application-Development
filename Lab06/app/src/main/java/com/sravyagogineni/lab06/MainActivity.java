package com.sravyagogineni.lab06;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    RecyclerView.Adapter mMyFragmentStateAdapter;
    int NUM_ITEMS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.container);
        mMyFragmentStateAdapter = new myFragmentStateAdapter(this);
        viewPager2.setAdapter(mMyFragmentStateAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int fragmentNumber = position + 1;
                Toast.makeText(MainActivity.this,
                        "You are now viewing the majestic Fragment #" + fragmentNumber + "!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class myFragmentStateAdapter extends FragmentStateAdapter {
        public myFragmentStateAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return MainFragment.newInstance(viewPager2);
        }

        @Override
        public int getItemCount() {
            return NUM_ITEMS;
        }
    }
}
