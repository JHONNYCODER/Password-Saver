package com.example.keeppass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText e_name, e_email, e_username, e_phnumber,e_password;
    Button bt_save,viewdata,Clear_data;
    public static final String DATABASE_NAME = "StudentDatabases.db";
    SQLiteDatabase mDatabase;
    private Toolbar main_tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        main_tool = findViewById(R.id.toolbar);
        setSupportActionBar(main_tool);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Save New Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        createEmployeeTable();

        //FindById (Button and Edittxt)
        e_name = (EditText) findViewById(R.id.e_name);
        e_email = (EditText) findViewById(R.id.e_email);
        e_username = (EditText) findViewById(R.id.e_username);
        e_phnumber = (EditText) findViewById(R.id.e_phnumber);
        e_password=findViewById(R.id.password);
        bt_save = (Button) findViewById(R.id.btn_save);
        viewdata = (Button) findViewById(R.id.viewdata);
        Clear_data=findViewById(R.id.clear_data);


        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        //Onclick Btn
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the Enter data
                String name = e_name.getText().toString().trim();
                String email = e_email.getText().toString().trim();
                String username = e_username.getText().toString();
                String phone = e_phnumber.getText().toString();
                String password = e_password.getText().toString();


                if (name.isEmpty() || email.isEmpty() || username.isEmpty() || phone.isEmpty()||password.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Fill the form", Toast.LENGTH_SHORT).show();

                } else {

                    String insertSQL = "INSERT INTO Student \n" +
                            "(Name, Email, UserName, PhoneNo,PassWord)\n" +
                            "VALUES \n" +
                            "(?, ?, ?, ?,?);";


                    //using the same method execsql for inserting values
                    //this time it has two parameters
                    //first is the sql string and second is the parameters that is to be binded with the query
                    mDatabase.execSQL(insertSQL, new String[]{name, email, username, phone,password});

                    Toast.makeText(MainActivity.this, "Great! Data Saved", Toast.LENGTH_SHORT).show();
                    clear_data();
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                }


            }
        });
        Clear_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_data();
            }
        });


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
    private void clear_data(){
        e_name.getText().clear();
        e_username.getText().clear();
        e_email.getText().clear();
        e_password.getText().clear();
        e_phnumber.getText().clear();
    }
}
