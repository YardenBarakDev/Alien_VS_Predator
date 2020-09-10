package com.bawp.alienvspredator;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.bumptech.glide.Glide;

public class Activity_OpenScreen extends AppCompatActivity {

    private Button openScreen_BTN_startGame;
    private Button openScreen_BTN_topScores;
    private ImageView openScreen_IMAGE_background;

    private androidx.appcompat.widget.AppCompatImageButton openScreen_ImgBTN_pauseMusic;
    private androidx.appcompat.widget.AppCompatImageButton openScreen_ImgBTN_playMusic;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);

        findViewByIdAll();
        loadImages();
        permissionsToRequest();
        mediaPlayer = MediaPlayer.create(Activity_OpenScreen.this, R.raw.openscreen_song);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        openScreen_BTN_startGame.setOnClickListener(openScreenButtonListener);
        openScreen_BTN_topScores.setOnClickListener(openScreenButtonListener);
        openScreen_ImgBTN_pauseMusic.setOnClickListener(openScreenButtonListener);
        openScreen_ImgBTN_playMusic.setOnClickListener(openScreenButtonListener);
    }

    private View.OnClickListener openScreenButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openScreenButtons(view);
        }
    };

    /*
      monitor which button was clicked and perform the necessary code
      by checking the buttons tag
       */
    private void openScreenButtons(View view) {
        switch (((String) view.getTag())) {
            case "openScreen_BTN_startGame":
                Intent intent = new Intent(Activity_OpenScreen.this, Activity_Game.class);
                startActivity(intent);
                break;
            case "openScreen_BTN_topScores":
                Intent intent2 = new Intent(Activity_OpenScreen.this, Activity_TopScores.class);
                startActivity(intent2);
                break;
            case "openScreen_ImgBTN_pauseMusic":
                if (mediaPlayer.isPlaying())
                mediaPlayer.pause();
                break;
            case "openScreen_ImgBTN_playMusic":
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
                break;
        }
    }
        private void loadImages () {
            Glide
                    .with(this)
                    .load(R.drawable.openscreen_background_img)
                    .into(openScreen_IMAGE_background);
        }


        //ask for location permission
    public void permissionsToRequest() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
             final String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                   Manifest.permission.ACCESS_COARSE_LOCATION,
                 Manifest.permission.INTERNET};

            ActivityCompat.requestPermissions(Activity_OpenScreen.this, permissions, 1);
        }
    }

    //check if the permission was granted
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults.length > 0
                    && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                messageDialog();
                openScreen_BTN_startGame.setEnabled(false);

            }
            else{
                openScreen_BTN_startGame.setEnabled(true);

            }
        }
    }
    //create message dialog which tell the user he must agree to the permissions
    //in order to use the app
    private void messageDialog() {
        new AlertDialog.Builder(Activity_OpenScreen.this).setMessage(R.string.openScreen_Rejected_permission).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        permissionsToRequest();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create().show();
    }

    private void findViewByIdAll () {
        openScreen_BTN_startGame = findViewById(R.id.openScreen_BTN_startGame);
        openScreen_BTN_topScores = findViewById(R.id.openScreen_BTN_topScores);
        openScreen_IMAGE_background = findViewById(R.id.openScreen_IMAGE_background);
        openScreen_ImgBTN_pauseMusic = findViewById(R.id.openScreen_ImgBTN_pauseMusic);
        openScreen_ImgBTN_playMusic = findViewById(R.id.openScreen_ImgBTN_playMusic);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mediaPlayer.isPlaying()){
            mediaPlayer = MediaPlayer.create(Activity_OpenScreen.this, R.raw.openscreen_song);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }

    }
}