package com.example.timemanager;

import android.app.Application;
import android.os.Build;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TimeManager extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder().build());

    }
}
