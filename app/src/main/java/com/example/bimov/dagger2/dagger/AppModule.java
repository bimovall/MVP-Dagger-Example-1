package com.example.bimov.dagger2.dagger;

import android.content.Context;

import com.example.bimov.dagger2.application.ApplicationShop;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by BimoV on 1/30/2017.
 */
@Module
public class AppModule {
    private ApplicationShop app;

    public AppModule(ApplicationShop app){
        this.app = app;
    }

    @Provides @Singleton
    public Context provideContext(){
        return app;
    }
}
