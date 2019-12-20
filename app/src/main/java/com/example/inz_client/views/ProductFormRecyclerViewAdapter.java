package com.example.inz_client.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.inz_client.R;
import com.example.inz_client.models.Product;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductFormRecyclerViewAdapter extends RecyclerView.Adapter<ProductFormRecyclerViewAdapter.ProductFormViewHolder> {

    List<Product> list;

    public ProductFormRecyclerViewAdapter(List<Product> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ProductFormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_form_row,parent,false);
        ProductFormViewHolder viewHolder = new ProductFormViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductFormViewHolder holder, int position) {
        Product p = list.get(position);
        holder.name.setText(p.getName());
        holder.amount.setText(String.valueOf(p.getAmount()));
        holder.price.setText(String.valueOf(p.getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductFormViewHolder extends RecyclerView.ViewHolder {
        EditText name;
        EditText amount;
        EditText price;
        public ProductFormViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameEt);
            amount = itemView.findViewById(R.id.amountEt);
            price = itemView.findViewById(R.id.priceEt);
        }
    }
}
