package com.example.bimov.dagger2.mvp;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by BimoV on 1/31/2017.
 */

public interface ProductView extends MvpView {

    void showErrorMessage(String message);

    void showSuccessMessage (String message);

    void showEmptyMessage(String message);

    void showProductNameEmptyMessage(String message);

    void showDescEmptyMessage(String message);

    void showQuantityEmptyMessage(String message);

    void showPriceEmptyMessage(String message);

    void showListItems();

    void showLoading();

    void hideErrorMessage();

    void hideLoading();
}
