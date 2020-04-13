package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class createNewTaskOrGroupMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_task_or_group_menu);
    }

    public void performCreateNewTask(View v){
        Intent intent = new Intent(this,createTask.class);
        startActivity(intent);
    }

    public void performCreateNewGroup(View v){
        Intent intent = new Intent(this,createGroup.class);
        startActivity(intent);
    }
}
