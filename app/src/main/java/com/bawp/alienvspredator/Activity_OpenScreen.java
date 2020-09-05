package com.bawp.alienvspredator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class Activity_OpenScreen extends AppCompatActivity {

    private Button openScreen_BTN_startGame;
    private Button openScreen_BTN_topScores;
    private ImageView openScreen_IMAGE_background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);

        findViewByIdAll();
        loadImages();

        openScreen_BTN_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_OpenScreen.this, Activity_Game.class);
                startActivity(intent);
            }
        });

        openScreen_BTN_topScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_OpenScreen.this, Activity_TopScores.class);
                startActivity(intent);
            }
        });
    }

    private void loadImages() {
        Glide
                .with(this)
                .load(R.drawable.openscreen_background_img)
                .into(openScreen_IMAGE_background);
    }

    private void findViewByIdAll() {
        openScreen_BTN_startGame = findViewById(R.id.openScreen_BTN_startGame);
        openScreen_BTN_topScores = findViewById(R.id.openScreen_BTN_topScores);
        openScreen_IMAGE_background = findViewById(R.id.openScreen_IMAGE_background);
    }
}