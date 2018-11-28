package com.example.rw18175;

import android.location.Location;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements  AMapLocationListener {
    private MyLocationStyle myLocationStyle;
    private AMap aMap;
    private MapView mv;
    private AMapLocationClient aMapLocationClient;
    private AMapLocationClientOption aMapLocationClientOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mv=findViewById(R.id.mv);
        mv.onCreate(savedInstanceState);// 此方法必须重写
       aMap = mv.getMap();
        ToggleButton tb=findViewById(R.id.tb);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                }else {
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                }
            }
        });
        initLocation();
    }

    private void initLocation() {
        MyLocationStyle myLocationStyle=new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.interval(2000);
        aMap.setMyLocationEnabled(true);
        myLocationStyle.showMyLocation(true);
        myLocationStyle.strokeColor(0xffff0000);
        myLocationStyle.radiusFillColor(0xff00ff00);
        myLocationStyle.strokeWidth(1.0f);
        aMap.setMyLocationStyle(myLocationStyle);
        aMapLocationClient=new AMapLocationClient(this);
        aMapLocationClientOption=new AMapLocationClientOption();
        aMapLocationClientOption.setNeedAddress(true);
        aMapLocationClient.setLocationListener(this);
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setInterval(2000);
        aMapLocationClient.setLocationOption(aMapLocationClientOption);
        aMapLocationClient.startLocation();
    }
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        float accuracy = aMapLocation.getAccuracy();
        double latitude = aMapLocation.getLatitude();
        double longitude = aMapLocation.getLongitude();
        long time = aMapLocation.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(time);
        String str="accuracy="+accuracy+"--latitude="+latitude+"--longitude="+longitude+"---tim="+format;
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mv.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mv.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mv.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mv.onSaveInstanceState(outState);
    }
}
