package com.equalpercentsteak.bandwagon;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AccountSettings extends MainActivity {

    /**
     * Creates the Account Settings page
     * @param savedInstanceState the saved Account Settings page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        TextView displayName = findViewById(R.id.displayNameShown);
        TextView email = findViewById(R.id.emailShown);

        displayName.setText(MainActivity.getUser().getId());
        email.setText(MainActivity.getUser().getUsername());
    }

//    public void performEnterNewGroup(View v){
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
//    }

    /**
     * Changes the activity to the main activity
     * @param v The current activity
     */
    public void performReturnHome(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
