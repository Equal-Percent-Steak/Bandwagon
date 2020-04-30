package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateGroup extends MainActivity {

    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
    }

    public void onClick(View v){
        EditText groupName = (EditText) findViewById(R.id.groupName);
        EditText description = (EditText) findViewById(R.id.groupDescription);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference groups = database.getReference("groups");

        group = new Group(groupName.getText().toString(), MainActivity.getUser());

        groups.child(groupName.getText().toString()).child("group_name").setValue(groupName.getText().toString());
        groups.child(groupName.getText().toString()).child("description").setValue(description.getText().toString());
        groups.child(groupName.getText().toString()).child("members").setValue(group.getUsers());
        performReturnHome();
    }

    public void performExitCreateNewGroup(View v){
        Intent intent = new Intent(this, CreateNewTaskOrGroupMenu.class);
        startActivity(intent);
    }

    public void performReturnHome() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
