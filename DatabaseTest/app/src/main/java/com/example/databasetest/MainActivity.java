package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = findViewById(R.id.create_database);
        Button addData = findViewById(R.id.add_data);
        dbhelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.getWritableDatabase();
            }
        });
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "Harry Potter");
                values.put("author", "J.K. Rowling");
                values.put("pages", 500);
                values.put("price", 96);
                db.insert("Book", null, values);
                values.clear();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values);
            }
        });
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book", values, "name = ?",
                        new String[]{"The Da Vinci Code"});
            }
        });
        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[]{"450"});
            }
        });

        Button selectData = findViewById(R.id.select_data);
        selectData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                Cursor cursor = db.query("Book", new String[]{"name", "author"}, null,
                        null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.
                                getColumnIndexOrThrow("name"));
                        String author = cursor.getString(cursor.
                                getColumnIndexOrThrow("author"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }
}