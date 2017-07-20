package com.example.tr.datacollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button submit;
    private EditText username;
    private EditText passsword;
    private boolean debug = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        initView();
    }

    private void initView() {
        submit = (Button) findViewById(R.id.subBtn);
        username = (EditText) findViewById(R.id.accountEt);
        passsword = (EditText) findViewById(R.id.pwdEt);
    }
    public void subMit(View v){
        if(debug){
            Intent i = new Intent(LoginActivity.this, MarkerPosition.class);
            i.putExtra("username", username.getText().toString());
            i.putExtra("password", passsword.getText().toString());
            startActivity(i);
            finish();
        }else {
            if (username.getText().toString().equals("admin") && passsword.getText().toString().equals("123")) {
                Intent i = new Intent(LoginActivity.this, MarkerPosition.class);
                i.putExtra("username", username.getText().toString());
                i.putExtra("password", passsword.getText().toString());
                startActivity(i);
                finish();
            } else if (username.getText().toString().equals("")) {
                Toast.makeText(this, "未输入用户名或警号!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    }
}