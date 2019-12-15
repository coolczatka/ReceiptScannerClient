package com.example.inz_client.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inz_client.R;
import com.example.inz_client.models.Product;

import java.util.List;

public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.ViewHolder> {

    List<Product> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row,parent,false);
        ProductsRecyclerViewAdapter.ViewHolder viewHolder = new ProductsRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = list.get(position);
        String amount = String.valueOf(p.getAmount());
        String price = String.valueOf(p.getPrice());
        String total = String.valueOf(p.getPrice()*p.getAmount());
        String row = p.getName()+": "+amount+"x"+price+" = "+total;
        holder.productInfo.setText(row);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView productInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productInfo = itemView.findViewById(R.id.product_info);
        }
    }
}
