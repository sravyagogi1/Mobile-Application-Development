package com.sravyagogineni.finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import android.widget.ImageButton;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class GameActivity extends AppCompatActivity {

    private int score = 0;
    private int highScore = 0;
    private int randomNumber;
    private int attempts = 0;

    private TextView scoreText, highScoreText, selectedNumberText;
    private SeekBar numberSeekBar;
    private ImageView box1, box2, box3, box4;

    private HashMap<String, String[]> categoryMap;
    private List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageButton backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(v -> finish());

        scoreText = findViewById(R.id.scoreText);
        highScoreText = findViewById(R.id.highScoreText);
        selectedNumberText = findViewById(R.id.selectedNumberText);
        numberSeekBar = findViewById(R.id.numberSeekBar);
        Button submitButton = findViewById(R.id.submitButton);

        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);

        setupData();
        updateScoreText();
        startNewRound();

        numberSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedNumberText.setText("Selected Number: " + (progress + 1));
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        submitButton.setOnClickListener(v -> {
            int guess = numberSeekBar.getProgress() + 1;

            attempts++;

            if (guess == randomNumber) {
                score++;
                if (score > highScore) highScore = score;
                scoreText.startAnimation(bounce);
                Toast.makeText(GameActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                resetRound();
            } else {
                if (attempts == 1) {
                    Toast.makeText(GameActivity.this, "Wrong, try again.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GameActivity.this, "Wrong! The correct number was " + randomNumber, Toast.LENGTH_SHORT).show();
                    score = 0;
                    highScore = 0;
                    resetRound();
                }
            }
            updateScoreText();
        });
    }

    private void startNewRound() {
        attempts = 0;
        generateRandomNumber();
        displayRandomItems();
    }

    private void resetRound() {
        startNewRound();
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(10) + 1;
    }

    private void updateScoreText() {
        scoreText.setText("Score: " + score);
        highScoreText.setText("High Score: " + highScore);
    }

    private void setupData() {
        categoryMap = new HashMap<>();

        categoryMap.put("icecream", new String[]{
                "pistachio", "rumraisin", "cottoncandy", "mintchocolatechip", "strawberry",
                "orangesorbet", "vanilla", "chocolate", "cookiedough", "cookiesandcream"
        });

        categoryMap.put("drinks", new String[]{
                "blackcoffee", "clubsoda", "water", "icedtea", "smoothie",
                "milkshake", "hotchocolate", "lemonade", "bobatea", "shirleytemple"
        });

        categoryMap.put("colors", new String[]{
                "vomitgreen", "beige", "gray", "orange", "green",
                "yellow", "red", "purple", "pink", "blue"
        });

        categoryMap.put("activity", new String[]{
                "homework", "cleaning", "museum", "hiking", "bowling",
                "movies", "arcade", "shopping", "amusementpark", "beachday"
        });

        categoryMap.put("day", new String[]{
                "", "monday", "", "tuesday", "", "wednesday", "thursday", "sunday", "friday", "saturday"
        });

        categoryMap.put("fastfood", new String[]{
                "wendys", "arbys", "kfc", "subway", "mcdonalds",
                "burgerking", "tacobell", "dominos", "chipotle", "chickfila"
        });

        categoryMap.put("desserts", new String[]{
                "", "", "fruitcake", "", "cheesecake",
                "icecream", "cupcakes", "chocolatechipcookie", "brownie", "tiramisu"
        });

        categoryMap.put("states", new String[]{
                "westvirginia", "arkansas", "ohio", "newjersey", "northcarolina",
                "massachusetts", "texas", "florida", "newyork", "california"
        });

        categoryMap.put("holiday", new String[]{
                "groundhogday", "columbusday", "presidentsday", "laborday", "easter",
                "july4th", "thanksgiving", "newyearseve", "halloween", "christmas"
        });

        categoryMap.put("fruits", new String[]{
                "honeydew", "papaya", "blueberries", "apple", "banana",
                "oranges", "strawberry", "peach", "watermelon", "mango"
        });

        categories = new ArrayList<>(categoryMap.keySet());
    }


    private void displayRandomItems() {
        Random random = new Random();

        List<String> chosenCategories = new ArrayList<>();
        while (chosenCategories.size() < 4) {
            String cat = categories.get(random.nextInt(categories.size()));
            if (!chosenCategories.contains(cat)) {
                chosenCategories.add(cat);
            }
        }

        ImageView[] boxes = {box1, box2, box3, box4};

        for (int i = 0; i < 4; i++) {
            String category = chosenCategories.get(i);
            String[] items = categoryMap.get(category);
            String imageName = items[randomNumber - 1];

            int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            boxes[i].setImageResource(resId);
        }
    }


}