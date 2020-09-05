package com.bawp.alienvspredator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class Activity_PresentWinner extends AppCompatActivity {

    private ImageView resultScreen_IMAGE_background;
    private Button presentWinner_BTN_toHighScores;
    private Button presentWinner_BTN_toMainPage;
    private boolean winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__presentwinner);

        findViewByIdAll();
        presentWinner_BTN_toHighScores.setOnClickListener(attackButtonListener);
        presentWinner_BTN_toMainPage.setOnClickListener(attackButtonListener);

        getInfoFromDifferentActivity();
        loadWinnerImage();
    }
    private View.OnClickListener attackButtonListener = new View.OnClickListener() {
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
        winner = intent.getBooleanExtra(MySP.KEYS.WINNER, winner);
    }

    private void loadWinnerImage() {
        if (winner){
            Glide
                    .with(this)
                    .load(R.drawable.presentwinner_predator)
                    .into(resultScreen_IMAGE_background);
        }
        else{
            Glide
                    .with(this)
                    .load(R.drawable.presentwinner_alien)
                    .into(resultScreen_IMAGE_background);
        }

    }
    private void findViewByIdAll(){
        resultScreen_IMAGE_background = findViewById(R.id.resultScreen_IMAGE_background);
        presentWinner_BTN_toHighScores = findViewById(R.id.presentWinner_BTN_toHighScores);
        presentWinner_BTN_toMainPage = findViewById(R.id.presentWinner_BTN_toMainPage);
    }
}