package com.example.bimov.dagger2.mvp;

import android.util.Log;

import com.example.bimov.dagger2.ShoppingCart;
import com.example.bimov.dagger2.application.ApplicationShop;
import com.example.bimov.dagger2.model.Product;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by BimoV on 2/28/2017.
 */

public class ProductListPresenter extends MvpBasePresenter<ProductListView> {

    @Inject
    ShoppingCart mCart;

    public ProductListPresenter() {
        ApplicationShop.getInstance().getAppComponent().inject(this);
    }

    public void onAddItemToCart(Product product) {
        mCart.addItemToCart(product);
        Log.d("CartSize",mCart.getShoppingCart().size()+"");

    }

    public String cartValue(){
        return String.valueOf(mCart.getShoppingCart().size());
    }
}
