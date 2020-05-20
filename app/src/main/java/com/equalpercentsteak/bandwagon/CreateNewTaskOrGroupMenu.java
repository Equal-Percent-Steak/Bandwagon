package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This class corresponds to the add button page which is not currently implemented in our app.
 */
public class CreateNewTaskOrGroupMenu extends MainActivity {

    /**
     * Creates a new instance of the add task or group screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_task_or_group_menu);
    }

    /**
     * changes the screen to Create new Task
     * @param v the button or item that is clicked
     */
    public void performCreateNewTask(View v){
        Intent intent = new Intent(this, CreateTask.class);
        startActivity(intent);
    }

    /**
     * changes the screen to Create New Group
     * @param v the button or item that is clicked
     */
    public void performCreateNewGroup(View v){
        Intent intent = new Intent(this,CreateGroup.class);
        startActivity(intent);
    }
}
