package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }

    public void onClick(View v){
        EditText usernameInput = (EditText) findViewById(R.id.username);
        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("users");
//TODO:Check for duplicate users
        users.child(usernameInput.getText().toString()).child("username").setValue(usernameInput.getText().toString());
        users.child(usernameInput.getText().toString()).child("first_name").setValue(firstName.getText().toString());
        users.child(usernameInput.getText().toString()).child("last_name").setValue(lastName.getText().toString());
    }
}
