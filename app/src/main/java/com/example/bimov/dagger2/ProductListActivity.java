package com.example.bimov.dagger2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.bimov.dagger2.adapter.ProductListAdapter;
import com.example.bimov.dagger2.application.ApplicationShop;
import com.example.bimov.dagger2.model.Product;
import com.example.bimov.dagger2.mvp.ProductListPresenter;
import com.example.bimov.dagger2.mvp.ProductListView;
import com.example.bimov.dagger2.util.ItemClickListener;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BimoV on 1/31/2017.
 */

public class ProductListActivity extends MvpActivity<ProductListView,ProductListPresenter> implements ProductListView , ItemClickListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtCountCart)
    TextView txtCountCart;

    private ProductListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Inject
    ShoppingCart mCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        ApplicationShop.getInstance().getAppComponent().inject(ProductListActivity.this);

        initToolbar();
        initRecyclerView();
        setData(presenter.cartValue());

    }

    private void setData(String value) {
        txtCountCart.setText(value);
    }

    @NonNull
    @Override
    public ProductListPresenter createPresenter() {
        return new ProductListPresenter();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Product");
    }

    private void initRecyclerView() {
        recyclerview.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter = new ProductListAdapter(mCart.showItemList(), getApplicationContext());
        mAdapter.setClickListener(this);
        recyclerview.setAdapter(mAdapter);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View view, int position, final Product product) {

        new AlertDialog.Builder(this)
                .setMessage("Do you want add to cart?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onAddItemToCart(product);
                        setData(presenter.cartValue());
                        //Toast.makeText(getApplicationContext(), product.getProductName(), Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();


    }
}
