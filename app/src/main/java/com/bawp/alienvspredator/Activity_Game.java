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
hp bar - 200
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

public class Activity_Game extends AppCompatActivity {

    //P1 widgets
    private Button game_BTN_p1_lightAttack;
    private Button game_BTN_p1_strongAttack;
    private Button game_BTN_p1_brutalAttack;
    private ProgressBar game_PB_p1HP;
    private ImageView game_IMAGE_p1;

    //P2 widgets
    private Button game_BTN_p2_lightAttack;
    private Button game_BTN_p2_strongAttack;
    private Button game_BTN_p2_brutalAttack;
    private ImageView game_IMAGE_p2;
    private ProgressBar game_PB_p2HP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViewByIdAll();
        loadImages();

        //p1 attack first
        disableP2Buttons();

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
                enableP2Buttons();
                disableP1Buttons();
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
                enableP1Buttons();
                disableP2Buttons();
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
                .with(this)
                .load(R.drawable.game_alien_img)
                .into(game_IMAGE_p1);

        Glide
                .with(this)
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

        //images
        game_IMAGE_p1 = findViewById(R.id.game_IMAGE_p1);
        game_IMAGE_p2 = findViewById(R.id.game_IMAGE_p2);
    }
}