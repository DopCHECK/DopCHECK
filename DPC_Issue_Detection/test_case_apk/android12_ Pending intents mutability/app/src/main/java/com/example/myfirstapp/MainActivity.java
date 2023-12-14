package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PICK_IMAGE = 101;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private EditText userID, pwd;
    private String userinfo, userpwd;
    private final int mCameraId = CameraCharacteristics.LENS_FACING_BACK;
    private static final int REQUEST_CODE_PERMISSION = 123;
    private CameraManager mCameraManager;
    private CameraCharacteristics mCameraCharacteristics;
    private RadioGroup radioGroup;
    private WifiManager wm;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private Button captureButton;
    private ImageView imageView;
    private String sex;
    private static final String TAG = "MainActivity";
    private TelephonyManager telephonyManager;
    private SensorManager sensorManager;

    //    private SubscriptionManager sm = SubscriptionManager.from(context);
    private SubscriptionInfo sb;

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;


    private TextView locationTextView;
    private LocationManager locationManager;
    private BiometricManager.Strings bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testcase
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);



        userID = findViewById(R.id.userID);
        pwd = findViewById(R.id.pwd);
        radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            //group就是radioGroup本身，checkedid接受的是用户选择的那个按钮id
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.nan) {
                    sex = "male";
                } else if (checkedId == R.id.nv) {
                    sex = "female";
                }
            }
        });

    }




    public void myclick(View view){
        userinfo = userID.getText().toString();
        userpwd = pwd.getText().toString();
        Toast.makeText(MainActivity.this, "user id is " + userinfo + " user password is " + userpwd + " sex is " + sex,Toast.LENGTH_SHORT).show();
    }
}
