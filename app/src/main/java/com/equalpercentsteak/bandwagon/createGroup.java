package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.equalpercentsteak.bandwagon.R;

public class createGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
    }

    public void performExitCreateNewGroup(View v){
        Intent intent = new Intent(this,createNewTaskOrGroupMenu.class);
        startActivity(intent);
    }
}
