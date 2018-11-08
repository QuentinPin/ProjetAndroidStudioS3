package com.example.i171193.game;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Bloc {

    private MainActivity context;
    private ImageView imageBloc;

    public Bloc(MainActivity pContexte){
        this.context = pContexte;
        this.imageBloc = new ImageView(context);
        this.genereBloc();
    }

    public void genereBloc(){
        imageBloc.setBackgroundResource(R.drawable.bloc);
        //Redimentionnement de l'image
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(150, 50);
        imageBloc.setLayoutParams(params);
        imageBloc.setX(this.positionAleatoire()); //Missile par au centre de l'avion
        imageBloc.setY(50); //Missile par devant l'avion
        context.fenetrePrincipal.addView(imageBloc); //Ajout du bloc dans la fenetre principale
    }

    private float positionAleatoire() {
        float X = (float) (Math.random() * context.largeurEcran);
        if (X > context.largeurEcran-150){
            X -= 150;
        }
        return X;
    }

    public ImageView getImageBloc(){
        return this.imageBloc;
    }

    public void bouger() {
        if (this.context.score >= 100*this.context.compteurTimeBloc) { //Faire apparaitre les cube plus vite au file du jeux
            this.context.compteurTimeBloc+=1;
            if (this.context.timeBloc > 60) {
                this.context.timeBloc -= 7; //Les bloc décende plus vite pour augmenter la difficulté
            }
        }
        imageBloc.setY(this.getImageBloc().getY() + 50);
    }
}
