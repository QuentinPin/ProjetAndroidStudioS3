package com.example.i171193.game;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class MusicManager {

    private Context context;
    private SoundPool soundPool;
    private int sonMissille;

    public MusicManager(Context context){
        super();
        this.context = context;
        this.soundPool = new SoundPool(50, AudioManager.STREAM_MUSIC, 0);
        this.sonMissille = soundPool.load(context, R.raw.laser,1);
    }

    public void jouerSonMissille(){
        soundPool.play(this.sonMissille, 1, 1, 1,0,1);
    }
}
