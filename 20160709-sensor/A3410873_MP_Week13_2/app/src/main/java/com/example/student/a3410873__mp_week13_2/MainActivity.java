package com.example.student.a3410873__mp_week13_2;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements
        SensorEventListener{
        SensorManager sm;
        Sensor sr;
        TextView txv;
        ImageView imageView;
        RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        sr=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        txv=(TextView)findViewById(R.id.txv);
        imageView=(ImageView)findViewById(R.id.imgMove);
        layout=(RelativeLayout)findViewById(R.id.layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this,sr,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        txv.setText(String.format(" X軸:%1.2f,Y軸:%1.2f,Z軸:%1.2f",
                event.values[0],ev));
    }
}
