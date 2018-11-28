package com.example.rw15;


import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class FruitActivity extends AppCompatActivity {
    private ImageView iv;
    private TextView tv;
    private CollapsingToolbarLayout ctl;
    private Toolbar tb;
    private String name;
    private  int img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
       name =intent.getStringExtra("name");
       img=intent.getIntExtra("img",R.mipmap.f1);
        initView();
    }

    private void initView() {
        tv=findViewById(R.id.tv_xiangqing);
        iv=findViewById(R.id.iv_xiangqing);
        ctl=findViewById(R.id.ctl);
        tb=findViewById(R.id.tb_xiangqing);
        setSupportActionBar(tb);

        iv.setImageResource(img);
        tv.setText(genrateStr(name));
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        }

        ctl.setTitle(name);

    }

    private String genrateStr(String name) {
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
