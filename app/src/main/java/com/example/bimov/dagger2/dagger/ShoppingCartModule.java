package com.example.bimov.dagger2.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.bimov.dagger2.MainActivity;
import com.example.bimov.dagger2.ShoppingCart;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by BimoV on 1/30/2017.
 */
@Module
public class ShoppingCartModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    ShoppingCart providesShoppingCart(SharedPreferences preferences) {
        return new ShoppingCart(preferences);
    }
}
