package com.example.myfirstapp;

import com.bumptech.glide.Glide;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.MacAddress;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.Toast;

//import com.lcw.library.imagepicker.ImagePicker;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_SELECT_PHOTO = 1;
    private static final int PERMISSION_REQUEST_ACTIVITY_RECOGNITION = 1001;

    private EditText userID, pwd;
    private Button startButton;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private String userinfo, userpwd;
    private RadioGroup radioGroup;
    private String sex;
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;

    //    private SubscriptionManager sm = SubscriptionManager.from(context);
    private SubscriptionInfo sb;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @SuppressLint({"AssertionSideEffect", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userID = findViewById(R.id.userID);
        pwd = findViewById(R.id.pwd);
        radioGroup = findViewById(R.id.radiogroup);

        startButton = findViewById(R.id.startalarm);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, TimerReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            //group就是radioGroup本身，checkedid接受的是用户选择的那个按钮id
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.nan){
                    sex = "male";
                } else if (checkedId == R.id.nv){
                    sex ="female";
                }
            }
        });
    }
    private void startTimer() {
        long delayMillis = 500; // 五秒

        // 设置定时器的触发时间和延迟时间
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delayMillis, alarmIntent);
    }


    public void myclick(View view){
        userinfo = userID.getText().toString();
        userpwd = pwd.getText().toString();
        Toast.makeText(MainActivity.this, "user id is " + userinfo + " user password is " + userpwd + " sex is " + sex,Toast.LENGTH_SHORT).show();
    }
}
