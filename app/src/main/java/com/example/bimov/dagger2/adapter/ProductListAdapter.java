package com.example.bimov.dagger2.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bimov.dagger2.R;
import com.example.bimov.dagger2.model.Product;
import com.example.bimov.dagger2.util.ItemClickListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BimoV on 2/7/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<Product> productsList = new ArrayList<>();
    private Context context;
    private ItemClickListener clickListener;

    public ProductListAdapter(List<Product> products, Context context) {
        this.productsList = products;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product items = productsList.get(position);
        holder.productName.setText(items.getProductName());
        holder.productSalePrice.setText(formatRupiah(items.getSalePrice()));
    }

    private String formatRupiah(double kurs) {
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(formatRp);
        return decimalFormat.format(kurs);
    }


    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.productSalePrice)
        TextView productSalePrice;
        @BindView(R.id.card_view)
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view)
        public void onClick(View v) {
            Product items = productsList.get(getAdapterPosition());
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition(),items);

        }
    }
}
