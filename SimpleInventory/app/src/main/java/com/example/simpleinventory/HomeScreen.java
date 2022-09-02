package com.example.simpleinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    private Button insertButton;
    private Button viewButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        insertButton = (Button) findViewById(R.id.Insert_button);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InsertProduct();
            }
        });

        viewButton=findViewById(R.id.View_button);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DisplayData();
            }
        });
    }
    public void InsertProduct(){
        Intent intent = new Intent(this, InsertProduct.class);
        startActivity(intent);
    }
    public void DisplayData(){
        Intent intent = new Intent(this, DisplayData.class);
        startActivity(intent);
    }
}