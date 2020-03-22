package com.example.timemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseVH> {
    ArrayList<Course> courseArrayList;
    Context context;
    Realm realm;

    public CourseAdapter(ArrayList<Course> courseArrayList, Context context) {
        this.courseArrayList = courseArrayList;
        this.context = context;
        realm=Realm.getDefaultInstance();
        loadCourses();
    }




    void loadCourses()
    {
        RealmQuery<Course> realmQuery=realm.where(Course.class);
        RealmResults<Course> realmResults=realmQuery.findAll();
        courseArrayList.addAll(realmResults);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseVH holder, int position) {
        holder.populateCourse(courseArrayList.get(position));

    }



    @Override
    public int getItemCount() {
        return courseArrayList.size();
    }

    public class CourseVH extends RecyclerView.ViewHolder {
        Button courseBtn;
        public CourseVH(View itemView) {
            super(itemView);
            courseBtn=itemView.findViewById(R.id.courseBtn);
        }

        public void populateCourse(final Course course) {
            courseBtn.setText(course.getEvent()+""+course.getTime());
            courseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent load=new Intent(context,TasksActivity.class);
                    load.putExtra("event",course.getEvent());
                    itemView.getContext().startActivity(load);
                }
            });
            courseBtn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(context).setTitle("Warning").setMessage("DO YOU WANT TO DELETE THIS EVENT?").setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Realm realm=Realm.getDefaultInstance();
                            String eventName=course.getEvent();
                            RealmResults<Course> realmResults=realm.where(Course.class).equalTo("EventName",eventName).findAll();
                            courseArrayList.removeAll(realmResults);
                            realm.beginTransaction();
                            realmResults.deleteAllFromRealm();
                            realm.commitTransaction();
                            notifyDataSetChanged();
                            Toast.makeText(context,"User clicked delete",Toast.LENGTH_SHORT).show();


                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context,"User clicked Cancel",Toast.LENGTH_SHORT);

                        }
                    }).show();
                    return true;
                }
            });
        }
    }
}
