package com.example.bimov.dagger2.util;

import android.view.View;

import com.example.bimov.dagger2.model.Product;

/**
 * Created by BimoV on 3/7/2017.
 */

public interface ItemClickListener {
    void onClick(View view, int position, Product product);
}
