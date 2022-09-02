package com.example.simpleinventory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateProduct extends AppCompatActivity {

    EditText upd_product_name,upd_product_unit,upd_product_price,upd_product_date,upd_product_available,upd_product_cost;
    Button updButton, deleteButton,cost_btn;
    String id,name,unit,price,date,avail,cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_update_product);
        upd_product_name = findViewById(R.id.product_name_upd);
        upd_product_unit = findViewById(R.id.unit_Text_upd);
        upd_product_price = findViewById(R.id.price_Text_upd);
        upd_product_date = findViewById(R.id.date_Text_upd);
        upd_product_available = findViewById(R.id.avail_Text_upd);
        upd_product_cost=findViewById(R.id.cost_inventory_upd);


        updButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.delButton);
        cost_btn = findViewById(R.id.prod_cost_upd);
        cost_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price_int = Integer.parseInt(upd_product_price.getText().toString());
                int inventory_int = Integer.parseInt(upd_product_available.getText().toString());
                int multiply = price_int*inventory_int;
                upd_product_cost.setText(Integer.toString(multiply));
            }
        });


        getAllIntentData();

        ActionBar actionBar= getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(name);
        }


        updButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(UpdateProduct.this);
                name = upd_product_name.getText().toString().trim();
                unit = upd_product_unit.getText().toString().trim();
                price = upd_product_price.getText().toString().trim();
                date = upd_product_date.getText().toString().trim();
                avail = upd_product_available.getText().toString().trim();
                cost = upd_product_cost.getText().toString().trim();


                db.updateData(id, name, unit, price, date, avail,cost);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               deleteDialog();

            }
        });

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        upd_product_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(UpdateProduct.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = month+"/"+dayOfMonth+"/"+year;
                        upd_product_date.setText(date);

                    }
                },year, month,day);
                dialog.show();

            }
        });

    }
    void getAllIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")&& getIntent().hasExtra("unit")
                && getIntent().hasExtra("price")&& getIntent().hasExtra("date")&& getIntent().hasExtra("avail")&& getIntent().hasExtra("cost")) {
                //get data
                id = getIntent().getStringExtra("id");
                name = getIntent().getStringExtra("name");
                unit = getIntent().getStringExtra("unit");
                price = getIntent().getStringExtra("price");
                date = getIntent().getStringExtra("date");
                avail = getIntent().getStringExtra("avail");
                cost = getIntent().getStringExtra("cost");

                //set data
                upd_product_name.setText(name);
                upd_product_unit.setText(unit);
                upd_product_price.setText(price);
                upd_product_date.setText(date);
                upd_product_available.setText(avail);
                upd_product_cost.setText(cost);
        }else{
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();

        }

    }
    void deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper db = new DBHelper(UpdateProduct.this);
                db.deleteRow(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}