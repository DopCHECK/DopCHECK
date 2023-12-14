package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.ConnectivityManager;
import android.net.MacAddress;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
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

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(MainActivity.this,"您申请了动态权限",Toast.LENGTH_SHORT).show();
        }else{
            //否则去请求相机权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
        }
        WifiConfiguration wifiConfig = new WifiConfiguration();
        // Add the network
        int netId = wifiManager.addNetwork(wifiConfig);
        int upnet = wifiManager.updateNetwork(wifiConfig);
        Log.e("testapp", "addNetwork" + String.valueOf(netId));
        Log.e("testapp", "updateNetwork" +String.valueOf(upnet));
        boolean rn = wifiManager.removeNetwork(netId);
        Log.e("testapp", "removeNetwork" +String.valueOf(rn));
        // Reconnect to the network
        boolean rc = wifiManager.reconnect();
        Log.e("testapp", "reconnect" +String.valueOf(rc));
// Disconnect from the network
        boolean dc = wifiManager.disconnect();
        Log.e("testapp", "disconnect" +String.valueOf(dc));
// Reassociate with the network
        boolean rec = wifiManager.reassociate();
        Log.e("testapp", "reassociate" +String.valueOf(rec));


        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
            // Iterate through the list and access network information
            if (configuredNetworks == null) {
                Log.e("testapp", "getConfiguredNetworks return null");
            }else {
                Log.e("testapp", configuredNetworks.toString());
            }
        }
    }


