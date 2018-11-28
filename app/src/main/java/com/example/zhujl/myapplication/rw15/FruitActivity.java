package com.example.zhujl.myapplication.rw15;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhujl.myapplication.R;

public class FruitActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private RecyclerView rv;
    private Toolbar tb;
    private CollapsingToolbarLayout ctbl;
    private String name;
    private int img;
    private ImageView iv;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        name=intent.getStringExtra("name");
        img=intent.getIntExtra("img",R.mipmap.f1);
        initView();
    }

    private void initView() {
        tb=findViewById(R.id.fruit_toolbar);
        setSupportActionBar(tb);
        ctbl=findViewById(R.id.rw15_ctbl);
        iv=findViewById(R.id.fruit_img);
        tv=findViewById(R.id.tv_content);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ctbl.setTitle(name);
        iv.setImageResource(img);
        tv.setText(genterCon(name));
    }

    public String genterCon(String name){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<1000;i++){
            sb.append(name);
        }
        return sb.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}
