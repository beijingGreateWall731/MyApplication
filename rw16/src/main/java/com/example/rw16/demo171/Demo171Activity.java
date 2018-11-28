package com.example.rw16.demo171;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.rw16.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo171Activity extends AppCompatActivity {
    private DrawerLayout dl;
    private ListView lv;
    private List<Map<String,Object>> list;
    private String[] from=new String[] {"img","title"};
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo171);
        initView();
    }

    private void initView() {
        tb=findViewById(R.id.tb);
        list=new ArrayList<>();
        dl=findViewById(R.id.dl);
        lv=findViewById(R.id.lv);
        int[] to={R.id.iv,R.id.tv};
        initData();
        SimpleAdapter adapter=new SimpleAdapter(this,list,R.layout.lv_item,from,to);
        lv.setAdapter(adapter);

        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setHomeAsUpIndicator(R.mipmap.caidan3);
        }

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String title = (String) list.get(position).get("title");
               Toast.makeText(Demo171Activity.this,title,Toast.LENGTH_SHORT).show();
               dl.closeDrawers();
           }
       });
    }

    private void initData() {
        String titles[]={"联系人","群组","地址","设置"};
        int[] imgs={R.mipmap.lianxiren,R.mipmap.qun,R.mipmap.dizhi,R.mipmap.shezhi};
        for(int i=0;i<titles.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put(from[0],imgs[i]);
            map.put(from[1],titles[i]);
            list.add(map);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            dl.openDrawer(GravityCompat.START);
        }
        return  true;
    }
}
