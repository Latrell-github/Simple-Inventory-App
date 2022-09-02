package com.example.simpleinventory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayData extends AppCompatActivity {
    private RecyclerView recyclerView;
    DBHelper db;
    ArrayList<String> product_id,product_name,product_unit,product_price,product_date,product_avail,product_cost;
    CustomAdapter customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        recyclerView = findViewById(R.id.recyclerView);
        db = new DBHelper(DisplayData.this);
        product_id = new ArrayList<>();
        product_name = new ArrayList<>();
        product_unit = new ArrayList<>();
        product_price = new ArrayList<>();
        product_date = new ArrayList<>();
        product_avail = new ArrayList<>();
        product_cost = new ArrayList<>();



        displayAllData();

        customView = new CustomAdapter(DisplayData.this, this, product_id, product_name,product_unit,product_price,product_date,product_avail,product_cost);
        recyclerView.setAdapter(customView);
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayData.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
            recreate();

        }
    }

    void displayAllData(){
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                product_id.add(cursor.getString(0));
                product_name.add(cursor.getString(1));
                product_unit.add(cursor.getString(2));
                product_price.add(cursor.getString(3));
                product_date.add(cursor.getString(4));
                product_avail.add(cursor.getString(5));
                product_cost.add(cursor.getString(6));



            }
        }
    }
}