package com.example.bimov.dagger2.mvp;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by BimoV on 2/28/2017.
 */

public interface ProductListView extends MvpView {

    void showLoading();

    void hideLoading();

}
