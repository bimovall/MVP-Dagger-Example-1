package com.example.bimov.dagger2.dagger;

import com.example.bimov.dagger2.ProductListActivity;
import com.example.bimov.dagger2.mvp.ProductListPresenter;
import com.example.bimov.dagger2.mvp.ProductPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by BimoV on 1/30/2017.
 */
@Singleton
@Component(modules = {AppModule.class, ShoppingCartModule.class})
public interface AppComponent {
    void inject(ProductPresenter listener);

    void inject(ProductListActivity activity);

    void inject(ProductListPresenter listPresenter);

}
