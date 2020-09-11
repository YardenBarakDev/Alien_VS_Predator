package com.bawp.alienvspredator;

/*
p1-Alien
p2-Predator
***************
p1 always start first
***************
turn = true => p1 turn
attack1- light attack 10hp
attack2- strong attack 20hp
attack3- brutal attack 30hp
hp bar - 200
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.util.Random;

public class Activity_Game extends AppCompatActivity {

    //P1 widgets
    private Button game_BTN_p1_lightAttack;
    private Button game_BTN_p1_strongAttack;
    private Button game_BTN_p1_brutalAttack;
    private ProgressBar game_PB_p1HP;
    private ImageView game_IMAGE_p1;
    private ImageView game_IMAGE_p1_dice;

    //P2 widgets
    private Button game_BTN_p2_lightAttack;
    private Button game_BTN_p2_strongAttack;
    private Button game_BTN_p2_brutalAttack;
    private ProgressBar game_PB_p2HP;
    private ImageView game_IMAGE_p2;
    private ImageView game_IMAGE_p2_dice;
    private Button game_BTN_rollDices;

    private TextView game_LBL_attackInfo;
    //Game variables
    private boolean turn;
    private boolean diceRollFinish = false;
    private Handler handlerGame = new Handler();
    private Handler handlerDices = new Handler();
    private final int DELAY = 1000;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViewByIdAll();
        loadImages();
        
        //p1 attack first
        //disableP2Buttons();
        disableAllButtons();

        game_BTN_rollDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerDices.postDelayed(runnableDice,DELAY);
                game_BTN_rollDices.setEnabled(false);
            }
        });

        //P1 attack buttons
        game_BTN_p1_lightAttack.setOnClickListener(attackButtonsListener);
        game_BTN_p1_strongAttack.setOnClickListener(attackButtonsListener);
        game_BTN_p1_brutalAttack.setOnClickListener(attackButtonsListener);

        //P2 attack buttons
        game_BTN_p2_lightAttack.setOnClickListener(attackButtonsListener);
        game_BTN_p2_strongAttack.setOnClickListener(attackButtonsListener);
        game_BTN_p2_brutalAttack.setOnClickListener(attackButtonsListener);
    }

    private View.OnClickListener attackButtonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            attackButtonClicked(view);
        }

        /*
        monitor which button was clicked and perform the necessary code
        by checking the buttons tag
         */
        private void attackButtonClicked(View view) {
            //P1 turn
            if (((String) view.getTag()).contains("p1")){
                //enableP2Buttons();
                //disableP1Buttons();
                switch (((String) view.getTag())) {
                    case "game_BTN_lightAttack_p1":
                        hitP2(GameVariables.LIGHT_ATTACK);
                        break;
                    case "game_BTN_StrongAttack_p1":
                        hitP2(GameVariables.STRONG_ATTACK);
                        break;
                    case "game_BTN_BrutalAttack_p1":
                        hitP2(GameVariables.BRUTAL_ATTACK);
                        break;
                }
            }
            //P2 turn
            else{
                //enableP1Buttons();
                //disableP2Buttons();
                switch (((String) view.getTag())) {
                    case "game_BTN_lightAttack_p2":
                        hitP1(GameVariables.LIGHT_ATTACK);
                        break;
                    case "game_BTN_StrongAttack_p2":
                        hitP1(GameVariables.STRONG_ATTACK);
                        break;
                    case "game_BTN_BrutalAttack_p2":
                        hitP1(GameVariables.BRUTAL_ATTACK);
                        break;
                }
            }
        }
    };

    //make the dice animation
    Runnable runnableDice = new Runnable() {
        final Random random = new Random();
        final int[] dices = {0, 0, 0};// [0] ->p1, [1]->p2, [2]->who roll the dice

        @Override
        public void run() {
            //get random number
            int number = random.nextInt(6) + 1;
            int resource = getResources().getIdentifier("game_dice"+number,
                    "drawable",
                    "com.bawp.alienvspredator");
            if (dices[2]%2 == 0){
                dices[0] = resource;
                game_IMAGE_p1_dice.setImageResource(dices[0]);
            }
            else{
                dices[1] = resource;
                game_IMAGE_p2_dice.setImageResource(dices[1]);
            }

            //create dice roll sound
            mediaPlayer =MediaPlayer.create(Activity_Game.this, R.raw.roll_dice);
            mediaPlayer.start();
            //if both dices have the same value or p2 haven't roll
            //it will make sure the animation will continue
            dices[2]++;
            if (dices[0] == dices[1] || dices[1] == 0) {
                if (dices[0] == dices[1]){
                    dices[0] = 0;
                    dices[1] = 0;
                }
                handlerDices.postDelayed(this, DELAY);
            }
            //it means the animation ended and one of the sides has a larger value in the dice
            //according to this whoever won the dice roll will start first
            else{
                turn = dices[0] > dices[1];
                diceRollFinish = true;
                handlerGame.postDelayed(runnableGame, DELAY);
            }
        }
    };

    //run the game it will stop only once of the sides lose
    Runnable runnableGame = new Runnable() {
        final int[] count = {1, 1};
        final int delay = 1000;
        final Random random = new Random();
        @Override
        public void run() {
            //get random number between 1-3 that will choose the relevant attack cording to the number
            //if turn == true -> p1 turn
            int number = random.nextInt(3) + 1;
            if(turn){
                switch(number){
                    case 1:
                        game_BTN_p1_lightAttack.performClick();
                        break;
                    case 2:
                        game_BTN_p1_strongAttack.performClick();
                        break;
                    case 3:
                        game_BTN_p1_brutalAttack.performClick();
                        break;
                }
                //change turn
                turn = false;
                //count how many attacks p1 did
                count[0]++;
                //make attack sound. each attack has a different sound
            }
            //p2 -> turn
            else{
                switch(number){
                    case 1:
                        game_BTN_p2_lightAttack.performClick();
                        break;
                    case 2:
                        game_BTN_p2_strongAttack.performClick();
                        break;
                    case 3:
                        game_BTN_p2_brutalAttack.performClick();
                        break;
                }
                //change turn
                turn = true;
                //count how many attacks p2 did
                count[1]++;
                //make attack sound. each attack has a different sound
            }
            gameSound(number);
            // continue until the health bar is empty
            if(game_PB_p1HP.getProgress() < GameVariables.MAX_HP && game_PB_p2HP.getProgress() < GameVariables.MAX_HP){
                handlerGame.postDelayed(this, delay);
                //update the game_LBL_attackInfo
                attackInfo(number, !turn);

            }
            else{
                if (turn)
                saveDataAndChangeActivity(count[0]);
                else
                    saveDataAndChangeActivity(count[1]);
            }
        }
    };

    //move the result to Activity_PresentWinner
    private void saveDataAndChangeActivity(int count) {
        Intent intent = new Intent(Activity_Game.this, Activity_PresentWinner.class);
        intent.putExtra(MySP.KEYS.WINNER, !turn);
        intent.putExtra(MySP.KEYS.ROUNDS, count);
        startActivity(intent);
        finish();
    }

    private void announceWinner(String winner) {
        disableAllButtons();
        Toast.makeText(this, winner, Toast.LENGTH_LONG).show();
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
        game_PB_p1HP.setProgress(attack + game_PB_p1HP.getProgress());
        if (game_PB_p1HP.getProgress() > GameVariables.CRITICAL_HP)
            game_PB_p1HP.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.game_progressbar_low_hp));
        if (game_PB_p1HP.getProgress() >= GameVariables.MAX_HP)
            announceWinner(GameVariables.P2_WIN);

    }

    private void hitP2(int attack) {
        game_PB_p2HP.setProgress(attack + game_PB_p2HP.getProgress());
        if (game_PB_p2HP.getProgress() > GameVariables.CRITICAL_HP)
            game_PB_p2HP.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.game_progressbar_low_hp));
        if (game_PB_p2HP.getProgress() >= GameVariables.MAX_HP)
            announceWinner(GameVariables.P1_WIN);
    }

    //load images from drawable using Glide
    private void loadImages() {
        Glide
                .with(Activity_Game.this)
                .load(R.drawable.game_alien_img)
                .into(game_IMAGE_p1);

        Glide
                .with(Activity_Game.this)
                .load(R.drawable.game_predator_img)
                .into(game_IMAGE_p2);
    }

    //disable all of p1's buttons
    private void disableP1Buttons(){
        game_BTN_p1_lightAttack.setEnabled(false);
        game_BTN_p1_strongAttack.setEnabled(false);
        game_BTN_p1_brutalAttack.setEnabled(false);
    }

    //disable all of p2's buttons
    private void disableP2Buttons(){
        game_BTN_p2_lightAttack.setEnabled(false);
        game_BTN_p2_strongAttack.setEnabled(false);
        game_BTN_p2_brutalAttack.setEnabled(false);
    }



    private void findViewByIdAll() {
        //p1 widgets
        game_BTN_p1_lightAttack = findViewById(R.id.game_BTN_p1_lightAttack);
        game_BTN_p1_strongAttack = findViewById(R.id.game_BTN_p1_strongAttack);
        game_BTN_p1_brutalAttack = findViewById(R.id.game_BTN_p1_brutalAttack);
        game_PB_p1HP = findViewById(R.id.game_PB_p1HP);

        //p2 widgets
        game_BTN_p2_lightAttack = findViewById(R.id.game_BTN_p2_lightAttack);
        game_BTN_p2_strongAttack = findViewById(R.id.game_BTN_p2_strongAttack);
        game_BTN_p2_brutalAttack = findViewById(R.id.game_BTN_p2_brutalAttack);
        game_PB_p2HP = findViewById(R.id.game_PB_p2HP);


        game_BTN_rollDices = findViewById(R.id.game_BTN_rollDices);
        game_LBL_attackInfo = findViewById(R.id.game_LBL_attackInfo);

        //images
        game_IMAGE_p1 = findViewById(R.id.game_IMAGE_p1);
        game_IMAGE_p2 = findViewById(R.id.game_IMAGE_p2);
        game_IMAGE_p1_dice = findViewById(R.id.game_IMAGE_p1_dice);
        game_IMAGE_p2_dice = findViewById(R.id.game_IMAGE_p2_dice);

    }

    //change the game_LBL_attackInfo according to who is attacking and which attack
    private void attackInfo(int attack, boolean player){
        Log.d("aaaaa", "attackInfo: " + player);
        String attacker;
        if (player)
            attacker = "Alien";
        else
            attacker = "Predator";

        switch (attack){
            case 1:
                attacker = attacker + " Light attack";
                game_LBL_attackInfo.setText(attacker);
                break;
            case 2:
                attacker = attacker + " Strong attack";
                game_LBL_attackInfo.setText(attacker);
                break;
            case 3:
                attacker = attacker + " Brutal attack";
                game_LBL_attackInfo.setText(attacker);
                break;
        }
        mediaPlayer.start();
    }

    //set the relevant mp3 file according to the attack and play it
    private void gameSound(int attack){
        switch (attack){
            case 1:
                mediaPlayer = MediaPlayer.create(Activity_Game.this, R.raw.sound_lightattack);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(Activity_Game.this, R.raw.sound_strongattack);
                break;
            case 3:
                mediaPlayer = MediaPlayer.create(Activity_Game.this, R.raw.sound_brutalattack);
                break;
        }
        mediaPlayer.start();
    }
        /*
    used in V1

    //enable p1 buttons
    private void enableP1Buttons(){
        game_BTN_p1_lightAttack.setEnabled(true);
        game_BTN_p1_strongAttack.setEnabled(true);
        game_BTN_p1_brutalAttack.setEnabled(true);
    }
    //enable p2 buttons
    private void enableP2Buttons(){
        game_BTN_p2_lightAttack.setEnabled(true);
        game_BTN_p2_strongAttack.setEnabled(true);
        game_BTN_p2_brutalAttack.setEnabled(true);
    }


     */


    //check if the roll of the dices part in the game finished before it start the game.
    @Override
    protected void onStart() {
        super.onStart();
        if (diceRollFinish){
            handlerGame.postDelayed(runnableGame, DELAY);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!diceRollFinish)
            handlerDices.removeCallbacks(runnableDice);
        else
            handlerGame.removeCallbacks(runnableGame);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}