package com.example.bimov.dagger2;

import android.content.SharedPreferences;

import com.example.bimov.dagger2.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BimoV on 1/30/2017.
 */

public class ShoppingCart {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<Product> shoppingCart;
    private List<Product> listProduct;
    private String cart;

    private static String OPEN_CART_EXIST = "open_cart_exists";
    private static String SERIALIZED_CART_ITEMS = "serialized_cart_items";
    private static String SERIALIZED_CUSTOMER = "serialized_customer";
    private static String SERIALIZED_ITEMS = "serialized_items";

    public ShoppingCart(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
        initShoppingCart();
    }

    private void initShoppingCart() {
        shoppingCart = new ArrayList<>();
        listProduct = new ArrayList<>();

        Gson gson = new Gson();

        String serializedCartItems = sharedPreferences.getString(SERIALIZED_CART_ITEMS, "");
        String serializedCustomer = sharedPreferences.getString(SERIALIZED_CUSTOMER, "");
        String serializedItems = sharedPreferences.getString(SERIALIZED_ITEMS, "");
        if (!serializedItems.equals("")) {
            listProduct = gson.<ArrayList<Product>>fromJson(serializedItems,
                    new TypeToken<ArrayList<Product>>() {
                    }.getType());
        }

        if (!serializedCartItems.equals("")) {
            shoppingCart = gson.<ArrayList<Product>>fromJson(serializedCartItems,
                    new TypeToken<ArrayList<Product>>() {
                    }.getType());
        }


//        updateApp();
    }

    public void addItemToCart(Product item) {
        if (shoppingCart.contains(item)) {
            int currentPosition = shoppingCart.indexOf(item);
            Product itemAlreadyInCart = shoppingCart.get(currentPosition);
            itemAlreadyInCart.setQuantity(itemAlreadyInCart.getQuantity() - item.getQuantity());
            shoppingCart.set(currentPosition, itemAlreadyInCart);
        } else {
            shoppingCart.add(item);
            Gson gson = new Gson();
            String serializedItems = gson.toJson(shoppingCart);
            editor.putString(SERIALIZED_CART_ITEMS, serializedItems).commit();
        }
    }

    private void updateApp() {

    }

    public void clearShoppingCart() {
        shoppingCart.clear();
        editor.putString(SERIALIZED_CART_ITEMS, "").commit();
        editor.putString(SERIALIZED_CUSTOMER, "").commit();
        editor.putString(OPEN_CART_EXIST, "").commit();
        updateApp();
    }

    public void removeItemFromCart(Product item) {
        shoppingCart.remove(item);
        updateApp();
    }

    public void completeCheckout() {
        shoppingCart.clear();
        updateApp();
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void saveCartToPreference() {
        if (shoppingCart != null) {
            Gson gson = new Gson();
            String serializedItems = gson.toJson(shoppingCart);
            editor.putString(SERIALIZED_CART_ITEMS, serializedItems).commit();
            editor.putBoolean(OPEN_CART_EXIST, true).commit();
        }
    }

    public void updateItemQty(Product item, int qty) {
        boolean itemAlreadyInCart = shoppingCart.contains(item);

        if (itemAlreadyInCart) {
            int position = shoppingCart.indexOf(item);
            Product itemInCart = shoppingCart.get(position);
            itemInCart.setQuantity(qty);
            shoppingCart.set(position, itemInCart);
        } else {
            item.setQuantity(qty);
            shoppingCart.add(item);
        }
        updateApp();
    }

    public void addItemList(Product products) {
        /*for (int i = 0; i < products.size(); i++) {
            listProduct.add(products.get(i));
        }*/
        listProduct.add(products);
        Gson gson = new Gson();
        String serializedItems = gson.toJson(listProduct);
        editor.putString(SERIALIZED_ITEMS, serializedItems).commit();
    }

    public List<Product> showItemList() {
        return listProduct;
    }
}
