package com.example.bimov.dagger2.mvp;

import android.text.TextUtils;
import android.util.Log;

import com.example.bimov.dagger2.ShoppingCart;
import com.example.bimov.dagger2.application.ApplicationShop;
import com.example.bimov.dagger2.model.Product;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by BimoV on 1/30/2017.
 */
public class ProductPresenter extends MvpBasePresenter<ProductView> {

    @Inject
    ShoppingCart mCart;

    public ProductPresenter() {
        ApplicationShop.getInstance().getAppComponent().inject(this);
    }

    public void checkData(String productName, String productDesc, String productQty, String productSalePrice) {
        boolean valid = true;
        if (isViewAttached()) {
            getView().showLoading();
        }
        if (TextUtils.isEmpty(productName) && TextUtils.isEmpty(productDesc) &&
                TextUtils.isEmpty(productQty) && TextUtils.isEmpty(productSalePrice)) {
            getView().showEmptyMessage("Fill the blank form");
            valid = false;
        }
        if (TextUtils.isEmpty(productName)) {
            getView().showProductNameEmptyMessage("Name cannot be empty");
            valid = false;
        }
        if (TextUtils.isEmpty(productDesc)) {
            getView().showDescEmptyMessage("Description cannot be empty");
            valid = false;
        }
        if (TextUtils.isEmpty(productQty)) {
            getView().showQuantityEmptyMessage("Quantity cannot be empty");
            valid = false;
        }
        if (TextUtils.isEmpty(productSalePrice)) {
            getView().showPriceEmptyMessage("Price cannot be empty");
            valid = false;
        }
        if (valid) {
            getView().hideErrorMessage();

            Product product = new Product();
            product.setId(mCart.showItemList().size()+1);
            product.setProductName(productName);
            product.setDescription(productDesc);
            product.setQuantity(Integer.parseInt(productQty));
            product.setSalePrice(Double.parseDouble(productSalePrice));

            mCart.addItemToCart(product);
            mCart.saveCartToPreference();

            if (mCart.showItemList().get(mCart.showItemList().size()-1).getProductName().equals(product.getProductName())){
                getView().showErrorMessage("Item Already Existed");
            }else{
                mCart.addItemList(product);
            }
        }

        if (isViewAttached()) {
            getView().hideLoading();
        }

    }

    /*public void onAddItem(List<Product> products) {
        mCart.addItemList(products);
        getView().showSuccessMessage("Success");
    }*/

    public void showListItems() {
        for (int i = 0; i < mCart.showItemList().size(); i++) {
            Product product = mCart.showItemList().get(i);
            Log.d("DescProd", product.getProductName());
            mCart.showItemList();

        }
        Log.d("Desc", String.valueOf(mCart.showItemList().size()));

        for (Product product : mCart.getShoppingCart()) {
            product.getQuantity();
            Log.d("quantity", String.valueOf(product.getQuantity()));
        }
        Log.d("quantity2", String.valueOf(mCart.getShoppingCart().size()));

        //getView().showListItems();
    }





  /*  public void onItemQuantityChanged(Product item, int qty){
        mCart.updateItemQty(item,qty);
    }

    public void onAddToCartButtonClicked(Product product){
        Product item = new Product(product,1);
        mCart.addItemToCart(item);
    }

    public void onClearButtonClicked(){
        mCart.clearShoppingCart();
    }

    public void onDeleteItemButtonClicked(Product item){
        mCart.removeItemFromCart(item);
    }*/
}
