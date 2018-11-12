package com.cqeec.by.sos;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends Activity {
    private String url = "http://132.232.27.164/login.php";
    private int status;
    private JSONObject json = new JSONObject();
    private Handler handler;

    public static final String TAG="LoginActivity";
    private Button login;
    private Button cancle;

    private EditText username;
    private EditText password;
    private EditText password2;
    private EditText findpd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        login();
        cancle();
    }

    private void cancle() {
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,LogonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = findViewById(R.id.I_username);
                password = findViewById(R.id.I_pd);
                password2 = findViewById(R.id.I_pd2);
                findpd = findViewById(R.id.I_fd);

                sendRequesWithOkHttp();
            }
        });
    }

    private void sendRequesWithOkHttp() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                if (msg.what==123)
                {
                    //跳转到登录成功的界面
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, LogonActivity.class);
                    startActivity(intent);
                }
                else if (msg.what == 234)
                {
                    Toast.makeText(LoginActivity.this, "已有该用户名", Toast.LENGTH_SHORT).show();
                }else  if (msg.what==345){
                    Toast.makeText(LoginActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                }else if (msg.what==456){
                    Toast.makeText(LoginActivity.this, "未知错误，可能是网络原因", Toast.LENGTH_SHORT).show();
                }else if (msg.what==567){
                    Toast.makeText(LoginActivity.this, "用户名丶密码丶密钥不能为空", Toast.LENGTH_SHORT).show();
                }else if (msg.what==1234){
                    Toast.makeText(LoginActivity.this, "注册成功，用户名中的空格已自动删除,", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, LogonActivity.class);
                    startActivity(intent);
                }

            }
        };

        //开启线程来执行网络访问
        new Thread(new Runnable() {
            @Override
            public void run() {
                String uname = username.getText().toString();
                String pd = password.getText().toString();
                String pd2 = password2.getText().toString();
                String fpd = findpd.getText().toString();
                try {
                    json.put("username", uname);
                    json.put("password", pd);
                    json.put("password2", pd2);
                    json.put("findpd", fpd);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                String jsonStr = json.toString();
                HttpUtils.postJson(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("TAG", "NetConnect error!");
                    }

                    @Override
                    //回调
                    public void onResponse(Call call, Response response) throws IOException {
                        //String responseStr = response.toString();
                        String responseBodyStr = response.body().string();
                        try {

                            //获取返回的json数据，为{"success":"success"}形式.
                            //JSONArray jsonArray = new JSONArray(responseBodyStr);
                            JSONObject jsonData = new JSONObject(responseBodyStr);
                            String resultStr = jsonData.getString( "success");

                            if (resultStr.equals("success")) //注册成功，发送消息
                            {

                                    Message msg = handler.obtainMessage();
                                    msg.what = 123;
                                    handler.sendMessage(msg);

                            }
                            else if(resultStr.equals("failed")) //注册失败
                            {
                                Message msg = handler.obtainMessage();
                                msg.what = 234;
                                handler.sendMessage(msg);
                            }else if (resultStr.equals("error")){
                                Message msg = handler.obtainMessage();
                                msg.what = 345;
                                handler.sendMessage(msg);
                            }else if (resultStr.equals("errornull")){
                                Message msg = handler.obtainMessage();
                                msg.what = 567;
                                handler.sendMessage(msg);
                            }else if (resultStr.equals("StringNull")){
                                Message msg = handler.obtainMessage();
                                msg.what = 1234;
                                handler.sendMessage(msg);
                            }
                            else {
                                Message msg = handler.obtainMessage();
                                msg.what = 456;
                                handler.sendMessage(msg);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, jsonStr);


            }
        }).start();
    }



    //预处理空间
    private void initViews() {
        login =findViewById(R.id.login2);


        cancle= findViewById(R.id.cancle);

    }

    


}
