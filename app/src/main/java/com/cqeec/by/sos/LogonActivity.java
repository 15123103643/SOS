package com.cqeec.by.sos;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LogonActivity extends AppCompatActivity {
    private Handler handler;
    private EditText username;
    private EditText password;
    private Button btn_logon;

    private String url = "http://132.232.27.164/logon.php";
    logonField m_result;

    private TextView login,calbel;
    private Button logon;

    Map<String, String> map = new HashMap<String, String>();

    private logonField parseJSONWithGson(String jsonData) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, logonField.class);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        login();//注册
       // logon();//登录
        calbel();
        //处理登录成功消息
        handler  = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                switch (msg.what)
                {
                    case 123:
                        try
                        {
                            //获取用户登录的结果
                            logonField result = (logonField) msg.obj;
                            String userName = result.getUsername();

                            Toast.makeText(LogonActivity.this, "您成功登录",Toast.LENGTH_SHORT).show();

                            //跳转到登录成功的界面
                            Intent intent = new Intent(LogonActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    case 234:
                        try
                        {//获取用户登录的结果
                            logonField result = (logonField) msg.obj;
                            String userName = result.getUsername();
                            Toast.makeText(LogonActivity.this, "密码错误",Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    case 345:
                        try
                        {
                            //获取用户登录的结果
                            logonField result = (logonField) msg.obj;
                            String userName = result.getUsername();

                            Toast.makeText(LogonActivity.this, "不存在该用户",Toast.LENGTH_SHORT).show();

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        break;

                    default:
                        break;
                }
            }
        };
        Bundle bundle = this.getIntent().getExtras();

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btn_logon = findViewById(R.id.btn_logon);
        btn_logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //POST信息中加入用户名和密码
                            map.put("username", username.getText().toString().trim());
                            map.put("pd", password.getText().toString().trim());
                            //HttpUtils.httpPostMethod(url, json, handler);
                            HttpUtils.post(url, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    Log.e("testsos", "OnFaile:",e);
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String responseBodyl = response.body().string();
                                    JSONObject jsonData = null;
                                    try {
                                        jsonData = new JSONObject(responseBodyl);
                                        String resultStr = jsonData.getString( "status");
                                        if (resultStr.equals("1")){
                                            m_result = parseJSONWithGson(responseBodyl);
                                            //发送登录成功的消息
                                            Message msg = handler.obtainMessage();
                                            msg.what = 123;
                                            msg.obj = m_result; //把登录结果也发送过去
                                            handler.sendMessage(msg);

                                        }else if (resultStr.equals("-2")){
                                            m_result = parseJSONWithGson(responseBodyl);
                                            //发送登录成功的消息
                                            Message msg = handler.obtainMessage();
                                            msg.what = 234;
                                            msg.obj = m_result; //把登录结果也发送过去
                                            handler.sendMessage(msg);

                                        }else if (resultStr.equals("-1")){
                                            m_result = parseJSONWithGson(responseBodyl);
                                            //发送登录成功的消息
                                            Message msg = handler.obtainMessage();
                                            msg.what = 345;
                                            msg.obj = m_result; //把登录结果也发送过去
                                            handler.sendMessage(msg);

                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, map);



                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });


    }
//取消
    private void calbel() {
        calbel = findViewById(R.id.calbel);
        calbel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogonActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    //注册跳转
    private void login() {
        login = findViewById(R.id.tv_loginactivity_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogonActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
