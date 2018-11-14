package com.example.i171193.game;

import android.content.Context;
import android.media.MediaPlayer;

public class SonMissile {

    private MediaPlayer mPlayer = null;

    public SonMissile(){
        super();

    }

    public void playSound(Context context) {
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = MediaPlayer.create(context, R.raw.laser);
        mPlayer.start();
    }

    public void stopSound(){
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }
}
