package com.example.simpleinventory;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;



public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Product.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_UNIT = "unit";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_AVAILABLE = "available";
    private static final String COLUMN_COST = "cost";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_UNIT + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_AVAILABLE + " TEXT, " +
                COLUMN_COST + " TEXT );";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

        Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);

        }
        return cursor;
        }


    void deleteRow(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context,"Task Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show();
        }


    }

    public void addProduct(String name, String unit, String price, String date, String available, String cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_UNIT, unit);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_AVAILABLE, available);
        cv.put(COLUMN_COST, cost);



        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Inserted Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(String id, String name, String unit, String price, String date, String avail, String cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_UNIT, unit);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_AVAILABLE, avail);
        cv.put(COLUMN_COST, cost);



        long result = db.update(TABLE_NAME, cv, "id=?",new String[]{id});
        if(result == -1){
            Toast.makeText(context,"Task Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Update Successfully",Toast.LENGTH_SHORT).show();
        }
    }
}

