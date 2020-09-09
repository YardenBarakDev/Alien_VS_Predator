package com.bawp.alienvspredator;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

public class AppMusic {

    private MediaPlayer mediaPlayer;
    private Boolean readyToPlay = false;
    private static AppMusic instance;

    public static AppMusic getInstance(){
        return instance;
    }

    private AppMusic(Context context){
        mediaPlayer = MediaPlayer.create(context,R.raw.audio_file);
        mediaPlayer.setLooping(true);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                readyToPlay = true;
            }
        });

    }

    public static AppMusic initHelper(Context context){
        if (instance == null)
            instance = new AppMusic(context);
        return instance;
    }


    public void play(){
        if (readyToPlay){
            mediaPlayer.start();
        }
    }

    public void pause(){
        mediaPlayer.pause();
    }

    public void stop(){
        mediaPlayer.stop();
        try {
            mediaPlayer.setDataSource(String.valueOf(R.raw.audio_file));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
