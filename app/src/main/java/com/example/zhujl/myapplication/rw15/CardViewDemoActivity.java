package com.example.zhujl.myapplication.rw15;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.zhujl.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CardViewDemoActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private RecyclerView rv;
    private Toolbar tb;
    private List<Fruit> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_demo);
        initView();
    }

    private void initView() {
        list=new ArrayList<>();
        fab=findViewById(R.id.rw15_fab);
        tb=findViewById(R.id.rw15_tb);
        setSupportActionBar(tb);
        rv=findViewById(R.id.rw15_rv);

        initData();
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rv.setAdapter(new MyAdapter(this,list));
    }

    private void initData() {
        int[] imgs={R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6};
        String names[]={"苹果","李子","草莓","橘子","猕猴桃","石榴","苹果","李子","草莓","橘子","猕猴桃","石榴","苹果","李子","草莓","橘子","猕猴桃","石榴"};
        for(int i=0;i<imgs.length;i++){
            Fruit fruit=new Fruit(names[i],imgs[i]);
            list.add(fruit);
        }
    }
}
