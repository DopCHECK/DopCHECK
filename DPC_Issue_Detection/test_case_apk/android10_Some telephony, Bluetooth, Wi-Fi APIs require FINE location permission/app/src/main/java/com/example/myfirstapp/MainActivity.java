package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.NetworkScanRequest;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.TelephonyScanManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingPermission")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
//        }else{
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
//        }

        try {
            CellLocation cellLocation = telephonyManager.getCellLocation();
        } catch (Exception e) {
            Log.e("testapp", "getCellLocation" + e.getClass().toString());
        }
        try {
            CellLocation cellLocation = (CellLocation) telephonyManager.getAllCellInfo();
        } catch (Exception e) {
            Log.e("testapp", "getAllCellInfo" + e.getClass().toString());
        }
//        try {
//            CellLocation cellLocation  = (CellLocation) telephonyManager.requestNetworkScan();
//        }catch (Exception e) {
//            Log.e("testapp", "getAllCellInfo" + e.getClass().toString());
//        }
//        try {
//            CellLocation cellLocation  = (CellLocation) telephonyManager.requestCellInfoUpdate();
//        }catch (Exception e) {
//            Log.e("testapp", "getAllCellInfo" + e.getClass().toString());
//        }
//        try {
//            CellLocation cellLocation  = telephonyManager.getAvailableNetworks();
//        }catch (Exception e) {
//            Log.e("testapp", "getAllCellInfo" + e.getClass().toString());
//        }
        try {
            ServiceState cellLocation = telephonyManager.getServiceState();
            Log.e("testapp", cellLocation.toString());
        } catch (Exception e) {
            Log.e("testapp", "getServiceState" + e.getClass().toString());
        }
//        if (telephonyManager != null) {
//            telephonyManager.requestCellInfoUpdate(Executors.newSingleThreadExecutor(), new TelephonyManager.CellInfoCallback() {
//                @Override
//                public void onCellInfo(List<CellInfo> cellInfoList) {
//                    for (CellInfo cellInfo : cellInfoList) {
//                        if (cellInfo instanceof CellInfoGsm) {
//                            CellSignalStrengthGsm cellSignalStrengthGsm = ((CellInfoGsm) cellInfo).getCellSignalStrength();
//                            CellIdentityGsm cellIdentityGsm = ((CellInfoGsm) cellInfo).getCellIdentity();
//                            // Do something with the cell signal strength and cell identity
//                        }
//                        // Similarly handle for CellInfoCdma, CellInfoLte, CellInfoWcdma, CellInfoTdscdma and CellInfoNr
//                    }
//                }
//            });
//        }

        NetworkScanRequest networkScanRequest = new NetworkScanRequest(NetworkScanRequest.SCAN_TYPE_ONE_SHOT, null, 11, 11, false, 1, null);

        Executor executor = Executors.newFixedThreadPool(1);
        TelephonyScanManager.NetworkScanCallback networkScanCallback = new TelephonyScanManager.NetworkScanCallback() {
            @Override
            public void onResults(List<CellInfo> results) {
                // Handle network scan results
            }

            @Override
            public void onError(int errorCode) {
                // Handle network scan error
            }

            @Override
            public void onComplete() {
                // Handle completion of network scan
            }
        };
        try {
            telephonyManager.requestNetworkScan(networkScanRequest, executor, networkScanCallback);
        } catch (Exception e) {
            Log.e("testapp", "requestNetworkScan" + e.getClass().toString());
        }
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        try {
            boolean success = wifiManager.startScan();
            Log.e("testapp", "startScan" + success);
        }catch (Exception e) {
            Log.e("testapp", "startScan" + e.getClass().toString());
        }

    }

}


