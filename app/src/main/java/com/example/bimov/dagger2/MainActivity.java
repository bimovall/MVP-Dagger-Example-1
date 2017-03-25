package com.example.bimov.dagger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnProductList)
    Button btnProductList;
    @BindView(R.id.btnAddProduct)
    Button btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btnProductList, R.id.btnAddProduct})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnProductList:

                Intent i = new Intent(getApplicationContext(), ProductListActivity.class);
                startActivity(i);
                break;
            case R.id.btnAddProduct:
                Intent intent = new Intent(getApplicationContext(), AddProductActivity.class);
                startActivity(intent);
                break;
        }
    }
}
