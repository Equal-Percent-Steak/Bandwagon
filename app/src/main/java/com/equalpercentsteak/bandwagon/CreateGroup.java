package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.equalpercentsteak.bandwagon.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateGroup extends AppCompatActivity {

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

        groups.child(groupName.getText().toString()).child("group_name").setValue(groupName.getText().toString());
        groups.child(groupName.getText().toString()).child("first_name").setValue(description.getText().toString());
    }

    public void performExitCreateNewGroup(View v){
        Intent intent = new Intent(this,createNewTaskOrGroupMenu.class);
        startActivity(intent);
    }
}
