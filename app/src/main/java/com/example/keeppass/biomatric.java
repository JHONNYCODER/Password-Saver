package com.example.keeppass;

import static com.example.keeppass.MainActivity.DATABASE_NAME;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class biomatric extends AppCompatActivity {
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    ConstraintLayout constraintLayout;
    SQLiteDatabase mDatabase;
    Button Use_Pin;
    ImageView Use_finger;
    ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biomatric);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createEmployeeTable();
        Use_Pin=findViewById(R.id.use_pin);
        Use_finger=findViewById(R.id.use_finger);

        BiometricManager biometricManager = BiometricManager.from(this);

        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(biomatric.this, "Finger print sensor not found", Toast.LENGTH_SHORT).show();
//
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(biomatric.this, "Finger print sensor Error", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(biomatric.this, "Finger print Not Enrolled", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_SUCCESS:
//                Toast.makeText(biomatric.this, "Authenticate Successfully", Toast.LENGTH_SHORT).show();
                break;
        }
        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(biomatric.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(biomatric.this, "" + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
//                Toast.makeText(biomatric.this, ""+result, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(biomatric.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(biomatric.this, "Authenticate Failed", Toast.LENGTH_SHORT).show();
            }
        });
        Use_Pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Go_to_Login();
                Intent intent= new Intent(biomatric.this,login.class);
                startActivity(intent);

            }
        });
        Use_finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrompInfo();
            }
        });




//        PrompInfo();
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



    private void PrompInfo() {

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Save Your Credential Securely")
                .setDescription("Use Finger print to Login")
                .setDeviceCredentialAllowed(true)
                .setConfirmationRequired(true)
                .build();
        biometricPrompt.authenticate(promptInfo);

    }


    private void Go_to_Login() {

        final Dialog dialog = new Dialog(biomatric.this);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.go_signup);
        dialog.setCancelable(false);
        dialog.show();

        Button ok = (Button) dialog.findViewById(R.id.okplease);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);

        ok.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(biomatric.this, login.class);
                startActivity(intent);
                finish();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();
                finish();
            }
        });

        dialog.show();


    }



}