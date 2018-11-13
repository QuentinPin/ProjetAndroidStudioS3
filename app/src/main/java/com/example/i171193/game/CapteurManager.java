package com.example.i171193.game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class CapteurManager implements SensorEventListener {

    private Game context;
    private SensorManager unSensorManager;
    private Sensor accelerometre;

    public CapteurManager(Game pContext) {
        this.context = pContext;
        unSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //Vérification si l'appareille possède l'accéleromettre
        if (unSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) {
            AlertDialog unAlertDialog = new AlertDialog.Builder(pContext).create();
            unAlertDialog.setTitle("Erreur");
            unAlertDialog.setMessage("Vous ne disposer pas du capteur nécessaire");
            unAlertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent unIntent = new Intent(Intent.ACTION_MAIN);
                    unIntent.addCategory(Intent.CATEGORY_HOME);
                    unIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(unIntent);
                    context.finish();
                }
            });
            unAlertDialog.show();
        }
        accelerometre = unSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //Calcule de la nouvelle position de l'avion
            float newX = context.avion.getX() + event.values[1];


            //Changer la postion de l'avion si il ne sort pas des borre
            if (newX > 0 && newX < context.largeurEcran-150)
                context.avion.setX(newX);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void Start() {
        unSensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void Stop() {
        unSensorManager.unregisterListener(this);
    }
}
