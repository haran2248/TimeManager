package com.example.timemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView CourseRv;
    Button newCourseBtn;
    ArrayList<Course> courses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CourseRv=(RecyclerView)findViewById(R.id.coursesRV);
        newCourseBtn=(Button)findViewById(R.id.coursebtn);

        newCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openEditCourse=new Intent(MainActivity.this,EditCourse.class);
                openEditCourse.setAction(" NewEvent");
                startActivity(openEditCourse);

            }
        });

        CourseRv.setLayoutManager(new LinearLayoutManager(this));
        courses= new ArrayList<>();
        CourseAdapter adapter=new CourseAdapter(courses,this);
        CourseRv.setAdapter(adapter);
    }
}
