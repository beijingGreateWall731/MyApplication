package com.example.rw16;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private DrawerLayout dl;
    private List<Map<String,Object>> list;
    private  String from[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        list=new ArrayList<>();
        lv=findViewById(R.id.lv);
        dl=findViewById(R.id.dl);
        dl.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                Toast.makeText(MainActivity.this,"onDrawerSlide",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Toast.makeText(MainActivity.this,"onDrawerOpened",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Toast.makeText(MainActivity.this,"onDrawerClosed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int i) {
                Toast.makeText(MainActivity.this,"onDrawerStateChanged",Toast.LENGTH_SHORT).show();
            }
        });
       from=new String[]{"img","title"};
        int to[]={R.id.iv,R.id.tv};
        initData();
        SimpleAdapter adapter=new SimpleAdapter(this,list,R.layout.lv_item,from,to);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"你点击的是"+list.get(position).get("title"),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        String name[]={"联系人","群组","地址","邮箱","设置"};
        int imgs[]={R.mipmap.lianxiren,R.mipmap.qun,R.mipmap.dizhi,R.mipmap.youxiang,R.mipmap.youxiang};
        for(int i=0;i<name.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put(from[0],imgs[i]);
            map.put(from[1],name[i]);
            list.add(map);
        }
    }
}
