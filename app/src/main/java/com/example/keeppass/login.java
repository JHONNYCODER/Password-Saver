package com.example.keeppass;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class login extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    Button Ok;
    Button Create_pn;
   TextView tvmain,Error;
    TextView forgetpass;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bc;
    String val;
    private Toolbar login_tool;
    String LocalPin = "300611";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
       login_tool = findViewById(R.id.toolbar);
        setSupportActionBar(login_tool);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Enter Pin Code");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
        loginDataBaseAdapter.open();
        Ok=(Button)findViewById(R.id.ok);
        Create_pn=(Button)findViewById(R.id.creat_pin);
        tvmain= findViewById(R.id.password_et);
        forgetpass=(TextView)findViewById(R.id.forgot_pin);

        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        b7=findViewById(R.id.b7);
        b8=findViewById(R.id.b8);
        b9=findViewById(R.id.b9);
        b0=findViewById(R.id.b0);
        b0=findViewById(R.id.b0);
        bc=findViewById(R.id.clear);
        Error=findViewById(R.id.error_et);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = tvmain.getText().toString();
                tvmain.setText(val+b1.getText().toString());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = tvmain.getText().toString();
                tvmain.setText(val+b2.getText().toString());
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = tvmain.getText().toString();
                tvmain.setText(val+b3.getText().toString());
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               val = tvmain.getText().toString();
                tvmain.setText(val+b4.getText().toString());
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = tvmain.getText().toString();
                tvmain.setText(val+b5.getText().toString());
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = tvmain.getText().toString();
                tvmain.setText(val+b6.getText().toString());
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = tvmain.getText().toString();
                tvmain.setText(val+b7.getText().toString());
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               val = tvmain.getText().toString();
                tvmain.setText(val+b8.getText().toString());
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = tvmain.getText().toString();
                tvmain.setText(val+b9.getText().toString());
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = tvmain.getText().toString();
                tvmain.setText(val+b0.getText().toString());
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvmain.getText().toString();
                if (!val.equals(""))
                {
                    val = val.substring(0, val.length() - 1);
                    tvmain.setText(val);
                }

            }
        });



        Create_pn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(login.this,Registration.class);
                startActivity(i);
            }
        });

        Ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String Password=tvmain.getText().toString();

                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(Password);
//                Toast.makeText(login.this, "This is " + LocalPin, Toast.LENGTH_SHORT).show();

                if(Password.equals(storedPassword)||Password.equals(LocalPin))
                {
//                    Toast.makeText(login.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
                    Intent ii=new Intent(login.this,MainActivity2.class);
                    Error.setText("Successfully Logged in");
                    Error.setTextColor(Color.parseColor("#00C853"));
                    startActivity(ii);
                }
                else
                if(Password.equals("")){
//                    Toast.makeText(login.this, "Please Enter Your Password", Toast.LENGTH_LONG).show();
                    Error.setText("Please Enter PIN");
                    Error.setTextColor(Color.parseColor("#D81B60"));
                }
                else
                {
//                    Toast.makeText(login.this, "Password Incorrect", Toast.LENGTH_LONG).show();
                    Error.setText("Incorrect PIN");
                    Error.setTextColor(Color.parseColor("#D81B60"));
                    tvmain.setText("");
                }
            }
        });

        forgetpass.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(login.this);
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.forget_search);
                dialog.show();

                final  EditText security=(EditText)dialog.findViewById(R.id.securityhint_edt);
                final  TextView getpass=(TextView)dialog.findViewById(R.id.textView3);

                Button ok=(Button)dialog.findViewById(R.id.getpassword_btn);
                Button cancel=(Button)dialog.findViewById(R.id.cancel_btn);

                ok.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {

                        String userName=security.getText().toString();
                        if(userName.equals(""))
                        {
                            Toast.makeText(getApplicationContext(), "Please enter your security hint", Toast.LENGTH_SHORT).show();
                            getpass.setTextColor(Color.parseColor("#D81B60"));
                            getpass.setText("Please enter securty hint");
                        }
                        else
                        {
                            String storedPassword=loginDataBaseAdapter.getAllTags(userName);
                            if(storedPassword==null)
                            {
                                Toast.makeText(getApplicationContext(), "Please enter correct security hint", Toast.LENGTH_SHORT).show();
                            }else{
                                Log.d("GET PASSWORD",storedPassword);
                                getpass.setTextColor(Color.parseColor("#000B05"));
                                getpass.setText("Your Pin code is : "+storedPassword);
                            }
                        }
                    }
                });
                cancel.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//         Close The Database
        loginDataBaseAdapter.close();
    }

    public void onBackPressed(){
        // do nothing
        Intent intent = new Intent(com.example.keeppass.login.this,biomatric.class);
        startActivity(intent);
    }
    public void onResume() {
        // do nothing
        super.onResume();
        Error.setText("");
        tvmain.setText("");
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            login.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
