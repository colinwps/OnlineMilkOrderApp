package com.colinapp.onlinemilkorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        BmobSMS.initialize(this,"b3f8dce8dcbe72882c843b5b47269a4d");
        txt_phoneno = findViewById(R.id.txt_phone);
        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignUpActivity.this.sendSMS();
            }
        });

    }

    private EditText txt_phoneno;
    private Button btn_send;

    public void sendSMS(){
        String phoneno = this.txt_phoneno.getText().toString();
        BmobSMS.requestSMS(this, phoneno, "milkorder", "milkorder", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if(e == null){
                    Toast.makeText(SignUpActivity.this,"请求成功!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SignUpActivity.this,"请求失败:" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
