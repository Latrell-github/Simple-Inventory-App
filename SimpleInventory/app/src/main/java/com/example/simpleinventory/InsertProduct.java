package com.example.simpleinventory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class InsertProduct extends AppCompatActivity {
    int SELECT_PHOTO = 1;
    Uri uri;
    ImageView imageView;
     DBHelper db;
     EditText dateText,price_input,available_input,cost;
     Button add_button,add_image,add_cost;
     TextInputEditText unit_input,product_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);


        dateText = findViewById(R.id.date_Text);
        product_input = findViewById(R.id.product_name);
        unit_input = findViewById(R.id.unit_Text);
        price_input = findViewById(R.id.price_Text);
        available_input = findViewById(R.id.avail_Text);

        cost = findViewById(R.id.cost_inventory);

        add_cost = findViewById(R.id.prod_cost);
        add_cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price_int = Integer.parseInt(price_input.getText().toString());
                int inventory_int = Integer.parseInt(available_input.getText().toString());
                int multiply = price_int*inventory_int;
                cost.setText(Integer.toString(multiply));
            }
        });

        add_image = findViewById(R.id.add_img);
        imageView = findViewById(R.id.imageView);

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_PHOTO);
            }
        });

        add_button = findViewById(R.id.addButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DBHelper(InsertProduct.this);
                db.addProduct(product_input.getText().toString().trim(),
                        unit_input.getText().toString().trim(),
                        price_input.getText().toString().trim(),
                        dateText.getText().toString().trim(),
                        available_input.getText().toString(),
                        cost.getText().toString().trim());

            }
        });

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(InsertProduct.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = month+"/"+dayOfMonth+"/"+year;
                        dateText.setText(date);

                    }
                },year, month,day);
                dialog.show();

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}