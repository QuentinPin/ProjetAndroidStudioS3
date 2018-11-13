package com.example.i171193.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    private Button rejouer;
    private Button menu;
    private TextView scoreJoueur;
    private TextView meilleurScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        this.getMeilleurScore();
        scoreJoueur = (TextView) findViewById(R.id.affScore);
        scoreJoueur.setText("Score : " + MainActivity.score);
        meilleurScore = (TextView) findViewById(R.id.meilleurScore);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int bestScore = preferences.getInt("MeilleurScore",0);
        meilleurScore.setText("Meilleur score : " + bestScore);
        rejouer = (Button) findViewById(R.id.rejouer);
        rejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejouer();
            }
        });
        menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
            }
        });
    }

    public void rejouer(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void menu(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    public void getMeilleurScore(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int bestScore = preferences.getInt("MeilleurScore",0);
        if(bestScore < MainActivity.score){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("MeilleurScore",MainActivity.score);
        }
    }
}
