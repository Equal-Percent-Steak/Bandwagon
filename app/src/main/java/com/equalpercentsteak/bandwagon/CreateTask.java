package com.equalpercentsteak.bandwagon;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    final String[] dateArr = new String[1];
    final String[] timeArr = new String[1];
    private AppCompatTextView tvPicker;
    private ArrayList<String> list;
    private DatabaseReference mGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        //Read users groups to spinner from Group class
        final Spinner groupMenu = (Spinner) findViewById(R.id.groupChoice);

        mGroups = FirebaseDatabase.getInstance().getReference().child("groups");
        mGroups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<String>();
                for(DataSnapshot groups: dataSnapshot.getChildren())
                {
                    String g = groups.child("group_name").getValue().toString();
                    list.add(g);
                }
                ArrayAdapter<String> groupMenuAdapter = new ArrayAdapter<String>(CreateTask.this,
                        android.R.layout.simple_list_item_1, list);
                groupMenuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                groupMenu.setAdapter(groupMenuAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CreateTask.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

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
                    public void onDateSet(DatePicker view, int yr, int monthOfYear, int dayOfMonth) {
                        String day = dayOfMonth + "";
                        String month = monthOfYear + 1 + "";
                        String year = yr + "";

                        if (day.length() == 1){
                            day = "0" + day;
                        }

                        if (month.length() == 1){
                            month = "0" + month;
                        }

                        if (year.length() == 3){
                            year = "0" + year;
                        }
                        dateArr[0] = "" + year + month + day;
                        dateButton.setText(year + "/" + month + "/" + day);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });


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
                                String hour = sHour + "";
                                String minute = sMinute + "";
                                if (hour.length() == 1){
                                    hour = "0" + hour;
                                }

                                if (minute.length() == 1){
                                    minute = "0" + minute;
                                }
                                timeArr[0] = "" + hour + minute;
                                timeButton.setText(hour + ":" + minute);
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });

    }

    public void addTaskToFB(View v) {

        EditText enterTask = findViewById(R.id.enterTask);
        EditText enterDetails = findViewById(R.id.enterDetails);
        Spinner groupChoice = findViewById(R.id.groupChoice);

        if( TextUtils.isEmpty(enterTask.getText())){
            Toast.makeText(this, "Please enter a task name", Toast.LENGTH_SHORT).show();
            enterTask.setError( "Task name must be entered" );
        }

        else if(groupChoice.getSelectedItem()==null){
            Toast.makeText(this, "Please select a group", Toast.LENGTH_SHORT).show();
            TextView errorText = (TextView)groupChoice.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Group must be selected");
        }

        else if(dateArr[0]==null) {
            Toast.makeText(this, "Please select a due date", Toast.LENGTH_SHORT).show();
            dateButton.setError("Due date must be selected");
        }

        else if(timeArr[0]==null) {
            Toast.makeText(this, "Please select a due time", Toast.LENGTH_SHORT).show();
            timeButton.setError("Due time must be selected");
        }

        else if( TextUtils.isEmpty(enterDetails.getText())){
            Toast.makeText(this, "Please enter task details", Toast.LENGTH_SHORT).show();
            enterDetails.setError( "Task details must be entered" );
        }

        else {

            EditText taskName = findViewById(R.id.enterTask);
            //EditText dueDate = findViewById(R.id.datePicker);
            EditText description = findViewById(R.id.enterDetails);

            date = dateArr[0];
            time = timeArr[0];

            Assignment a = new Assignment();
            a.setTitle(taskName.getText().toString());
            a.setDescription(description.getText().toString());
            a.setDate(date);
            a.setTime(time);
            Group g = new Group();
            g.setName(groupChoice.getSelectedItem().toString());
            a.setGroup(groupChoice.getSelectedItem().toString());

            int itemCount = myAdapter.getItemCount();
            myAdapter.notifyItemInserted(itemCount);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference groups = database.getReference("groups");

            groups.child(groupChoice.getSelectedItem().toString()).child("assignments").child(taskName.getText().toString()).setValue(a);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    public void performExitCreateNewTask(View v){
        Intent intent = new Intent(this, CreateNewTaskOrGroupMenu.class);
        startActivity(intent);
    }

    public void performReturnHome(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


//    private void showGroupMenu(){
//        final GroupDropdownMenu menu = new GroupDropdownMenu(this);
//        menu.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        menu.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
//        menu.setOutsideTouchable(true);
//        menu.setFocusable(true);
//        menu.showAsDropDown(tvPicker);
//        menu.setGroupSelectedListener(new GroupDropdownAdapter.GroupSelectedListener() {
//            @Override
//            public void onGroupSelected(int position, Group group) {
//                menu.dismiss();
//                Toast.makeText(CreateTask.this, "Group selected is: " + group.getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

}
