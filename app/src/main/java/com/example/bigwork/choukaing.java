package com.example.bigwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class choukaing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choukaing);
        Intent intent = getIntent();
        int state = intent.getIntExtra("state", 0);
        Log.d("recordList", String.valueOf(state));
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            List<Record> recordList = (List<Record>) bundle.getSerializable("RecordList");
            Log.d("recordList", recordList.get(0).toString());
            // 对state对象和recordList列表进行操作
        }
        VideoView videoView = findViewById(R.id.video_view);
        String videoPath = null;
        if(state==1){
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.choukaing1;
        }else if(state==2){
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.choukaing2;
        }else if(state==3){
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.choukaing3;
        }else if(state==4){
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.choukaing4;
        }else if(state==5){
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.choukaing5;
        }

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(choukaing.this, chickimg.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}