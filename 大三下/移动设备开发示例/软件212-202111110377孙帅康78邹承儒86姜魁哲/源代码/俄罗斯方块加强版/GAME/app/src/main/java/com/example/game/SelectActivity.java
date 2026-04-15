package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 选择关卡活动
 * 实现了一个简单的选择界面，允许用户根据不同的等级选择进入
 */

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {

    Button grade1, grade2, grade3, grade4, grade5;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        grade1 = (Button) findViewById(R.id.grade1);
        grade2 = (Button) findViewById(R.id.grade2);
        grade3 = (Button) findViewById(R.id.grade3);
        grade4 = (Button) findViewById(R.id.grade4);
        grade5 = (Button) findViewById(R.id.grade5);

        grade1.setOnClickListener(this);
        grade2.setOnClickListener(this);
        grade3.setOnClickListener(this);
        grade4.setOnClickListener(this);
        grade5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SelectActivity.this, MainActivity.class);

        switch (v.getId()) {
            case R.id.grade1:
                startActivity(intent);
                break;
            case R.id.grade2:
                startActivity(intent);
                break;
            case R.id.grade3:
                startActivity(intent);
                break;
            case R.id.grade4:
                startActivity(intent);
                break;
            case R.id.grade5:
                startActivity(intent);
                break;
            default:
                break;
        }
//        startActivity(intent);
    }
}
