package com.cqeec.by.sos;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wkp.runtimepermissions.callback.PermissionCallBack;
import com.wkp.runtimepermissions.util.RuntimePermissionUtil;

import org.litepal.LitePal;

import java.io.File;

public class UserActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.M)

    //
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private Uri cropImageUri;

    private Button bback, bsave;//按钮
    private EditText user_name, user_sfzh, user_jtdz, user_xx, user_gmfy, user_zdy;//输入框
    private ImageView photo;//头像
    private Uri imageUri;//

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LitePal.initialize(this);
        setContentView(R.layout.activity_user);
        ActionBar();//隐藏自带标题
        Edit();//实例化控件
        bsave();//保存按钮
        bback();//返回
        img_camera();//头像的保存
        Select();//显示用户数据
        //img_set();//显示用户头像


    }

    //绑定控件
    public void Edit() {

        photo = findViewById(R.id.photo);
        bback = findViewById(R.id.bback);
        user_name = findViewById(R.id.username);
        user_sfzh = findViewById(R.id.sfzh);
        user_jtdz = findViewById(R.id.jtdz);
        user_xx = findViewById(R.id.xx);
        user_gmfy = findViewById(R.id.gmfy);
        user_zdy = findViewById(R.id.zdy);
    }

    /*将保存的用户图片显示到IMAVIEW*/
    public void img_set() {
        try {
            Bitmap bmp = BitmapFactory.decodeFile(fileUri.toString());
            if (bmp==null){

            }else {
                photo.setImageBitmap(bmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //相机
    public void camera() {
        applyPermission();
    }

    //权限
    public void applyPermission() {
        //权限检查，回调是权限申请结果
        RuntimePermissionUtil.checkPermissions(this, RuntimePermissionUtil.CAMERA, new PermissionCallBack() {
            @Override
            public void onCheckPermissionResult(boolean hasPermission) {
                if (hasPermission) {
                    //直接做具有权限后的操作
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            //通过FileProvider创建一个content类型的Uri
                            imageUri = FileProvider.getUriForFile(UserActivity.this, "com.cqeec.by.sos.fileprovider", fileUri);
                        PhotoUtils.takePicture(UserActivity.this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        Toast.makeText(UserActivity.this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                        Log.e("asd", "设备没有SD卡");
                    }
                    Toast.makeText(UserActivity.this, "权限申请成功", Toast.LENGTH_SHORT).show();

                }else {
                    //显示权限不具备的界面
                    Toast.makeText(UserActivity.this, "权限申请失败", Toast.LENGTH_SHORT).show();

                }
            }
        });
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

    //相册
    public void photo() {
        PhotoUtils.openPic(UserActivity.this, CODE_GALLERY_REQUEST);
    }

    //图片按钮
    private void img_camera() {
        photo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final String[] names = {"相机", "图库", "取消"};// 列表中显示的内容组成的数组
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setTitle("图片选择");
                builder.setIcon(R.mipmap.select);
                BaseAdapter adapter = new UserSelectAdapter(UserActivity.this, names);
                DialogInterface.OnClickListener listener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                switch (which) {
                                    case 0:
                                        camera();
                                        break;
                                    case 1:
                                        photo();
                                        break;
                                    default:
                                        break;
                                }

                            }
                        };
                builder.setAdapter(adapter, listener);
                builder.create();
                builder.show();


            }


        });


    }

    //隐藏自带标题
    public void ActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();

        }
    }

    //保存按钮

    //查询表数据,用户第二次进入的数据显示
    private void Select() {

        UserDB userDB = LitePal.findFirst(UserDB.class);
        if (userDB != null) {
            user_name.setText(userDB.getU_name());
            user_gmfy.setText(userDB.getU_gmfy());
            user_jtdz.setText(userDB.getU_jtdz());
            user_sfzh.setText(userDB.getU_sfzh());
            user_zdy.setText(userDB.getU_zdy());
            user_xx.setText(userDB.getU_xx());
        } else {
            Log.i("用户数据", "Select: 数据加载失败");
        }

    }

    public void bsave() {
        //查询ID等于1的数据
        final UserDB userDB = LitePal.find(UserDB.class, 1);
        //List<UserDB> userDB = LitePal.findAll(UserDB.class);
        bsave = findViewById(R.id.bsave);
        try {
            if (userDB.getId() == 1) {
                /*    bsave = findViewById(R.id.bsave);*/
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
                /*   bsave = findViewById(R.id.bsave);*/
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

        } catch (NullPointerException e) {
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
            e.printStackTrace();

        }


    }

    /* -----------------------------------------------*/
   /*
   拍照完成后的回调函数
   */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int output_X = 480, output_Y = 480;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    cropImageUri = Uri.fromFile(fileUri);
                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(this, "com.cqeec.by.sos.fileprovider", new File(newUri.getPath()));
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        Toast.makeText(UserActivity.this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        showImages(bitmap);
                    }
                    break;
            }
        }
    }

    /*
    将图片显示到桌面
    */
    private void showImages(Bitmap bitmap) {
        Log.i("图片查看", bitmap.toString());
        photo.setImageBitmap(bitmap);

    }
}





