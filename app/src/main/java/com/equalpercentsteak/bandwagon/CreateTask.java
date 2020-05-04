package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateTask extends MainActivity {

    FirebaseFirestore db;
    final String TAG = "CreateTask";
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private EditText dateButton;
    private EditText timeButton;
    private String time;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        Spinner groupMenu = (Spinner) findViewById(R.id.groupChoice);

        ArrayAdapter<String> groupMenuAdapter = new ArrayAdapter<String>(CreateTask.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(
                R.array.dropdownNames));
        groupMenuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupMenu.setAdapter(groupMenuAdapter);
        initFirestore();
        final String[] dateArr = new String[1];
        final String[] timeArr = new String[1];
        dateButton = findViewById(R.id.datePicker);
        dateButton.setInputType(InputType.TYPE_NULL);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(CreateTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateButton.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, year, month, day);
                datePicker.show();
                dateArr[0] ="" + day + month + year;
            }
        });
        date = dateArr[0];
//        initFirestore();
        timeButton = findViewById(R.id.timePicker);
        timeButton.setInputType(InputType.TYPE_NULL);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                timePicker = new TimePickerDialog(CreateTask.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                timeButton.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePicker.show();
                timeArr[0] = "" + hour + minutes;
            }
        });
        time = timeArr[0];
    }



    private void initFirestore(){
        db = FirebaseFirestore.getInstance();
        DocumentReference dr = db.collection("groups").document("0KouSLYueZjr8L5lzyaA");
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "Get failed with ", task.getException());
                }
            }
        });

    }

    public void addTaskToFB(View v) {
        EditText taskName = findViewById(R.id.enterTask);
        //EditText dueDate = findViewById(R.id.datePicker);
        Spinner groupChoice = findViewById(R.id.groupChoice);
        EditText description = findViewById(R.id.enterDetails);


        Assignment a = new Assignment();
        a.setTitle(taskName.getText().toString());
        a.setDescription(description.getText().toString());
//        a.setImg(R.drawable.ic_android_black_24dp);
        int itemCount = myAdapter.getItemCount();
        myAdapter.notifyItemInserted(itemCount);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference groups = database.getReference("groups");
        groups.child(groupChoice.getSelectedItem().toString()).child("assignments").child(taskName.getText().toString()).setValue(a);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void performExitCreateNewTask(View v){
        Intent intent = new Intent(this, CreateNewTaskOrGroupMenu.class);
        startActivity(intent);
    }

    public void performReturnHome(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
