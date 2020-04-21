package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }
    public void performReturnSettings(View v) {
        Intent intent = new Intent(this,accountSettings.class);
        startActivity(intent);
    }
}
