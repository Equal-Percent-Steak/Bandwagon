package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CreateTask extends MainActivity {

    FirebaseFirestore db;
    final String TAG = "CreateTask";
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private EditText dateButton;
    private EditText timeButton;


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
            }
        });
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
            }
        });

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

//        Map<String, Object> task = new HashMap<>();
//        task.put("task_name",taskName.getText().toString());
//        //task.put("due_date",dueDate.getText().toString());
//        task.put("group",groupChoice.getSelectedItem().toString());
//        task.put("description",description.getText().toString());

//        db.collection("groups").add(task);

        Assignment a = new Assignment();
        a.setTitle(taskName.getText().toString());
        a.setDescription(description.getText().toString());
//        a.setImg(R.drawable.ic_android_black_24dp);
        int itemCount = myAdapter.getItemCount();
        assignments.add(a);
        myAdapter.notifyItemInserted(itemCount);

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
