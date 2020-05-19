package com.equalpercentsteak.bandwagon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

/**
 * This class corresponds to the Change Password screen that can be opened from the Settings page
 */
public class ChangePassword extends MainActivity {

    private Bundle savedInstanceState;

    /**
     * Creates the activity to change the user's password
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    /**
     * Changes the screen to the account settings screen
     * @param v the button clicked which changes the view
     */
    public void performReturnSettings(View v) {
        Intent intent = new Intent(this,AccountSettings.class);
        startActivity(intent);
    }

}
