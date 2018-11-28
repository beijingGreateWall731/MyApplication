package com.example.rw16;

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

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity {
    private  DrawerLayout dl;
    private RecyclerView rv;
    private SwipeRefreshLayout srl;
    private List<Fruit> list;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        list=new ArrayList<>();
        Toolbar tb=findViewById(R.id.tb);
        final NavigationView nv=findViewById(R.id.nv);
        setSupportActionBar(tb);
        ActionBar ab=getSupportActionBar();
       dl=findViewById(R.id.dl);
        if(ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.mipmap.caidan3);
            ab.setTitle(R.string.app_name);
        }
        nv.setCheckedItem(R.id.lxr);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.lxr:
//                        Toast.makeText(Main2Activity.this,"你点击的是："+menuItem.getTitle(),Toast.LENGTH_SHORT).show();
//                        break;
//                }
                Toast.makeText(Main2Activity.this,"你点击的是："+menuItem.getTitle(),Toast.LENGTH_SHORT).show();
               dl.closeDrawers();
                return true;
            }
        });

        rv=findViewById(R.id.rv);
        srl=findViewById(R.id.srl);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        initData();
        adapter=new MyAdapter(list,this);
        rv.setAdapter(adapter);
        srl.setColorSchemeColors(getResources().getColor(R.color.blue));
        srl.setColorSchemeResources(R.color.colorAccent);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startMyThread();
            }
        });
    }

    private void startMyThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    for(int i=0;i<10;i++){
                        Thread.sleep(500);
                        initData();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            srl.setRefreshing(false);
                            Toast.makeText(Main2Activity.this,"加载完成",Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
    private void initData() {
        int imgs[]={R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6};
        String names[]={"苹果","樱桃","草莓","桔子","猕猴桃","石榴","苹果","樱桃","草莓","桔子","猕猴桃","石榴","苹果","樱桃","草莓","桔子","猕猴桃","石榴","苹果","樱桃","草莓","桔子","猕猴桃","石榴"};
        for(int i=0;i<imgs.length;i++){
            list.add(new Fruit(names[i],imgs[i]));
        }
    }
}
