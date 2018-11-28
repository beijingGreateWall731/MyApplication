package com.example.rw15;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        list=new ArrayList<>();
        Toolbar tb=findViewById(R.id.tb);
        RecyclerView rv=findViewById(R.id.rv);
        setSupportActionBar(tb);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        initData();
        rv.setAdapter(new MyAdapter(list,this));
    }

    private void initData() {
        int imgs[]={R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4,R.mipmap.f5,R.mipmap.f6};
        String names[]={"苹果","樱桃","草莓","桔子","猕猴桃","石榴","苹果","樱桃","草莓","桔子","猕猴桃","石榴","苹果","樱桃","草莓","桔子","猕猴桃","石榴","苹果","樱桃","草莓","桔子","猕猴桃","石榴"};
        for(int i=0;i<imgs.length;i++){
            list.add(new Fruit(names[i],imgs[i]));
        }
    }
}
