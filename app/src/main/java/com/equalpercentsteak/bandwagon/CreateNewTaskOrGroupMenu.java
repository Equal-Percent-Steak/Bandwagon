package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This class corresponds to the add button page which is not currently implemented in our app.
 */
public class CreateNewTaskOrGroupMenu extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_task_or_group_menu);
    }

    public void performCreateNewTask(View v){
        Intent intent = new Intent(this, CreateTask.class);
        startActivity(intent);
    }

    public void performCreateNewGroup(View v){
        Intent intent = new Intent(this,CreateGroup.class);
        startActivity(intent);
    }
}
