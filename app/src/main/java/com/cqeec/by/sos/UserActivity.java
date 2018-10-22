package com.cqeec.by.sos;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    public static final int TAKE_PHOTO = 1;
    private Button bback, bsave;//按钮
    private EditText user_name, user_sfzh, user_jtdz, user_xx, user_gmfy, user_zdy;//输入框
    private ImageButton ibtn;//头像
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LitePal.initialize(this);
        setContentView(R.layout.activity_user);
        ActionBar();//隐藏自带标题
        Edit();//实例化控件
        Select();
        bsave();//保存按钮
        bback();//返回
        img_camera();
        //显示


    }

    private void img_camera() {
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(getExternalCacheDir()+"/images/", "output_Uname.jpg");
                try {
                    if (!outputImage.getParentFile().exists()) {
                        outputImage.getParentFile().mkdir();
                    }

                        Log.i("jpg", outputImage.getAbsolutePath());
                        if (Build.VERSION.SDK_INT >= 24) {
                            imageUri = FileProvider.getUriForFile(UserActivity.this,
                                    "com.cqeec.by.sos.fileprovider", outputImage);
                        } else {
                            imageUri = Uri.fromFile(outputImage);
                        }
                        //启动相机程序
                        Intent intent2 = new Intent("android.media.action.IMAGE_CAPTURE");
                        intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent2.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent2, TAKE_PHOTO);

                } catch (Exception e) {
                    Toast.makeText(UserActivity.this, "出现错误，请联系开发者", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
            //显示
            public void onActityResult(int requestCode,int resultCode,Intent data){
                switch (requestCode){
                    case TAKE_PHOTO:
                        if (resultCode == RESULT_OK){
                            try {
                                //将拍摄的照片显示出来
                                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                        .openInputStream(imageUri));
                                ibtn.setImageBitmap(bitmap);
                            }catch (FileNotFoundException e){
                                e.printStackTrace();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });


    }
    //显示
    public  void onActityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    try {
                        //将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(imageUri));
                        ibtn.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
                default:
                    break;
        }
    }

    //返回
    private void bback() {
        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //查询表数据,用户第二次进入的数据显示
    private void Select() {
        UserDB userDB = LitePal.find(UserDB.class, 1);
        user_name.setText(userDB.getU_name());
        user_gmfy.setText(userDB.getU_gmfy());
        user_jtdz.setText(userDB.getU_jtdz());
        user_sfzh.setText(userDB.getU_sfzh());
        user_zdy.setText(userDB.getU_zdy());
        user_xx.setText(userDB.getU_xx());

    }

    //绑定控件
    public void Edit() {
        ibtn = findViewById(R.id.imageButton);
        bback = findViewById(R.id.bback);
        user_name = findViewById(R.id.username);
        user_sfzh = findViewById(R.id.sfzh);
        user_jtdz = findViewById(R.id.jtdz);
        user_xx = findViewById(R.id.xx);
        user_gmfy = findViewById(R.id.gmfy);
        user_zdy = findViewById(R.id.zdy);
    }

    //保存按钮
    public void bsave() {
        //查询ID等于1的数据
        final UserDB userDB = LitePal.find(UserDB.class, 1);
        if (userDB.getId() == 1) {
            bsave = findViewById(R.id.bsave);
            bsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserDB db = new UserDB();
                    String name = user_name.getText().toString();
                    String sfzh = user_sfzh.getText().toString();
                    String jtdz = user_jtdz.getText().toString();
                    String xx = user_xx.getText().toString();
                    String gmfy = user_gmfy.getText().toString();
                    String zdy = user_zdy.getText().toString();
                    db.setU_name(name);
                    db.setU_sfzh(sfzh);
                    db.setU_jtdz(jtdz);
                    db.setU_xx(xx);
                    db.setU_gmfy(gmfy);
                    db.setU_zdy(zdy);
                    Log.i("用户数据", "修改数据");
                    db.update(userDB.getId());

                    Toast.makeText(UserActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            bsave = findViewById(R.id.bsave);
            bsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UserDB db = new UserDB();
                    db.setU_name(user_name.getText().toString());
                    db.setU_sfzh(user_sfzh.getText().toString());
                    db.setU_jtdz(user_jtdz.getText().toString());
                    db.setU_xx(user_xx.getText().toString());
                    db.setU_gmfy(user_gmfy.getText().toString());
                    db.setU_zdy(user_zdy.getText().toString());
                    Log.i("用户数据", "保存数据");
                    db.save();

                    Toast.makeText(UserActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }


    }

    //隐藏自带标题
    public void ActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();

        }
    }
}
