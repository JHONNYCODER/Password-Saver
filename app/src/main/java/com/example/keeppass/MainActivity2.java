package com.example.keeppass;


import static com.example.keeppass.MainActivity.DATABASE_NAME;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    //a list to store all the products
    List<Product> productList;
    RecyclerView recyclerView;
    SQLiteDatabase mDatabase;
    ProductAdapter adapter;
    View Add_New;
    private Toolbar main_tool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        main_tool = findViewById(R.id.toolbar_main2);
        setSupportActionBar(main_tool);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Saved Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Add_New=findViewById(R.id.floatingActionButton);
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createEmployeeTable();





        Add_New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
                finish();
//                Toast.makeText(MainActivity2.this, "Add New Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);

        showEmployeesFromDatabase();
    }

    private void showEmployeesFromDatabase() {
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorproduct = mDatabase.rawQuery("SELECT * FROM Student", null);
        List<Product> productList= new ArrayList<>();

        //if the cursor has some data
        if (cursorproduct.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                productList.add(new Product(
                        cursorproduct.getInt(0),
                        cursorproduct.getString(1),
                        cursorproduct.getString(2),
                        cursorproduct.getString(3),
                        cursorproduct.getString(4),
                        cursorproduct.getString(5)
                ));
            } while (cursorproduct.moveToNext());
        }
        if (productList.isEmpty()) {
            Toast.makeText(this, "No items Found in database", Toast.LENGTH_SHORT).show();
        }
        //closing the cursor
        cursorproduct.close();

        //creating the adapter object
        adapter = new ProductAdapter(this, R.layout.custom_list_item, productList, mDatabase);

        //adding the adapter to listview
        recyclerView.setAdapter(adapter);

        adapter.reloadEmployeesFromDatabase();  //this method is in prdouctadapter
    }
    private void createEmployeeTable() {
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student " +
                "(\n" +
                "    id INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    Name varchar(200) NOT NULL,\n" +
                "    Email varchar(200) NOT NULL,\n" +
                "    UserName varchar(200) NOT NULL,\n" +
                "    PhoneNo Varchar(200) NOT NULL,\n" +
                "    PassWord Varchar(200) NOT NULL\n" +
                ");"
        );

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            MainActivity2.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
//    private void PrompInfo() {
//
//        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Keep Password Saved and Secure")
//                .setDescription("Use Finger print to Login")
//                .setDeviceCredentialAllowed(true)
//                .setConfirmationRequired(true)
//                .build();
//        biometricPrompt.authenticate(promptInfo);

    }




