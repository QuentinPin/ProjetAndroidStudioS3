package com.example.i171193.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private CapteurManager unCapteurManager;
    protected RelativeLayout fenetrePrincipal;
    protected ImageView avion;
    private DisplayMetrics metrics;
    protected float largeurEcran;
    protected float hauteurEcran;
    private GestureDetectorCompat unGestureDetector;
    private Timer timerMissile;
    private TimerTask timerTask;
    protected List<Missile> listMissile;
    protected List<Bloc> listBloc;
    private int temps;
    protected static int score = 0;
    protected int timeBloc;
    protected int compteurTimeBloc;
    public static TextView affScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listMissile = new ArrayList<Missile>();
        listBloc = new ArrayList<Bloc>();
        timerMissile = new Timer();
        timeBloc = 200;
        compteurTimeBloc = 1;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        temps++;
                        for (int i = 0; i < listMissile.size(); i++) {
                            listMissile.get(i).bouger();
                        }

                        if (temps == timeBloc) { //A l'initialisation toutes les 2 seconde
                            gererLesBloc();
                            temps = 0;
                            for (Bloc unBloc : listBloc) {
                                unBloc.bouger();
                                gameOver(unBloc); //Est-ce que le bloc est en bah e l'écran
                            }
                        }
                    }

                });

            }
        };
        timerMissile.schedule(timerTask, 0, 10);
        this.startGame();
    }

    @Override
    public void onResume() {
        super.onResume();
        unCapteurManager.Start();
    }

    @Override
    public void onPause() {
        super.onPause();
        unCapteurManager.Stop();
    }

    public void startGame() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fenetrePrincipal = (RelativeLayout) findViewById(R.id.fenetrePrincipal);
        unCapteurManager = new CapteurManager(this);
        //Création de l'image avec la bonne ressource
        avion = new ImageView(this);
        avion.setBackgroundResource(R.drawable.avion);
        //Redimentionnement de l'image
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 150);
        avion.setLayoutParams(params);
        //Rotation de l'avion pour avoir le né vers le haut
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        largeurEcran = metrics.widthPixels;
        hauteurEcran = metrics.heightPixels;
        //Positionement de l'avion
        avion.setX((largeurEcran / 2) - 75);
        avion.setY(hauteurEcran - 150);
        //Ajouter l'image a la fentre principale
        fenetrePrincipal.addView(avion);
        unGestureDetector = new GestureDetectorCompat(this, this);
        score = 0;
        affScore = (TextView) findViewById(R.id.affScore);
        affScore.setText("Score = 0");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        unGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Missile unMissile = new Missile(this, this.avion.getX(), new ImageView(this));
        listMissile.add(unMissile);
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public void gererLesBloc() {
        Bloc unBloc = new Bloc(this);
        listBloc.add(unBloc);
    }


    private void gameOver(Bloc unBloc) {
        if (unBloc.getImageBloc().getY() > this.hauteurEcran){
            Intent intent = new Intent(this, GameOver.class);
            startActivity(intent);
            timerMissile.cancel();
        }
    }

    @Override
    protected void onStop(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        String bestScore = preferences.getString("MeilleurScore","");
        if( bestScore.equalsIgnoreCase("" )|| Integer.parseInt(bestScore) < Game.score ){
            editor.putString("MeilleurScore", "" + Game.score);
        }
        editor.apply();
        super.onStop();
    }
}
