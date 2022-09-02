package com.example.simpleinventory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    Activity act;
    private ArrayList product_id,product_name,product_unit,product_price,product_date,product_avail,product_cost;


    CustomAdapter(Activity act,
                  Context context,
                  ArrayList<String> productId,
                  ArrayList<String> productName,
                  ArrayList<String> product_unit,
                  ArrayList<String> product_price,
                  ArrayList<String> product_date,
                  ArrayList<String> product_avail,
                  ArrayList<String> product_cost){
        this.act = act;
        this.context = context;
        this.product_id = productId;
        this.product_name = productName;
        this.product_unit = product_unit;
        this.product_price = product_price;
        this.product_date = product_date;
        this.product_avail = product_avail;
        this.product_cost = product_cost;


    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.product_id_text.setText(String.valueOf(product_id.get(position)));
        holder.product_name_text.setText(String.valueOf(product_name.get(position)));
        holder.product_unit_text.setText(String.valueOf(product_unit.get(position)));
        holder.product_price_text.setText(String.valueOf(product_price.get(position)));
        holder.product_date_text.setText(String.valueOf(product_date.get(position)));
        holder.product_avail_text.setText(String.valueOf(product_avail.get(position)));
        holder.product_cost_text.setText(String.valueOf(product_cost.get(position)));

        holder.updLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateProduct.class);
                intent.putExtra("id",String.valueOf(product_id.get(position)));
                intent.putExtra("name",String.valueOf(product_name.get(position)));
                intent.putExtra("unit",String.valueOf(product_unit.get(position)));
                intent.putExtra("price",String.valueOf(product_price.get(position)));
                intent.putExtra("date",String.valueOf(product_date.get(position)));
                intent.putExtra("avail",String.valueOf(product_avail.get(position)));
                intent.putExtra("cost",String.valueOf(product_cost.get(position)));
;
                act.startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView product_id_text,product_name_text,product_unit_text,product_price_text,product_date_text,product_avail_text,product_cost_text;
        LinearLayout updLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id_text = itemView.findViewById(R.id.prod_id);
            product_name_text = itemView.findViewById(R.id.prod_name);
            product_unit_text = itemView.findViewById(R.id.product_unit);
            product_price_text = itemView.findViewById(R.id.prod_price);
            product_date_text = itemView.findViewById(R.id.exp_date);
            product_avail_text = itemView.findViewById(R.id.prod_avail);
            product_cost_text = itemView.findViewById(R.id.view_cost);

            updLayout = itemView.findViewById(R.id.updLayout);
        }
    }
}
