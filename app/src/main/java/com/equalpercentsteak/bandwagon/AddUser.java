package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddUser extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Spinner groupMenu = (Spinner) findViewById(R.id.groupChoice);
        ArrayList<Group> groups = Group.createGroupsList();
        ArrayList<String> groupNames= new ArrayList<String>();
        for(int i = 0; i <groups.size(); i++){
            String group = groups.get(i).getName();
            groupNames.add(group);
        }
        ArrayAdapter<String> groupMenuAdapter = new ArrayAdapter<String>(AddUser.this,
                android.R.layout.simple_list_item_1, groupNames);
        groupMenuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupMenu.setAdapter(groupMenuAdapter);
    }

    public void onClick(View v){
        EditText usernameInput = (EditText) findViewById(R.id.username);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("users");
//TODO:Add members to the correct group
        users.child(usernameInput.getText().toString()).child("username").setValue(usernameInput.getText().toString());
    }
}
