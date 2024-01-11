package com.example.bigwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static int chounum = 1;
    public static int fourchounum = 1;
    public static String[] fivarray = {"七七", "琴", "莫娜", "刻晴", "迪希亚", "迪卢克"};
    public static String[] fivaweapon = {"和璞鸢", "狼末", "天空之卷", "天空之脊"};
    public static String[] upfivarray = {"神里凌华", "申鹤"};
    public static int wai = 0;
    public static String[] fourarray = {"香菱", "罗莎莉亚", "菲谢尔", "班尼特"};
    public static String[] fourweapon = {"祭礼弓", "祭礼剑", "绝弦", "流浪乐章", "西风剑", "西风长枪", "西风猎弓", "西风秘典", "西福斯的月光"};
    public static String[] upfourarray = {"米卡", "迪奥娜", "砂糖"};
    public static String[] sanarray = {"安铁剑", "反曲弓", "黑缨枪", "猎弓", "黎明神剑", "魔道绪论", "白缨枪", "讨龙英雄坛", "鸦羽弓", "钺矛"};
    private List<Record> RecordList = new ArrayList<>();
    public static String nowcard = "申鹤卡池";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView cards1 = (ImageView) findViewById(R.id.cards1);
        ImageView cards2 = (ImageView) findViewById(R.id.cards2);
        ImageView bigcards = (ImageView) findViewById(R.id.bigcards);
        Button tenchou = (Button) findViewById(R.id.tenchou);
        Button onechou = (Button) findViewById(R.id.onechou);
        cards1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigcards.setImageResource(R.drawable.cards1);
                nowcard = "申鹤卡池";
            }
        });
        cards2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigcards.setImageResource(R.drawable.cards2);
                nowcard = "神里凌华卡池";
            }
        });
        tenchou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int state = 3;
                RecordList.clear();
                Intent intent = new Intent(MainActivity.this, choukaing.class);


                for (int i = 1; i <= 10; i++) {
                    int nowstate = chouone();
                    Log.d("nowstate", String.valueOf(nowstate));
                    if (nowstate < state) {
                        state = nowstate;
                    }
                    if (nowcard != "常驻池") {
                        chouup(nowstate);
                    }

                }
                if(state==1){
                    intent.putExtra("state", 5);
                }else{
                    intent.putExtra("state", 4);
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable("RecordList", (ArrayList<Record>) RecordList);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        onechou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecordList.clear();
                Intent intent = new Intent(MainActivity.this, choukaing.class);

                int state = chouone();
                if (nowcard != "常驻池") {
                    chouup(state);
                }

                intent.putExtra("state", state);
                Bundle bundle = new Bundle();
                bundle.putSerializable("RecordList", (ArrayList<Record>) RecordList);
                intent.putExtras(bundle);
                startActivity(intent);

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

    public void chouup(int state) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // 注意月份是从0开始计数的，所以需要加1
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        if (state == 1) {
            int min = 1; // 最小值（包含）
            int max = 10; // 最大值（包含）
            // 创建 Random 对象
            Random random = new Random();
            // 使用 nextInt 方法生成随机整数，范围为 [min, max]
            int randomNumber = random.nextInt(max - min + 1) + min;
            if (randomNumber <= 5) {
                wai = 1;
                int min1 = 0; // 最小值（包含）
                int max1 = fivarray.length - 1; // 最大值（包含）
                // 创建 Random 对象
                Random random1 = new Random();
                // 使用 nextInt 方法生成随机整数，范围为 [min, max]
                int randomNumber1 = random1.nextInt(max1 - min1 + 1) + min1;
                Record Recorditem = new Record("角色", fivarray[randomNumber1], nowcard, time, "5");
                RecordList.add(Recorditem);
            } else {
                wai = 0;
                int min1 = 0; // 最小值（包含）
                int max1 = upfivarray.length - 1; // 最大值（包含）
                // 创建 Random 对象
                Random random1 = new Random();
                // 使用 nextInt 方法生成随机整数，范围为 [min, max]
                int randomNumber1 = random.nextInt(max1 - min1 + 1) + min1;
                Record Recorditem = new Record("角色", upfivarray[randomNumber1], nowcard, time, "5");
                RecordList.add(Recorditem);
            }
        } else if (state == 2) {
            int min = 1; // 最小值（包含）
            int max = 10; // 最大值（包含）
            // 创建 Random 对象
            Random random = new Random();
            // 使用 nextInt 方法生成随机整数，范围为 [min, max]
            int randomNumber = random.nextInt(max - min + 1) + min;
            if (randomNumber <= 3) {
                int min1 = 0; // 最小值（包含）
                int max1 = fourarray.length - 1 + fourweapon.length - 1; // 最大值（包含）
                // 创建 Random 对象
                Random random1 = new Random();
                // 使用 nextInt 方法生成随机整数，范围为 [min, max]
                int randomNumber1 = random.nextInt(max1 - min1 + 1) + min1;
                if (randomNumber1 <= fourarray.length - 1) {
                    Record Recorditem = new Record("角色", fourarray[randomNumber1], nowcard, time, "4");
                    RecordList.add(Recorditem);
                } else {
                    Record Recorditem = new Record("武器", fourweapon[randomNumber1 - fourarray.length + 1], nowcard, time, "4");
                    RecordList.add(Recorditem);
                }

            } else {
                int min1 = 0; // 最小值（包含）
                int max1 = upfourarray.length - 1; // 最大值（包含）
                // 创建 Random 对象
                Random random1 = new Random();
                // 使用 nextInt 方法生成随机整数，范围为 [min, max]
                int randomNumber1 = random.nextInt(max1 - min1 + 1) + min1;
                Record Recorditem = new Record("角色", fourarray[randomNumber1], nowcard, time, "4");
                RecordList.add(Recorditem);
            }
        } else {
            int min1 = 0; // 最小值（包含）
            int max1 = sanarray.length - 1; // 最大值（包含）
            // 创建 Random 对象
            Random random = new Random();
            // 使用 nextInt 方法生成随机整数，范围为 [min, max]
            int randomNumber1 = random.nextInt(max1 - min1 + 1) + min1;
            Log.d("randomNumber1", String.valueOf(randomNumber1));
            Record Recorditem = new Record("武器", sanarray[randomNumber1], nowcard, time, "3");
            RecordList.add(Recorditem);
        }
    }

    public int chouone() {
        int fiveprob = 6;
        int fourprob = 51;
        int min = 1; // 最小值（包含）
        int max = 1000; // 最大值（包含）
        // 创建 Random 对象
        Random random = new Random();
        // 使用 nextInt 方法生成随机整数，范围为 [min, max]
        int randomNumber = random.nextInt(max - min + 1) + min;
        Log.d("allin", String.valueOf(randomNumber));
        if (fourchounum == 9) {
            fourprob = 561;
        } else if (fourchounum == 10) {
            fourprob = 1000;
        }

        if (chounum >= 74) {
            fiveprob = (chounum - 73) * 60 + 6;
        }
        if (randomNumber <= fiveprob) {
            chounum = 1;
            fourchounum++;
            return 1;
        } else if (randomNumber <= fourprob) {
            fourchounum = 1;
            chounum++;
            return 2;
        } else {
            Log.d("allin", String.valueOf(chounum));
            fourchounum = fourchounum + 1;
            chounum = chounum + 1;
            return 3;
        }

    }
}