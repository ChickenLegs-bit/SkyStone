package org.firstinspires.ftc.teamcode.listeners;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MyAndroidSensorListener implements SensorEventListener {
    private final SensorManager sensorManager;
    private final Sensor accelorometer;
    private final Telemetry telemetry;

    //new My AndroidListener(args...)
    public MyAndroidSensorListener(SensorManager sensorManager, Telemetry telemetry){
        this.sensorManager = sensorManager;
        this.telemetry = telemetry;

        this.accelorometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void onResume(){
        sensorManager.registerListener(this,accelorometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float motionX = sensorEvent.values[0];
        float motionY = sensorEvent.values[1];
        float motionZ = sensorEvent.values[2];

        telemetry.addData("X velocity", motionX);
        telemetry.addData("Y velocity", motionY);
        telemetry.addData("Z velocity", motionZ);
        telemetry.update();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
