package com.colinapp.onlinemilkorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //bmob 服务初始化
        Bmob.initialize(this,"b3f8dce8dcbe72882c843b5b47269a4d");
        findWidget();
        this.btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.updateData();
            }
        });

        this.btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_about:
                break;
            case R.id.action_ver:
                break;
                default:
                    break;
        }

        return super.onOptionsItemSelected(item);
    }

    //定义控件
    private EditText txt_name;
    private EditText txt_age;
    private Button btn_insert;
    private Button btn_open;

    //初始化控件

    private void findWidget(){

        this.txt_name =  findViewById(R.id.txt_name);
        this.txt_age =   findViewById(R.id.txt_age);
        this.btn_insert =  findViewById(R.id.btn_insert);
        this.btn_open = findViewById(R.id.btn_open);
    }
    private void updateData(){

        Person p = new Person();
        p.setName(this.txt_name.getText().toString());
        p.setAge(this.txt_age.getText().toString());
        p.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

                if (e == null){
                    Toast.makeText(MainActivity.this,"插入数据成功:",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this,"插入数据失败:" + e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
