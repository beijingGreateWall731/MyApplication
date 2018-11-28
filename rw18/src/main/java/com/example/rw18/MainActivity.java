package com.example.rw18;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AMapLocationListener {
    private MapView mv;
    private AMap aMap;
    private ToggleButton tb;
    private AMapLocationClient aMapLocationClient;
    private AMapLocationClientOption aMapLocationClientOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mv=findViewById(R.id.mv);
        mv.onCreate(savedInstanceState);
        if(aMap==null){
            aMap=mv.getMap();
        }
//        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
//        aMap.setTrafficEnabled(true);// 显示实时交通状况
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        tb=findViewById(R.id.tb);
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
        Button btn=findViewById(R.id.btn_goto_share);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SharedActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initLocation() {
        MyLocationStyle myLocationStyle=new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.interval(2000);
        aMap.setMyLocationEnabled(true);
        myLocationStyle.showMyLocation(true);
        myLocationStyle.strokeColor(0xffff0000);
        myLocationStyle.radiusFillColor(0xff00ff00);
        myLocationStyle.strokeWidth(2.0f);
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

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(aMapLocation!=null){
            if(aMapLocation.getErrorCode()==0){
                int locationType = aMapLocation.getLocationType();
                double latitude = aMapLocation.getLatitude();
                double longitude = aMapLocation.getLongitude();
                float accuracy = aMapLocation.getAccuracy();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=new Date(aMapLocation.getTime());
                String format = sdf.format(date);
                String str="locationType="+locationType+"--latitude="+latitude+"--longitude="+longitude+"--\naccuracy="+accuracy+"--format="+format;
                Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
