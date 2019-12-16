package com.example.inz_client.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inz_client.R;
import com.example.inz_client.activities.ReceiptDetailsActivity;
import com.example.inz_client.models.Receipt;

import java.util.List;

public class ReceiptsRecyclerViewAdapter extends RecyclerView.Adapter<ReceiptsRecyclerViewAdapter.ReceiptViewHolder> {

    List<Receipt> list;
    String token;
    Context context;

    public ReceiptsRecyclerViewAdapter(List<Receipt> list, Context context, String t) {
        this.list = list;
        this.context = context;
        this.token = t;
    }

    @NonNull
    @Override
    public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_row,parent,false);
        ReceiptViewHolder viewHolder = new ReceiptViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptViewHolder holder, final int position) {
        Log.d(this.getClass().getName(),"onBind odpalone");
        holder.shop.setText(list.get(position).getShop());
        holder.data.setText(list.get(position).getDate());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ReceiptDetailsActivity.class);
                i.putExtra("token",token);
                i.putExtra("receipt_id",list.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ReceiptViewHolder extends RecyclerView.ViewHolder{
        public TextView data;
        public TextView shop;
        RelativeLayout parentLayout;

        public ReceiptViewHolder(View ithemView) {
            super(ithemView);
            this.data = ithemView.findViewById(R.id.realise_date);
            this.shop = ithemView.findViewById(R.id.shop);
            parentLayout = ithemView.findViewById(R.id.receipt_row_layout);
        }
    }
}
