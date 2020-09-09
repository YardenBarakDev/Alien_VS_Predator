package com.bawp.alienvspredator;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Activity_PresentWinner extends AppCompatActivity {

    private ImageView resultScreen_IMAGE_background;
    private Button presentWinner_BTN_toHighScores;
    private Button presentWinner_BTN_toMainPage;
    private TextView presentWinner_LBL_winnerInfo;
    private boolean winner;
    private Score score;
    private int rounds;
    private LocationManager locationManager;
    private double longitude;
    private double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__presentwinner);
        findViewByIdAll();
        getCurrentLocation();

        presentWinner_BTN_toHighScores.setOnClickListener(presentWinnerButtonListener);
        presentWinner_BTN_toMainPage.setOnClickListener(presentWinnerButtonListener);
        getInfoFromDifferentActivity();
        loadWinnerImage();
        setWinnerText();
        createScore();
        checkAndSaveTopScores();
    }

    private void checkAndSaveTopScores() {
        TopTen topTen = new TopTen();
        topTen.getScoresFromSP();
        ArrayList<Score> tempArray =  topTen.getTopTenScores();

        if (tempArray.size() < topTen.getARRAY_MAX_SIZE()){
            tempArray.add(score);
            topTen.setTopTenScores(tempArray);
        }else {
            Score tempScore = score;
            for (int i = 0; i < topTen.getARRAY_MAX_SIZE(); i++) {
                if (tempScore.getNumOfMoves() > tempArray.get(i).getNumOfMoves()){
                    tempArray.set(i, score);
                    tempScore = tempArray.get(i);
                }
            }
        }
        topTen.setTopTenScores(tempArray);
        topTen.saveScoresInSP();
    }

    private void createScore() {
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        Log.d("datea", currentDateTimeString);
        score = new Score().setLat(latitude).
                setLon(longitude).
                setNumOfMoves(rounds).
                setTimeStamp(currentDateTimeString).
                setWinner(winner);

        Gson gson = new Gson();
        String json = gson.toJson(score);
    }

    private void getCurrentLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null){
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            String a = "lon " + location.getLongitude() + " lat" + location.getLongitude();
            Log.d("yarden", a);
        }
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
    private void getInfoFromDifferentActivity() {
        Intent intent = getIntent();
        winner = intent.getBooleanExtra(MySP.KEYS.WINNER, false);
        rounds = intent.getIntExtra(MySP.KEYS.ROUNDS, -1);
    }



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
}