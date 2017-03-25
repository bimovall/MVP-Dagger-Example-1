package com.example.bimov.dagger2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bimov.dagger2.model.Product;
import com.example.bimov.dagger2.mvp.ProductPresenter;
import com.example.bimov.dagger2.mvp.ProductView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity extends MvpActivity<ProductView, ProductPresenter> implements ProductView {


    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_show)
    Button btnShow;
    @BindView(R.id.productName)
    EditText edtProductName;
    @BindView(R.id.productDescription)
    EditText edtProductDescription;
    @BindView(R.id.productSalePrice)
    EditText edtProductSalePrice;
    @BindView(R.id.productQuantity)
    EditText edtProductQuantity;

    private List<Product> products = new ArrayList<>();

    private String productName, productDesc, productQty, productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        /*webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://innodev.vnetcloud.com/cctv-client/?port=8091");*/

    }

    @NonNull
    @Override
    public ProductPresenter createPresenter() {
        return new ProductPresenter();
    }


    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProductNameEmptyMessage(String message) {
        edtProductName.setError(message);

    }

    @Override
    public void showDescEmptyMessage(String message) {
        edtProductDescription.setError(message);

    }

    @Override
    public void showQuantityEmptyMessage(String message) {
        edtProductQuantity.setError(message);

    }

    @Override
    public void showPriceEmptyMessage(String message) {
        edtProductSalePrice.setError(message);

    }

    @Override
    public void showListItems() {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideErrorMessage() {
        edtProductName.setError(null);
        edtProductDescription.setError(null);
        edtProductSalePrice.setError(null);
        edtProductQuantity.setError(null);
    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.btn_add, R.id.btn_show})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                productName = edtProductName.getText().toString().trim();
                productDesc = edtProductDescription.getText().toString().trim();
                productPrice = edtProductSalePrice.getText().toString().trim();
                productQty = edtProductQuantity.getText().toString().trim();

                presenter.checkData(productName, productDesc, productQty, productPrice);

                break;
            case R.id.btn_show:
                presenter.showListItems();
                break;
        }
    }
}
