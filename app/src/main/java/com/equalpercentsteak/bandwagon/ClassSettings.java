package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ClassSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_settings);
    }

    public void performReturnHome(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
