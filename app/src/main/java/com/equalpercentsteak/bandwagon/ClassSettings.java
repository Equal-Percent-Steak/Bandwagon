package com.equalpercentsteak.bandwagon;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This is the class for the Class Settings page that is not currently implemented in our app.
 */
public class ClassSettings extends MainActivity {

    /**
     * Creates the Class Settings page
     * @param savedInstanceState the saved Class Settings page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_settings);
    }

    /**
     * Switches the view to the Main Activity upon the click of the view object
     * @param v the object to be clicked
     */
    public void performReturnHome(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
