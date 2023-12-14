package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PHONE_STATE_PERMISSION = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_CODE_PICK_IMAGE = 101;
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

    private Button recordButton;

    private Dialog systemDialog;


    @SuppressLint({"AssertionSideEffect", "MissingInflatedId", "MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testcase

        // 模拟一个系统对话框
        systemDialog = createSystemDialog();
        systemDialog.show();

        // 尝试在应用程序中关闭系统对话框
//        closeSystemDialog();
        sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

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


    private Dialog createSystemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("System Dialog")
                .setMessage("This is a system dialog.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮的操作
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消按钮的操作
                    }
                });
        return builder.create();
    }
    private void closeSystemDialog() {
        if (systemDialog != null && systemDialog.isShowing()) {
            systemDialog.dismiss();
        }
    }
    public void myclick(View view){
        userinfo = userID.getText().toString();
        userpwd = pwd.getText().toString();
        Toast.makeText(MainActivity.this, "user id is " + userinfo + " user password is " + userpwd + " sex is " + sex,Toast.LENGTH_SHORT).show();
    }
}
