package com.example.timemanager;
import io.realm.RealmObject;

public class Course extends RealmObject
{
    private String Event;
    private String time;

    public Course() {
    }

    public Course(String event, String time) {
        Event = event;
        this.time = time;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
