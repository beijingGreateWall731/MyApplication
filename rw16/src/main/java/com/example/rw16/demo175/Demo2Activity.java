package com.example.rw16.demo175;

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

public class Demo2Activity extends AppCompatActivity {
    private Toolbar tb;
    private DrawerLayout dl;
    private NavigationView nv;
    private RecyclerView rv;
    private List<Fruit> list;
    private SwipeRefreshLayout srl;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        initView();
    }

    private void initView() {
        srl=findViewById(R.id.srl);
        list=new ArrayList<>();
        rv=findViewById(R.id.rv);
        dl=findViewById(R.id.dl);
        nv=findViewById(R.id.nv);
        tb = findViewById(R.id.tb);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.mipmap.caidan3);
        }
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.qun:
                        Toast.makeText(Demo2Activity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.lianxir:
                        Toast.makeText(Demo2Activity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        break;
                }
                dl.closeDrawers();
                return true;
            }
        });

        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        initData();
        adapter=new MyAdapter(list,this);
        rv.setAdapter(adapter);
        srl.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimaryDark,R.color.aa,R.color.bb);
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
            initData2();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    srl.setRefreshing(false);
                    Toast.makeText(Demo2Activity.this,"加载完成",Toast.LENGTH_SHORT).show();
                }
            });
            adapter.notifyDataSetChanged();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                break;
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
    private void initData2() {
        int imgs[]={R.mipmap.g1,R.mipmap.g2,R.mipmap.g3,R.mipmap.g4,R.mipmap.g5,R.mipmap.g6};
        String names[]={"苹果","樱桃","草莓","桔子","猕猴桃","石榴"};
        for(int i=0;i<imgs.length;i++){
            list.add(1,new Fruit(names[i],imgs[i]));
        }
    }
}
