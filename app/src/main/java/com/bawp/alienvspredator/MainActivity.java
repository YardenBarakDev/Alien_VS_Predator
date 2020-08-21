package com.bawp.alienvspredator;

/*
p1-Alien
p2-Predator
***************
p1 always start first
***************
attack1- light attack 10hp
attack2- strong attack 20hp
attack3- brutal attack 30hp
hp bar - 250

 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    //P1 widgets
    private Button main_BTN_p1_attack1;
    private Button main_BTN_p1_attack2;
    private Button main_BTN_p1_attack3;
    private ProgressBar main_PB_p1HP;
    private ImageView main_IMAGE_p1;

    //P2 widgets
    private Button main_BTN_p2_attack1;
    private Button main_BTN_p2_attack2;
    private Button main_BTN_p2_attack3;
    private ImageView main_IMAGE_p2;
    private ProgressBar main_PB_p2HP;

    //Game variables
    private final int LIGHT_ATTACK = 10;
    private final int STRONG_ATTACK = 20;
    private final int BRUTAL_ATTACK = 30;
    private final int MAX_HP = 250;
    private final int CRITICAL_HP = 200;
    private final String P1_WIN = "Alien Win";
    private final String P2_WIN = "Predator Win";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIdAll();
        loadImages();
        disableP2Buttons();

        main_BTN_p1_attack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableP2Buttons();
                disableP1Buttons();
                hitP2(LIGHT_ATTACK);
            }
        });

        main_BTN_p1_attack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableP2Buttons();
                disableP1Buttons();
                hitP2(STRONG_ATTACK);
            }
        });

        main_BTN_p1_attack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableP2Buttons();
                disableP1Buttons();
                hitP2(BRUTAL_ATTACK);
            }
        });

        main_BTN_p2_attack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableP1Buttons();
                disableP2Buttons();
                hitP1(LIGHT_ATTACK);
            }
        });

        main_BTN_p2_attack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableP1Buttons();
                disableP2Buttons();
                hitP1(STRONG_ATTACK);
            }
        });

        main_BTN_p2_attack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableP1Buttons();
                disableP2Buttons();
                hitP1(BRUTAL_ATTACK);
            }
        });
    }

    private void p1Win() {
        disableAllButtons();
        Toast.makeText(this, P1_WIN, Toast.LENGTH_LONG).show();
    }

    private void p2Win() {
        disableAllButtons();
        Toast.makeText(this, P2_WIN, Toast.LENGTH_LONG).show();
    }

    private void disableAllButtons() {
        disableP1Buttons();
        disableP2Buttons();
    }

    /*
    hitP1 and hitP2
    add the hit amount to the progress bar
    if the progress bar progress is > CRITICAL_HP it will change the progress bar background to red
     if the progress bar progress is >= MAX_HP the game will end
     */
    private void hitP1(int attack) {
        main_PB_p1HP.setProgress(attack + main_PB_p1HP.getProgress());
        if (main_PB_p1HP.getProgress() > CRITICAL_HP)
            main_PB_p1HP.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.main_progressbar_low_hp));
        if (main_PB_p1HP.getProgress() >= MAX_HP)
            p2Win();

    }

    private void hitP2(int attack) {
        main_PB_p2HP.setProgress(attack + main_PB_p2HP.getProgress());
        if (main_PB_p2HP.getProgress() > CRITICAL_HP)
            main_PB_p2HP.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.main_progressbar_low_hp));
        if (main_PB_p2HP.getProgress() >= MAX_HP)
            p1Win();
    }

    //load images from drawable using Glide
    private void loadImages() {
        Glide
                .with(this)
                .load(R.drawable.main_alien_img)
                .into(main_IMAGE_p1);

        Glide
                .with(this)
                .load(R.drawable.main_predator_img)
                .into(main_IMAGE_p2);
    }

    //disable all of p1's buttons
    private void disableP1Buttons(){
        main_BTN_p1_attack1.setEnabled(false);
        main_BTN_p1_attack2.setEnabled(false);
        main_BTN_p1_attack3.setEnabled(false);
    }

    //disable all of p2's buttons
    private void disableP2Buttons(){
        main_BTN_p2_attack1.setEnabled(false);
        main_BTN_p2_attack2.setEnabled(false);
        main_BTN_p2_attack3.setEnabled(false);
    }

    //enable p1 buttons
    private void enableP1Buttons(){
        main_BTN_p1_attack1.setEnabled(true);
        main_BTN_p1_attack2.setEnabled(true);
        main_BTN_p1_attack3.setEnabled(true);
    }
    //enable p2 buttons
    private void enableP2Buttons(){
        main_BTN_p2_attack1.setEnabled(true);
        main_BTN_p2_attack2.setEnabled(true);
        main_BTN_p2_attack3.setEnabled(true);
    }

    private void findViewByIdAll() {
        //p1 widgets
        main_BTN_p1_attack1 = findViewById(R.id.main_BTN_p1_attack1);
        main_BTN_p1_attack2 = findViewById(R.id.main_BTN_p1_attack2);
        main_BTN_p1_attack3 = findViewById(R.id.main_BTN_p1_attack3);
        main_PB_p1HP = findViewById(R.id.main_PB_p1HP);

        //p2 widgets
        main_BTN_p2_attack1 = findViewById(R.id.main_BTN_p2_attack1);
        main_BTN_p2_attack2 = findViewById(R.id.main_BTN_p2_attack2);
        main_BTN_p2_attack3 = findViewById(R.id.main_BTN_p2_attack3);
        main_PB_p2HP = findViewById(R.id.main_PB_p2HP);

        //images
        main_IMAGE_p1 = findViewById(R.id.main_IMAGE_p1);
        main_IMAGE_p2 = findViewById(R.id.main_IMAGE_p2);
    }
}