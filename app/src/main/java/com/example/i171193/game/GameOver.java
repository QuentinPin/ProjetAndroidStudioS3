package com.example.i171193.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    private Button rejouer;
    private Button menu;
    private TextView scoreJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        scoreJoueur = (TextView) findViewById(R.id.affScore);
        scoreJoueur.setText("Score : " + MainActivity.score);
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
}
