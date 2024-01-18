package com.example.providertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String newId = "1";

    private static final String TAG = "MainActivity";

    private static final String AUTHORITY = "content://com.example.databasetest.provider/book";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加数据
                Uri uri = Uri.parse(AUTHORITY);
                ContentValues values = new ContentValues();
                values.put("name", "Harry Potter");
                values.put("author", "J.K. Rowling");
                values.put("pages", 514);
                values.put("price", 43.95);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = Integer.toString(Integer.parseInt(newUri.getPathSegments().get(1)));
                Log.d("MainActivity", newId);
            }
        });
        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(AUTHORITY);
                Cursor cursor = getContentResolver().query(uri, null, null,
                        null, null);
                if (cursor != null) {
                    while(cursor.moveToNext()) {
                        String name = cursor.getString(cursor
                                .getColumnIndexOrThrow("name"));
                        String author = cursor.getString(cursor
                                .getColumnIndexOrThrow("author"));
                        int pages = cursor.getInt(cursor.getColumnIndexOrThrow("pages"));
                        double price = cursor.getDouble(cursor
                                .getColumnIndexOrThrow("price"));
                        Log.d(TAG, "name: " + name);
                        Log.d(TAG, "author: " + author);
                        Log.d(TAG, "pages: " + pages);
                        Log.d(TAG, "price: " + price);
                    }
                    cursor.close();
                }
            }
        });
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(AUTHORITY + "/" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "A Storm of Swords");
                values.put("pages", 1216);
                values.put("price", 24.05);
                getContentResolver().update(uri, values, null, null);
            }
        });
        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(AUTHORITY + "/" + newId);
                getContentResolver().delete(uri, null, null);
            }
        });
    }
}