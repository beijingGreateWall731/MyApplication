package com.example.rw16.demo171;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rw16.Fruit;
import com.example.rw16.MyAdapter;
import com.example.rw16.R;

import java.util.ArrayList;
import java.util.List;

public class Demo1712Activity extends AppCompatActivity {
    private DrawerLayout dl;
    private Toolbar tb;
    private NavigationView nv;
    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    private MyAdapter adapter;
    private List<Fruit> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1712);
        initView();
    }

    private void initView() {

        rv=findViewById(R.id.rv);
        srl=findViewById(R.id.srl);
        tb=findViewById(R.id.tb);
        dl=findViewById(R.id.dl);
        nv=findViewById(R.id.nv);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.mipmap.caidan3);
        }

        diJi();
        setRvSrl();
    }

    private void setRvSrl() {
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        list=new ArrayList<>();
        initData();
        adapter=new MyAdapter(list,this);
        rv.setAdapter(adapter);

        srl.setColorSchemeResources(R.color.aa,R.color.dd,R.color.ee,R.color.colorPrimary);
//        srl.setProgressBackgroundColorSchemeResource(R.color.blue);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getDataFromNewWork();
                    }
                }).start();
            }
        });
    }

    private void getDataFromNewWork() {
        try {
            Thread.sleep(5000);
            getData();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    srl.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(Demo1712Activity.this,"加载完成",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void diJi() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.dizhi:
                        Toast.makeText(Demo1712Activity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.email:
                        Toast.makeText(Demo1712Activity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        break;
                }
               // Toast.makeText(Demo1712Activity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                dl.closeDrawers();
                return  true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            dl.openDrawer(GravityCompat.START);
        }
        return  true;
    }
    private void initData() {
        int imgs[]={R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6};
        String names[]={"苹果","樱桃","草莓","桔子","猕猴桃","石榴","苹果","樱桃","草莓","桔子","猕猴桃","石榴","苹果","樱桃","草莓","桔子","猕猴桃","石榴","苹果","樱桃","草莓","桔子","猕猴桃","石榴"};
        for(int i=0;i<imgs.length;i++){
            list.add(new Fruit(names[i],imgs[i]));
        }
    }
    private void getData() {
        int imgs[]={R.mipmap.g1,R.mipmap.g2,R.mipmap.g3};
        String names[]={"苹果","樱桃","草莓"};
        for(int i=0;i<imgs.length;i++){
            list.add(0,new Fruit(names[i],imgs[i]));
        }
    }
}
