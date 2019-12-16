package com.example.inz_client.views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inz_client.R;
import com.example.inz_client.models.Product;

import java.util.List;

public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductViewHolder> {

    List<Product> list;

    public ProductsRecyclerViewAdapter(List<Product> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row,parent,false);
        ProductViewHolder viewHolder = new ProductViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Log.d(this.getClass().getName(),"onBind odpalone");
        Product p = list.get(position);
        String amount = String.valueOf(p.getAmount());
        String price = String.valueOf(p.getPrice());
        String total = String.valueOf(p.getPrice()*p.getAmount());
        String row = p.getName()+": "+amount+"x"+price+" = "+total;
        holder.productInfo.setText(row);
    }

    @Override
    public int getItemCount() {
        try {
            return list.size();
        }catch (NullPointerException e){
            return 0;
        }
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView productInfo;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productInfo = itemView.findViewById(R.id.product_info);
        }
    }
}
