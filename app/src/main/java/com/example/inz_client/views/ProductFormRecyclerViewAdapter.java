package com.example.inz_client.views;

import android.util.Log;
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
        Log.d("product row","onBind odpalone");
        Product p = list.get(position);
        holder.name.setText(p.getName());
        holder.amount.setText(String.valueOf(p.getAmount()));
        holder.price.setText(String.valueOf(p.getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductFormViewHolder extends RecyclerView.ViewHolder {
        EditText name;
        EditText amount;
        EditText price;
        public ProductFormViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameEt);
            amount = itemView.findViewById(R.id.amountEt);
            price = itemView.findViewById(R.id.priceEt);
        }
        public boolean validatePrice(){
            return price.getText().toString().matches("\\d+\\.?\\d{0,2}");
        }
        public boolean validateAmount(){
            return amount.getText().toString().matches("\\d+\\.?\\d{0,2}");
        }
        public boolean validateName(){
            return !name.getText().toString().equals("");
        }

        public EditText getName() {
            return name;
        }

        public void setName(EditText name) {
            this.name = name;
        }

        public EditText getAmount() {
            return amount;
        }

        public void setAmount(EditText amount) {
            this.amount = amount;
        }

        public EditText getPrice() {
            return price;
        }

        public void setPrice(EditText price) {
            this.price = price;
        }
    }
}
