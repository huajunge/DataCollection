package com.example.tr.datacollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button submit;
    private EditText username;
    private EditText passsword;
    private Spinner team;
    private boolean debug = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();
    }
    public void sendPost(String url, Object object, final String errorNotification){
        JSON JJ =(JSONObject) JSONObject.toJSON(object);
        JSONObject jsonObject = JSONObject.parseObject(JJ.toJSONString());
        System.out.println(JJ.toJSONString());
        System.out.println(jsonObject.toJSONString());
        Log.i("httptest","JJ.toJSONString()"+JJ.toJSONString());
        Log.i("httptest","jsonObject.toJSONString()"+jsonObject.toJSONString());

        org.json.JSONObject jsonObject1 = null;
        try {
            jsonObject1 = new org.json.JSONObject(jsonObject.toJSONString());
            Log.i("httptest","jsonObject1.toJSONString()"+jsonObject1.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                jsonObject1 , new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject jsonObject) {
                Log.i("httptest","onResponse"+jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("httptest","onErrorResponse"+volleyError.getMessage());
                Log.i("httptest","onErrorResponse"+errorNotification);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String,String>();
                headers.put("Accept","application/json");
                headers.put("Content-Type","application/json;charset=UTF-8");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    };
    private void initView() {
        submit = (Button) findViewById(R.id.subBtn);
        username = (EditText) findViewById(R.id.accountEt);
        passsword = (EditText) findViewById(R.id.pwdEt);
        team = (Spinner) findViewById(R.id.team);

        String[] teams = {"请选择所属大队","一大队","二大队","三大队","四大队"};
        team.setAdapter(new MyAdapter2(teams,this).getAdaper());
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