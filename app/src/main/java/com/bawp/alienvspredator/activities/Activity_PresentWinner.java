package com.bawp.alienvspredator.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bawp.alienvspredator.util.MySP;
import com.bawp.alienvspredator.R;
import com.bawp.alienvspredator.model.Score;
import com.bawp.alienvspredator.model.TopTen;
import com.bumptech.glide.Glide;
import java.util.Date;

public class Activity_PresentWinner extends AppCompatActivity {

    private ImageView resultScreen_IMAGE_background;
    private Button presentWinner_BTN_toHighScores;
    private Button presentWinner_BTN_toMainPage;
    private TextView presentWinner_LBL_winnerInfo;
    private boolean winner;
    private Score score;
    private int rounds;
    private double longitude;
    private double latitude;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__presentwinner);
        findViewByIdAll();
        presentWinner_BTN_toHighScores.setOnClickListener(presentWinnerButtonListener);
        presentWinner_BTN_toMainPage.setOnClickListener(presentWinnerButtonListener);

        getCurrentLocation();
        playWinnerSound();

        getInfoFromDifferentActivity();
        loadWinnerImage();
        setWinnerText();
        createScore();
        checkAndSaveTopScores();
    }



    //create a score
    private void createScore() {
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        score = new Score().setLat(latitude).
                setLon(longitude).
                setNumOfMoves(rounds).
                setTimeStamp(currentDateTimeString).
                setWinner(winner);
    }

    //check if the score is good enough to be in the top ten array
    private void checkAndSaveTopScores() {

        //if the array size is less then 10 just add the score
        if (TopTen.getInstance().getTopTenScores().size() <  TopTen.getInstance().getARRAY_MAX_SIZE()){
            TopTen.getInstance().getTopTenScores().add(score);
            TopTen.getInstance().saveScoresInSP();

            //if the array size is 10 check if the last spot moves is grater then the current score
            //if it's grater it replace them
        } else if(TopTen.getInstance().getTopTenScores().get(TopTen.getInstance().getARRAY_MAX_SIZE() -1).getNumOfMoves()
                > score.getNumOfMoves()){
            TopTen.getInstance().getTopTenScores().set(TopTen.getInstance().getARRAY_MAX_SIZE() -1, score);
            TopTen.getInstance().saveScoresInSP();
        }

    }

    //check if all the permissions are granted and then fetch the location
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            new AlertDialog.Builder(Activity_PresentWinner.this).setMessage(R.string.openScreen_Rejected_permission).
                                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                    }).create().show();
            finish();
        }
        //get the location
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null){
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        //if the location is null the user will be alerted and the activity will close
        else{
            new AlertDialog.Builder(Activity_PresentWinner.this).setMessage("Unable to find location").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).create().show();
        }
    }

    private void setWinnerText() {
        if (winner) {
            String winnerText = " Alien win in " + rounds + " rounds";
            presentWinner_LBL_winnerInfo.setText(winnerText);
        }
        else {
            String winnerText = "Predator win in " + rounds + " rounds";
            presentWinner_LBL_winnerInfo.setText(winnerText);
        }
    }


    //buttons listener
    private View.OnClickListener presentWinnerButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           buttonClicked(view);
        }

        private void buttonClicked(View view) {
            switch (((String) view.getTag())) {
                case "presentWinner_BTN_toMainPage":
                    finish();
                    break;
                case "presentWinner_BTN_toHighScores":
                    Intent intent = new Intent(Activity_PresentWinner.this, Activity_TopScores.class);
                    startActivity(intent);
                    finish();
                    break;
            }

        }
    };

    // get the the winner details from Activity_Game
    private void getInfoFromDifferentActivity() {
        Intent intent = getIntent();
        winner = intent.getBooleanExtra(MySP.KEYS.WINNER, false);
        rounds = intent.getIntExtra(MySP.KEYS.ROUNDS, -1);
    }


    //load the background photo according to the winner
    private void loadWinnerImage() {
        if (winner){
            Glide
                    .with(this)
                    .load(R.drawable.presentwinner_alien)
                    .into(resultScreen_IMAGE_background);
        }
        else{
            Glide
                    .with(this)
                    .load(R.drawable.presentwinner_predator)
                    .into(resultScreen_IMAGE_background);
        }

    }

    private void findViewByIdAll(){
        resultScreen_IMAGE_background = findViewById(R.id.resultScreen_IMAGE_background);
        presentWinner_BTN_toHighScores = findViewById(R.id.presentWinner_BTN_toHighScores);
        presentWinner_BTN_toMainPage = findViewById(R.id.presentWinner_BTN_toMainPage);
        presentWinner_LBL_winnerInfo = findViewById(R.id.presentWinner_LBL_winnerInfo);
    }

    private void playWinnerSound() {
        mediaPlayer = MediaPlayer.create(Activity_PresentWinner.this, R.raw.sound_winner);
        mediaPlayer.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
    }
}