package com.example.i171193.game;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Missile {

    private Game context;
    private float X;
    private ImageView imageMissile;
    private SoundPool mySound = null;

    public Missile(Game pContexte, float pX, ImageView pImageMissile) {
        this.context = pContexte;
        this.X = pX;
        imageMissile = pImageMissile;
        imageMissile.setBackgroundResource(R.drawable.missile2);
        //Redimentionnement de l'image
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(15, 50);
        imageMissile.setLayoutParams(params);
        imageMissile.setX(pX + 40); //Missile par au centre de l'avion
        imageMissile.setY(context.hauteurEcran - 150); //Missile par devant l'avion
        context.fenetrePrincipal.addView(imageMissile);
    }

    public void bouger() {
        imageMissile.setY(imageMissile.getY() - 7);
        if (imageMissile.getY() < 0) {//Si le missille sort de l'écran
            context.listMissile.remove(this);
            context.fenetrePrincipal.removeView(imageMissile);
            context.score -= 5;
            context.affScore.setText("Score = " + context.score);
        }
        for (int i = 0; i < context.listBloc.size(); i++) {
            if (context.listBloc.get(i).getImageBloc().getX() <= imageMissile.getX() && (context.listBloc.get(i).getImageBloc().getX() + 150) >= imageMissile.getX()) {
                if (context.listBloc.get(i).getImageBloc().getY() <= imageMissile.getY() && (context.listBloc.get(i).getImageBloc().getY() + 50) >= imageMissile.getY()){
                    context.fenetrePrincipal.removeView(context.listBloc.get(i).getImageBloc()); //Supression de la vue du bloc
                    context.fenetrePrincipal.removeView(this.imageMissile); //Supression de la vue du missille
                    context.listBloc.remove(context.listBloc.get(i)); //On enlève le bloc de la liste
                    context.listMissile.remove(this); //On enlève les missile de la liste e missille
                    context.score += 10;
                    context.affScore.setText("Score = " + context.score);
                }
            }
        }
    }
}
