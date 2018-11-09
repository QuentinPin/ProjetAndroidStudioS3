package com.example.i171193.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button jouer;

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
    }

    public void jouer(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
