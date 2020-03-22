package com.example.timemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import io.realm.Realm;

public class EditCourse extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Button startbtn,stopbtn,done,cancel;
    RadioButton prod,unprod,relax,other;
    EditText describe;
    String description,start_time,end_time;
    TextView start_tv;
    TextView stop_tv;
    String time;


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        start_tv=findViewById(R.id.startview);
        start_tv.setText("STARTING TIME"+hourOfDay+minute);
        stop_tv=findViewById(R.id.endView);
        stop_tv.setText("STOP TIME:"+hourOfDay+minute);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        done=findViewById(R.id.done);
        cancel=findViewById(R.id.cancel);
        startbtn=findViewById(R.id.startbutton);
        stopbtn=findViewById(R.id.stopbutton);
        prod=findViewById(R.id.prod);
        unprod=findViewById(R.id.unpro);
        relax=findViewById(R.id.Relaxation);
        other=findViewById(R.id.Other);
        describe=findViewById(R.id.Description);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment=new TimePickerFragment();
                dialogFragment.show(getSupportFragmentManager(),"time picker");
            }
        });
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment=new TimePickerFragment();
                dialogFragment.show(getSupportFragmentManager(),"time picker");
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                description=describe.getText().toString();
                start_time=start_tv.getText().toString();
                end_time=stop_tv.getText().toString();
                time=start_time+"to"+end_time;

                Course course=new Course(description,time);
                Realm realm=Realm.getDefaultInstance();
                if(getIntent().getAction().equals("NewEvent")){
                    realm.beginTransaction();
                    realm.insertOrUpdate(course);
                    realm.commitTransaction();

                    Intent openMain=new Intent(EditCourse.this,MainActivity.class);
                    openMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(openMain);
                    finish();
                    Toast.makeText(EditCourse.this,"AFTER FINISH",Toast.LENGTH_SHORT).show();
                    }


                }




        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
