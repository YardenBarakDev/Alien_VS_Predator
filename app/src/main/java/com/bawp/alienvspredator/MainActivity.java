package com.bawp.alienvspredator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private Button main_BTN_alien_attack_1;
    private Button main_BTN_alien_attack_2;
    private Button main_BTN_alien_attack_3;
    private Button main_BTN_predator_attack_1;
    private Button main_BTN_predator_attack_2;
    private Button main_BTN_predator_attack_3;
    private ImageView main_IMAGE_alien;
    private ImageView main_IMAGE_predator;
    private ProgressBar main_PB_alienHP;
    private ProgressBar main_PB_predatorHP;

    private final int lightAttack = 10;
    private final int strongAttack = 20;
    private final int brutalAttack = 30;
    private final int MAX_HP = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIdAll();
        loadImages();


        main_BTN_alien_attack_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enablePredatorButtons();
                hitPredator(lightAttack);
            }
        });

        main_BTN_alien_attack_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enablePredatorButtons();
                hitPredator(strongAttack);
            }
        });

        main_BTN_alien_attack_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enablePredatorButtons();
                hitPredator(brutalAttack);
            }
        });

        main_BTN_predator_attack_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableAlienButtons();
                hitAlien(lightAttack);
            }
        });

        main_BTN_predator_attack_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableAlienButtons();
                hitAlien(strongAttack);
            }
        });

        main_BTN_predator_attack_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableAlienButtons();
                hitAlien(brutalAttack);
            }
        });
    }

    private void checkIfGameOver() {
        if (main_PB_predatorHP.getProgress() >= MAX_HP){
            disableAlienButtons();
            disablePredatorButtons();
            Toast.makeText(this, "Alien Win", Toast.LENGTH_LONG).show();
        }
        if (main_PB_alienHP .getProgress() >= MAX_HP){
            disableAlienButtons();
            disablePredatorButtons();
            Toast.makeText(this, "Predator Win", Toast.LENGTH_LONG).show();
        }
    }


    private void hitAlien(int attack) {
        main_PB_alienHP.setProgress(attack + main_PB_alienHP.getProgress());
        checkIfGameOver();
    }



    private void hitPredator(int attack) {
        main_PB_predatorHP.setProgress(attack + main_PB_predatorHP.getProgress());
        checkIfGameOver();
    }

    private void loadImages() {
        Glide
                .with(this)
                .load(R.drawable.alien2_img)
                .into(main_IMAGE_alien);

        Glide
                .with(this)
                .load(R.drawable.predator2_img)
                .into(main_IMAGE_predator);
    }

    private void disableAlienButtons(){
        main_BTN_alien_attack_1.setEnabled(false);
        main_BTN_alien_attack_2.setEnabled(false);
        main_BTN_alien_attack_3.setEnabled(false);
    }

    private void disablePredatorButtons(){
        main_BTN_predator_attack_1.setEnabled(false);
        main_BTN_predator_attack_2.setEnabled(false);
        main_BTN_predator_attack_3.setEnabled(false);
    }

    private void enableAlienButtons(){
        disablePredatorButtons();
        main_BTN_alien_attack_1.setEnabled(true);
        main_BTN_alien_attack_2.setEnabled(true);
        main_BTN_alien_attack_3.setEnabled(true);
    }

    private void enablePredatorButtons(){
        disableAlienButtons();
        main_BTN_predator_attack_1.setEnabled(true);
        main_BTN_predator_attack_2.setEnabled(true);
        main_BTN_predator_attack_3.setEnabled(true);
    }

    private void findViewByIdAll() {
        //alien buttons
        main_BTN_alien_attack_1 = findViewById(R.id.main_BTN_alien_attack_1);
        main_BTN_alien_attack_2 = findViewById(R.id.main_BTN_alien_attack_2);
        main_BTN_alien_attack_3 = findViewById(R.id.main_BTN_alien_attack_3);
        main_PB_alienHP = findViewById(R.id.main_PB_alienHP);

        //predator buttons
        main_BTN_predator_attack_1 = findViewById(R.id.main_BTN_predator_attack_1);
        main_BTN_predator_attack_2 = findViewById(R.id.main_BTN_predator_attack_2);
        main_BTN_predator_attack_3 = findViewById(R.id.main_BTN_predator_attack_3);
        main_PB_predatorHP = findViewById(R.id.main_PB_predatorHP);

        //images
        main_IMAGE_alien = findViewById(R.id.main_IMAGE_alien);
        main_IMAGE_predator = findViewById(R.id.main_IMAGE_predator);

    }
}