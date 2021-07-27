package com.pujiy.mywarehouse.exception;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.pujiy.mywarehouse.R;

public class HandlerActivity extends AppCompatActivity {

    private String problemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        Intent intent = getIntent();
        problemDescription = intent.getStringExtra("error");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}