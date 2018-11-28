package com.example.zhujl.myapplication;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.sql.SQLNonTransientException;

public class Demo174Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo174);
        Toolbar tb=findViewById(R.id.tb_174);
        setSupportActionBar(tb);
        FloatingActionButton fab=findViewById(R.id.fab_174);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"要删除吗？",Snackbar.LENGTH_SHORT)
                        .setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(Demo174Activity.this,"删除成功",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.back:
                Toast.makeText(this,"back",Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                Toast.makeText(this,"home",Toast.LENGTH_SHORT).show();
                break;
            case R.id.set:
                Toast.makeText(this,"set",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
