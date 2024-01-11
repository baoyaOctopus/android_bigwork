package com.example.bigwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.List;

public class chickimg extends AppCompatActivity {
    public static List<Record> recordList;
    public static int nowkey = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nowkey = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chickimg);
        Intent intent = getIntent();
        int state = intent.getIntExtra("state", 0);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            recordList = (List<Record>) bundle.getSerializable("RecordList");
            // 对state对象和recordList列表进行操作
        }
        for (int i = 0; i < recordList.size(); i++) {
            Log.d("recordList", dealHanzi(recordList.get(0).getResult()));
        }
        ImageView chilckimgview = (ImageView) findViewById(R.id.chilckimgview);

        setimg(0);

        chilckimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowkey++;
                Log.d("resourceName", String.valueOf(nowkey));
                if (nowkey < recordList.size()) {
                    setimg(nowkey);
                } else {
                    Intent intent = new Intent(chickimg.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    public String dealHanzi(String Hanzi) {
        StringBuilder pinyinStringBuilder = new StringBuilder();
        for (char c : Hanzi.toCharArray()) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
            if (pinyinArray != null && pinyinArray.length > 0) {
                pinyinStringBuilder.append(pinyinArray[0]);

            } else {
                pinyinStringBuilder.append(c);
            }
        }
        String pinyinString = pinyinStringBuilder.toString();
        pinyinString = pinyinString.replaceAll("\\d", ""); // 使用正则表达式替换所有数字为空字符串
        return pinyinString;
    }

    public void setimg(int key) {

        ImageView chilckimgview = (ImageView) findViewById(R.id.chilckimgview);
        String resourceName = null;
        if (recordList.get(key).getLevel().equals("3")) {
            resourceName = "sanxing_" + dealHanzi(recordList.get(key).getResult());
            int resId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
            chilckimgview.setImageResource(resId);
        } else if (recordList.get(key).getLevel().equals("4")) {
            if (recordList.get(key).getType().equals("武器")) {
                resourceName = "sixing_" + dealHanzi(recordList.get(key).getResult());
                int resId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
                chilckimgview.setImageResource(resId);
            } else {
                resourceName = "sixing_" + dealHanzi(recordList.get(key).getResult() )+ "1";
                int resId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
                chilckimgview.setImageResource(resId);
            }


        } else if (recordList.get(key).getLevel().equals("5")) {
            if (recordList.get(key).getType().equals("武器")) {
                resourceName = "wuxing_" + dealHanzi(recordList.get(key).getResult());
                int resId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
                chilckimgview.setImageResource(resId);
            } else {
                resourceName = "wuxing_" + dealHanzi(recordList.get(key).getResult() )+ "1";
                int resId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
                chilckimgview.setImageResource(resId);
            }
        }
        Log.d("resourceName", resourceName);
    }
}