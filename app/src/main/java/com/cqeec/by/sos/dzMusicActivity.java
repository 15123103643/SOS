package com.cqeec.by.sos;


import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class dzMusicActivity extends Activity {
    public static MediaPlayer mp = new MediaPlayer();//声音处理类
    private Button stop;

    @Override
    protected void onStart() {
        super.onStart();
        if (!mp.isPlaying()) {
            initMediaPlayer(); // 初始化MediaPlayer
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz_music);

        stop();

    }

    private void stop() {

        stop = findViewById(R.id.b_stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                stop.setEnabled(false);
                Intent intent = new Intent(dzMusicActivity.this, MainActivity.class);
                //startActivity(intent);
                //Intent intent = new Intent();
                // 为Intent设置Action、Category属性
                intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
                intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
                startActivity(intent);


            }
        });

    }

    private void initMediaPlayer() {
        if (mp.isPlaying() == false) {
            mp.reset();
            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.music);
            try {
                mp.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                        file.getLength());
                mp.prepare();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mp.setVolume(0.5f, 1.0f);
            mp.setLooping(true);
        }

        Log.i("警告", "语音");
//        if (!mp. isPlaying()){
//            mp.start();
//        }
        mp.start();


    }

    //警用返回
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}





