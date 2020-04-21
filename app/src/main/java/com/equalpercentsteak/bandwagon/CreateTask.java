package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateTask extends MainActivity {

    FirebaseFirestore db;
    final String TAG = "CreateTask";


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
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    public void addTaskToFB(View v) {
        EditText taskName = findViewById(R.id.enterTask);
        //EditText dueDate = findViewById(R.id.datePicker);
        Spinner groupChoice = findViewById(R.id.groupChoice);
        EditText description = findViewById(R.id.enterDetails);

        Map<String, Object> task = new HashMap<>();
        task.put("task_name",taskName.getText().toString());
        //task.put("due_date",dueDate.getText().toString());
        task.put("group",groupChoice.getSelectedItem().toString());
        task.put("description",description.getText().toString());

        db.collection("groups").add(task);

        Assignment a = new Assignment();
        a.setTitle(taskName.getText().toString());
        a.setDescription(description.getText().toString());
        a.setImg(R.drawable.ic_android_black_24dp);
        assignments.add(a);

        int itemCount = myAdapter.getItemCount();
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
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
