package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class AccountSettings extends MainActivity {

    /**
     * Starts the AccountSettings page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
    }

    /**
     * Creates the ChangePassword activity
     * @param v
     */
    public void performChangePassword(View v){
        Intent intent = new Intent(this, ChangePassword.class);
        startActivity(intent);
    }

    /**
     * Returns to the home page
     * @param v
     */
    public void performReturnHome(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
