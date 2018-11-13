package com.example.i171193.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button jouer;
    private Button regle;
    private Button information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        jouer = (Button) findViewById(R.id.jouer);
        jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jouer();
            }
        });
        regle = (Button)findViewById(R.id.regle);
        regle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regle();
            }
        });
        information = (Button) findViewById(R.id.information);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                information();
            }
        });
    }

    public void jouer(){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    public void regle(){
        Intent intent = new Intent(this, Regle.class);
        startActivity(intent);
    }

    public void information(){
        Intent intent = new Intent(this, Information.class);
        startActivity(intent);
    }
}
