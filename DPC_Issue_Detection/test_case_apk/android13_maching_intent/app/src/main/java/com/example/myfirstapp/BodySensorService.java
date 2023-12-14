package com.example.myfirstapp;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

public class BodySensorService extends Service implements SensorEventListener {

    private SensorManager sensorManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // 获取传感器管理器实例
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // 注册身体传感器监听器
        Sensor bodySensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        sensorManager.registerListener(this, bodySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 执行后台任务

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // 停止传感器监听
        sensorManager.unregisterListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            // 处理身体传感器数据
            float heartRate = event.values[0];
            // ...
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 传感器精度变化时的回调
    }
}
