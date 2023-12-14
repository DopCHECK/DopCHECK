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
import android.net.wifi.aware.AttachCallback;
import android.net.wifi.aware.DiscoverySessionCallback;
import android.net.wifi.aware.IdentityChangedListener;
import android.net.wifi.aware.PublishConfig;
import android.net.wifi.aware.PublishDiscoverySession;
import android.net.wifi.aware.SubscribeConfig;
import android.net.wifi.aware.WifiAwareManager;
import android.net.wifi.aware.WifiAwareSession;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo;
import android.net.wifi.p2p.nsd.WifiP2pServiceInfo;
import android.net.wifi.rtt.RangingRequest;
import android.net.wifi.rtt.RangingResult;
import android.net.wifi.rtt.RangingResultCallback;
import android.net.wifi.rtt.WifiRttManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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
import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
    private WifiP2pManager wifiP2pManager;
    private WifiRttManager wifiRttManager;
    private Executor executor;
    private WifiP2pManager.Channel channel;
    private WifiAwareSession wifiAwareSession;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String permission = Manifest.permission.NEARBY_WIFI_DEVICES;
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                // Permission already granted
                try {
                    startLocalOnlyHotspotTest();
                    Log.e("ex_log", "111");
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "startLocalOnlyHotspot per exception is " + e.getClass());
                }
                try {
                    attachTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "attach per exception is " + e.getClass());
                }
                try {
                    publishTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "publish per exception is " + e.getClass());
                }
                try {
                    subscribeTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "subscribe per exception is " + e.getClass());
                }
                try {
                    addLocalServiceTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "addLocalService per exception is " + e.getClass());
                }
                try {
                    connectTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "connect per exception is " + e.getClass());
                }
                try {
                    createGroupTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "createGroup per exception is " + e.getClass());
                }
                try {
                    discoverPeersTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "discoverPeers per exception is " + e.getClass());
                }
                try {
                    discoverServicesTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "discoverServices per exception is " + e.getClass());
                }
                try {
                    requestDeviceInfoTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "requestDeviceInfo per exception is " + e.getClass());
                }
                try {
                    requestGroupInfoTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "requestGroupInfo per exception is " + e.getClass());
                }
                try {
                    requestPeersTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "requestPeers per exception is " + e.getClass());
                }
                try {
                    startRangingTest();
                } catch (Exception e){
                    String es = e.getClass().toString();
                    Log.e("ex_log", "startRanging per exception is " + e.getClass());
                }
            } else {
                // Request permission
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSION_REQUEST_CODE);
                try {
                    startLocalOnlyHotspotTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "startLocalOnlyHotspot exception is " + e.getClass());
                }
                try {
                    attachTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
//                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "attach exception is " + e.getClass());
                }
                try {
                    Log.e("ex_log", "att");
                    publishTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
//                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "publish exception is " + e.getClass());
                }
                try {
                    subscribeTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
//                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "subscribe exception is " + e.getClass());
                }
                try {
                    addLocalServiceTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
//                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "addLocalService exception is " + e.getClass());
                }
                try {
                    connectTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
//                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "connect exception is " + e.getClass());
                }
                try {
                    createGroupTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
//                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "createGroup exception is " + e.getClass());
                }
                try {
                    discoverPeersTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "discoverPeers exception is " + e.getClass());
                }
                try {
                    discoverServicesTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "discoverServices exception is " + e.getClass());
                }
                try {
                    requestDeviceInfoTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "requestDeviceInfo exception is " + e.getClass());
                }
                try {
                    requestGroupInfoTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "requestGroupInfo exception is " + e.getClass());
                }
                try {
                    requestPeersTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "requestPeers exception is " + e.getClass());
                }
                try {
                    startRangingTest();
                } catch (Exception e) {
                    String es = e.getClass().toString();
                    assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                    Log.e("ex_log", "startRanging exception is " + e.getClass());
                }
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSION_REQUEST_CODE);

            }
        } else {
            Log.e("ex_log", "sdk版本太老了");
        }

        userID = findViewById(R.id.userID);
        pwd = findViewById(R.id.pwd);
        radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.nan){
                    sex = "male";
                } else if (checkedId == R.id.nv){
                    sex ="female";
                }
            }
        });


    }
    IdentityChangedListener listener1 = new IdentityChangedListener() {
        @Override
        public void onIdentityChanged(byte[] mac) {
            // 实现 onIdentityChanged 方法的逻辑
        }
    };
    // Handle the identity change
//    @SuppressLint("MissingPermission")
    private void startLocalOnlyHotspotTest() {
        Log.e("ex_log", "startLocalOnlyHotspotTest");
        WifiManager mWifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        mWifiManager.startLocalOnlyHotspot(new WifiManager.LocalOnlyHotspotCallback() {
            public void onStarted( WifiManager.LocalOnlyHotspotReservation reservation) {
                super.onStarted(reservation);
            }

            @Override
            public void onFailed(int reason) {
                super.onFailed(reason);
            }

            @Override
            public void onStopped() {
                super.onStopped();
                // 在本地热点停止时执行的操作
            }
        },null);
        };

    private void attachTest() {
        Log.e("ex_log", "attachTest");
        WifiAwareManager mWifiAwareManager = (WifiAwareManager) this.getSystemService(Context.WIFI_AWARE_SERVICE);
        mWifiAwareManager.attach(new AttachCallback() {
            public void onAttached(WifiAwareSession session) {
                wifiAwareSession = session;
            }
            public void onAttachFailed() {
            }
        }, listener1,null);
    };
    @SuppressLint("MissingPermission")
    private void publishTest() {
        Log.e("ex_log", "publishTest");
        WifiAwareManager mWifiAwareManager = (WifiAwareManager) this.getSystemService(Context.WIFI_AWARE_SERVICE);
        mWifiAwareManager.attach(new AttachCallback() {
            public void onAttached(WifiAwareSession session) {
                WifiAwareSession wifiAwareSession = session;
                publishService();

            }
            public void onAttachFailed() {
            }
        }, null);
    };

//    @SuppressLint("MissingPermission")
    private void publishService() {
        if (wifiAwareSession != null) {
            PublishConfig config = new PublishConfig.Builder()
                    .setServiceName("ExampleService")
                    .build();
            try {
                wifiAwareSession.publish(config, new DiscoverySessionCallback() {
                    @Override
                    public void onPublishStarted(PublishDiscoverySession session) {
                        // 成功！
                    }

                    @Override
                    public void onSessionConfigUpdated() {
                        // 更新
                    }

                    @Override
                    public void onSessionConfigFailed() {
                        // 失败
                    }
                }, null);
            } catch (Exception e) {
                String es = e.getClass().toString();
                assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                Log.e("ex_log", "publish exception is " + e.getClass());
            }
        }
    }

    private void subscribeTest() {
        Log.e("ex_log", "subscribeTest");
        WifiAwareManager mWifiAwareManager = (WifiAwareManager) this.getSystemService(Context.WIFI_AWARE_SERVICE);
        mWifiAwareManager.attach(new AttachCallback() {
            public void onAttached(WifiAwareSession session) {
                WifiAwareSession wifiAwareSession = session;
                subscribeService();

            }
            public void onAttachFailed() {
            }
        }, null);
    };
//    @SuppressLint("MissingPermission")
    private void subscribeService() {
        if (wifiAwareSession != null) {
            // 创建发布配置
            SubscribeConfig config = new SubscribeConfig.Builder()
                    .setServiceName("ExampleService")
                    .build();
            try {
                wifiAwareSession.subscribe(config, new DiscoverySessionCallback() {
                    public void onSubscribeStarted(PublishDiscoverySession session) {
                        // 成功！
                    }
                }, null);
            } catch (Exception e) {
                String es = e.getClass().toString();
                assert es.equals("class java.lang.SecurityException") : "No SecurityException is thrown";
                Log.e("ex_log", "subscribe exception is " + e.getClass());
            }
        }
    }
//    @SuppressLint("MissingPermission")
    private void addLocalServiceTest() {
        Log.e("ex_log", "addLocalServiceTest");
        wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = wifiP2pManager.initialize(this, getMainLooper(), null);
        if (wifiP2pManager != null && channel != null) {

            WifiP2pServiceInfo serviceInfo = WifiP2pDnsSdServiceInfo.newInstance("my_instance", "my_type", new HashMap<String, String>());
            wifiP2pManager.addLocalService(channel, serviceInfo, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onFailure(int reason) {
                }
            });
        }
    }
//    @SuppressLint("MissingPermission")
    private void connectTest() {
        Log.e("ex_log", "connectTest");
        wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = wifiP2pManager.initialize(this, getMainLooper(), null);
        if (wifiP2pManager != null && channel != null) {
            WifiP2pConfig config = new WifiP2pConfig();
            config.deviceAddress = "111";
            wifiP2pManager.connect(channel, config, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onFailure(int reason) {
                }
            });
        }
    }
//    @SuppressLint("MissingPermission")
    private void createGroupTest() {
        Log.e("ex_log", "createGroupTest");
//        wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
//        channel = wifiP2pManager.initialize(this, getMainLooper(), null);
        if (wifiP2pManager != null && channel != null) {
            wifiP2pManager.createGroup(channel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onFailure(int reason) {
                }
            });
        }
    }
//    @SuppressLint("MissingPermission")
    private void discoverPeersTest() {
        Log.e("ex_log", "discoverPeersTest");
        if (wifiP2pManager != null && channel != null) {
            wifiP2pManager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onFailure(int reason) {
                }
            });
        }
    }
//    @SuppressLint("MissingPermission")
    private void discoverServicesTest() {
        Log.e("ex_log", "discoverServicesTest");
        if (wifiP2pManager != null && channel != null) {
            wifiP2pManager.discoverServices(channel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                }
                @Override
                public void onFailure(int reason) {
                }
            });
        }
    }
//    @SuppressLint("MissingPermission")
    private void requestDeviceInfoTest() {
        Log.e("ex_log", "requestDeviceInfoTest");
        if (wifiP2pManager != null && channel != null) {
            wifiP2pManager.requestDeviceInfo(channel, new WifiP2pManager.DeviceInfoListener() {
                public void onDeviceInfoAvailable(WifiP2pDevice device) {
                    if (device != null) {
                        device.deviceName = "my_device";
                        device.deviceAddress = "my_device_address";
                        String deviceName = device.deviceName;
                        String deviceAddress = device.deviceAddress;
                    }
                }
            });
        }
    }
//    @SuppressLint("MissingPermission")
    private void requestGroupInfoTest() {
        Log.e("ex_log", "requestGroupInfoTest");
        if (wifiP2pManager != null && channel != null) {
            wifiP2pManager.requestGroupInfo(channel, new WifiP2pManager.GroupInfoListener() {
                public void onGroupInfoAvailable(WifiP2pGroup group) {
                    if (group != null) {
                        String networkName = group.getNetworkName();
                        String passphrase = group.getPassphrase();
                        int deviceCount = group.getClientList().size();
                    }
                }
            });
        }
    }
//    @SuppressLint("MissingPermission")
    private void requestPeersTest() {
        Log.e("ex_log", "requestPeersTest");
        if (wifiP2pManager != null && channel != null) {
            wifiP2pManager.requestPeers(channel, new WifiP2pManager.PeerListListener() {
                public void onPeersAvailable(WifiP2pDeviceList peers) {
                }
            });
        }
    }
    @SuppressLint("MissingPermission")
    private void startRangingTest() {
        Log.e("ex_log", "startRangingTest");
        if (wifiRttManager != null) {
            RangingRequest request = new RangingRequest.Builder().build();

            RangingResultCallback callback = new RangingResultCallback() {
                @Override
                public void onRangingResults(List<RangingResult> results) {
                    for (RangingResult result : results) {
                        if (result.getStatus() == RangingResult.STATUS_SUCCESS) {
                            double distanceMeters = result.getDistanceMm() / 1000.0;
                        }
                    }
                }
                @Override
                public void onRangingFailure(int code) {
                }
            };
            executor = Executors.newFixedThreadPool(4);
            wifiRttManager.startRanging(request, executor, callback);
        }
    }



    public void myclick(View view){
        userinfo = userID.getText().toString();
        userpwd = pwd.getText().toString();
        Toast.makeText(MainActivity.this, "user id is " + userinfo + " user password is " + userpwd + " sex is " + sex,Toast.LENGTH_SHORT).show();
    }
}
