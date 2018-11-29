package com.example.i171193.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button jouer;
    private Button regle;
    private Button information;
    private DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int largeurEcran = metrics.widthPixels;
        int hauteurEcran = metrics.heightPixels;
        if (largeurEcran<hauteurEcran){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Cette application n'est pas disponible pour tablette");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
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

    @Override
    public void onBackPressed() {
        //bloquage du bouton retour
    }

}
