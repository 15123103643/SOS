package com.cqeec.by.sos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG="LoginActivity";
    private Button login;
    private Button cancle;
    private TextView textView;
    private String response;
    private EditText inputId_P;
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
    }

    private void login() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("添加个人信息");
        View view= View.inflate(LoginActivity.this,R.layout.activity_login,null);
        builder.setView(view);


        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Id_P=inputId_P.getText().toString();
                String LastName=username.getText().toString();
                String FirstName=password.getText().toString();
                String Address=password.getText().toString();
                String City=findpd.getText().toString();
                try {
                    jsonObject.put("username",Id_P);
                    jsonObject.put("password",LastName);
                    jsonObject.put("findpd",FirstName);
                    jsonObject.put("Address",Address);
                    jsonObject.put("City",City);
                } catch (JSONException e) {
                    e.printStackTrace();
                };
                send();
            }
        });
        builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog ad=builder.create();
        ad.show();

        username= (EditText)ad.findViewById(R.id.l_username);
        password= (EditText)ad.findViewById(R.id.l_pd);
        password= (EditText)ad.findViewById(R.id.l_pd2);
        findpd  = (EditText)ad.findViewById(R.id.l_fd);



    }
    private void send() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost();
            }
        }).start();

    }
    JSONObject jsonObject=new JSONObject();
    private void executeHttpPost() {

        String path="http://127.0.0.1/android_connect/create_person.php";
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //conn.setConnectTimeout(3000);     //设置连接超时时间
            conn.setDoOutput(true);  //打开输出流，以便向服务器提交数据
            conn.setDoInput(true);  //打开输入流，以便从服务器获取数据
            conn.setUseCaches(false);//使用Post方式不能使用缓存
            conn.setRequestMethod("POST");  //设置以Post方式提交数据
            //conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            //conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");

            // 往服务器里面发送数据
            String Json=jsonObject.toString();

            System.out.println("-----------    "+Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.i("upload: ", "doJsonPost: "+conn.getResponseCode());//如输出200，则对了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void initViews() {
        login =findViewById(R.id.login);
        cancle= findViewById(R.id.cancle);

    }

}
