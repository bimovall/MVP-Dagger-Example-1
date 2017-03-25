package com.example.bimov.dagger2.application;

import android.app.Application;

import com.example.bimov.dagger2.dagger.AppComponent;
import com.example.bimov.dagger2.dagger.AppModule;
import com.example.bimov.dagger2.dagger.DaggerAppComponent;

/**
 * Created by BimoV on 1/30/2017.
 */

public class ApplicationShop extends Application {

    private static ApplicationShop instance = new ApplicationShop();
    private static AppComponent appComponent;

    public static ApplicationShop getInstance(){
        return instance;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

}
