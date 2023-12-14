package com.example.myfirstapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
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
import android.hardware.SensorManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.MacAddress;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.Toast;

//import com.lcw.library.imagepicker.ImagePicker;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    private Context context;
    private ComponentName chuan;
    private String sex;
    private static final String TAG = "MainActivity";
    private TelephonyManager telephonyManager;
    private SensorManager sensorManager;

    //    private SubscriptionManager sm = SubscriptionManager.from(context);
    private SubscriptionInfo sb;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("AssertionSideEffect")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testcase
//        **Hide sensitive content from clipboard**
        ClipboardManager clipboardManager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        String text = "123456";
        ClipData clipData = ClipData.newPlainText("text", text);
//        设置隐藏属性
//        PersistableBundle extras = new PersistableBundle();
//        extras.putBoolean("android.content.extra.IS_SENSITIVE", true);
//        clipData.getDescription().setExtras(extras);

        clipboardManager.setPrimaryClip(clipData);
        Log.e("ClipboardUtil", "复制的文本是： " + text);

    }




    public void myclick(View view){
        userinfo = userID.getText().toString();
        userpwd = pwd.getText().toString();
        Toast.makeText(MainActivity.this, "user id is " + userinfo + " user password is " + userpwd + " sex is " + sex,Toast.LENGTH_SHORT).show();
    }
}
