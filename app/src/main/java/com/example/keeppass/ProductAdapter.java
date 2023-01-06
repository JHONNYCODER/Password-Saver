package com.example.keeppass;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.biometric.BiometricPrompt;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    int custom_list_item;
    SQLiteDatabase mDatabase;
    Boolean isHide = true;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    private Context mCtx;
//    BiometricManager biometricManager = BiometricManager.from(mCtx);


    //this context we will use to inflate the layout


    //we are storing all the products in a list
    private List<Product> productList;

    //getting the context and product list with constructor
    public ProductAdapter(Context mCtx, int custom_list_item, List<Product> productList, SQLiteDatabase mDatabase) {
        this.mCtx = mCtx;
        this.custom_list_item = custom_list_item;
        this.mDatabase = mDatabase;
        this.productList = productList;
        Button Floaiting;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custom_list_item, null);



        return new ProductViewHolder(view);

       
    }

//    LayoutInflater inflater = LayoutInflater.from(mCtx);
//    View view = inflater.inflate(R.layout.activity_main, null);

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position

        final Product product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewName.setText(product.getName());
        holder.textViewUsername.setText(product.getUsername());
        holder.textViewEmail.setText(product.getEmail());
        holder.textViewPhone.setText(product.getPhno());
        holder.textViewPass.setText(product.getPass_w());
        holder.ivcopy.setVisibility(View.INVISIBLE);




        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmployee(product);
            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM Student WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{product.getId()});
                        Snackbar.make(view, "Deleted" + product.getName(), Snackbar.LENGTH_SHORT).show();
                        Toast.makeText(mCtx, "Deleted successfully!", Toast.LENGTH_SHORT).show();

                          reloadEmployeesFromDatabase(); //Reload List
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.Show_Pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PrompInfo();
//
//                BiometricManager biometricManager = BiometricManager.from(mCtx);
//                switch (biometricManager.canAuthenticate()) {
//                    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
//                        Toast.makeText(mCtx, "Finger print sensor not found", Toast.LENGTH_SHORT).show();
////
//                        break;
//                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
//                        Toast.makeText(mCtx, "Finger print sensor Error", Toast.LENGTH_SHORT).show();
//                        break;
//                    case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
//                        Toast.makeText(mCtx, "Finger print Not Enrolled", Toast.LENGTH_SHORT).show();
//                        break;
//                    case BiometricManager.BIOMETRIC_SUCCESS:
////                Toast.makeText(biomatric.this, "Authenticate Successfully", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//
//                Executor executor = ContextCompat.getMainExecutor(mCtx);
//                biometricPrompt = new BiometricPrompt((FragmentActivity) mCtx, executor, new BiometricPrompt.AuthenticationCallback() {
//                    @Override
//                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
//                        super.onAuthenticationError(errorCode, errString);
//                        Toast.makeText(mCtx, "" + errString, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
//                        super.onAuthenticationSucceeded(result);
//                        holder.textViewEmail.setTransformationMethod(PasswordTransformationMethod.getInstance());
                if (isHide==false){
//                    holder.Show_Pw.setBackgroundResource(R.drawable.visibility);
                    holder.Show_Pw.setImageResource(R.drawable.invisible);
//                    holder.textViewPass.setInputType(InputType.TYPE_CLASS_TEXT);
                    holder.textViewEmail.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    holder.textViewUsername.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    holder.textViewPhone.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    holder.textViewPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    holder.ivcopy.setVisibility(View.INVISIBLE);
                    isHide = true;
//                    Toast.makeText(mCtx, "Password hide!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
//                   holder.textViewPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    holder.textViewEmail.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                holder.textViewUsername.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                holder.textViewPhone.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                holder.textViewPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                holder.Show_Pw.setImageResource(R.drawable.visibility);
                holder.ivcopy.setVisibility(View.VISIBLE);
                isHide = false;

//                Toast.makeText(mCtx, "Password Unhide!", Toast.LENGTH_SHORT).show();


                    }

//                    @Override
//                    public void onAuthenticationFailed() {
//                        super.onAuthenticationFailed();
//                        Toast.makeText(mCtx, "Authenticate Failed", Toast.LENGTH_SHORT).show();
//                    }
//                });





//                if (isHide==true){
////                    holder.textViewPass.setInputType(InputType.TYPE_CLASS_TEXT);
//                    holder.textViewEmail.setTransformationMethod(PasswordTransformationMethod.getInstance());
////                    holder.textViewUsername.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    holder.textViewPhone.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    holder.textViewPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    isHide = false;
//                    return;
//                }
//               else
////                   holder.textViewPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                holder.textViewEmail.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
////                holder.textViewUsername.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                holder.textViewPhone.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                holder.textViewPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//
//                    isHide = true;
//
//                Toast.makeText(mCtx, "Password Unhide!", Toast.LENGTH_SHORT).show();

        });
        holder.ivcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ClipboardManager) holder.itemView.getContext().getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("stylish text", holder.textViewPass.getText()));
                Toast.makeText(holder.itemView.getContext(), "PAssword Copied to Clipboard", Toast.LENGTH_SHORT).show();

            }
        });


    }
    void reloadEmployeesFromDatabase() {
        Cursor cursorproduct1 = mDatabase.rawQuery("SELECT * FROM Student", null);
        if (cursorproduct1.moveToFirst()) {
            productList.clear();
            do {
                productList.add(new Product(
                        cursorproduct1.getInt(0),
                        cursorproduct1.getString(1),
                        cursorproduct1.getString(2),
                        cursorproduct1.getString(3),
                        cursorproduct1.getString(4),
                        cursorproduct1.getString(5)
                ));
            } while (cursorproduct1.moveToNext());
        }
        cursorproduct1.close();
        notifyDataSetChanged();
    }
    private void updateEmployee(final Product product) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_employee, null);
        builder.setView(view);
        final EditText editTextName = view.findViewById(R.id.editTextName);
        final EditText editUsername = view.findViewById(R.id.editUsername);
        final EditText editemail = view.findViewById(R.id.editEmail);
        final EditText editphno = view.findViewById(R.id.editTextphno);
        final EditText editPass = view.findViewById(R.id.editTextpassword);


        editTextName.setText(product.getName());
        editUsername.setText(product.getUsername());
        editemail.setText(product.getEmail());
        editphno.setText(product.getPhno());
        editPass.setText(product.getPass_w());

        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

        // CREATE METHOD FOR EDIT THE FORM
        view.findViewById(R.id.buttonUpdateEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String email = editemail.getText().toString().trim();
                String username = editUsername.getText().toString().trim();
                String phno = editphno.getText().toString().trim();
                String pass_w = editPass.getText().toString().trim();

                if (name.isEmpty()) {
                    editTextName.setError("Name can't be blank");
                    editTextName.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    editUsername.setError("Email can't be blank");
                    editUsername.requestFocus();
                    return;
                }
                if (username.isEmpty()) {
                    editTextName.setError("Username can't be blank");
                    editTextName.requestFocus();
                    return;
                }
                if (phno.isEmpty()) {
                    editTextName.setError("Mobile No can't be blank");
                    editTextName.requestFocus();
                    return;
                }
                if (pass_w.isEmpty()) {
                    editTextName.setError("Password can't be blank");
                    editTextName.requestFocus();
                    return;
                }

                //Name, Email, UserName, PhoneNo, PassWord

                String sql = "UPDATE Student\n"+
                        "SET Name = ?,\n"+
                        "Email= ?,\n"+
                        "Username= ?,\n"+
                        "PhoneNO= ?,\n"+
                        "PassWord= ?\n"+
                        "WHERE id = ?;\n";


//                mDatabase.execSQL(insertSQL, new String[]{name, email, username, phno,pass_w,String.valueOf(product.getId())});
                mDatabase.execSQL(sql, new String[]{name, email,username,phno,pass_w, String.valueOf(product.getId())});
                Toast.makeText(mCtx, "Data Updated", Toast.LENGTH_SHORT).show();
               reloadEmployeesFromDatabase();

                dialog.dismiss();


            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewUsername, textViewEmail, textViewPhone,textViewPass;
        ImageView editbtn, deletebtn,Show_Pw,ivcopy;
        Button Add_new;
        Boolean isHide = true;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            textViewPass = itemView.findViewById(R.id.textViewPassword);
            deletebtn = itemView.findViewById(R.id.buttonDeleteStudent);
            Show_Pw = itemView.findViewById(R.id.button_show_pw);
            editbtn = itemView.findViewById(R.id.buttonEditstudent);
            ivcopy = itemView.findViewById(R.id.copy_btn);
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//            BiometricManager biometricManager = BiometricManager.from(mCtx);
//            switch (biometricManager.canAuthenticate()) {
//                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
//                    Toast.makeText(mCtx, "Finger print sensor not found", Toast.LENGTH_SHORT).show();
////
//                    break;
//                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
//                    Toast.makeText(mCtx, "Finger print sensor Error", Toast.LENGTH_SHORT).show();
//                    break;
//                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
//                    Toast.makeText(mCtx, "Finger print Not Enrolled", Toast.LENGTH_SHORT).show();
//                    break;
//                case BiometricManager.BIOMETRIC_SUCCESS:
////                Toast.makeText(biomatric.this, "Authenticate Successfully", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            Executor executor = ContextCompat.getMainExecutor(mCtx);
//            biometricPrompt = new BiometricPrompt((FragmentActivity) mCtx, executor, new BiometricPrompt.AuthenticationCallback() {
//                @Override
//                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
//                    super.onAuthenticationError(errorCode, errString);
//                    Toast.makeText(mCtx, "" + errString, Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
//                    super.onAuthenticationSucceeded(result);
//
//                    if (isHide==true){
////                    holder.textViewPass.setInputType(InputType.TYPE_CLASS_TEXT);
//                       textViewEmail.setTransformationMethod(PasswordTransformationMethod.getInstance());
////                    holder.textViewUsername.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                        textViewPhone.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                        textViewPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                        isHide = false;
//                        Toast.makeText(mCtx, "Password hide!", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    else
////                   holder.textViewPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                  textViewEmail.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
////                holder.textViewUsername.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                  textViewPhone.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                 textViewPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//
//                    isHide = true;
//
//                    Toast.makeText(mCtx, "Password Unhide!", Toast.LENGTH_SHORT).show();
//
//
//                }
//
//                @Override
//                public void onAuthenticationFailed() {
//                    super.onAuthenticationFailed();
//                    Toast.makeText(mCtx, "Authenticate Failed", Toast.LENGTH_SHORT).show();
//                }
//            });





        }
    }


//    private void PrompInfo() {
//
//        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Keep Password Saved and Secure")
//                .setDescription("Use Finger print to Login")
//                .setDeviceCredentialAllowed(true)
//                .setConfirmationRequired(true)
//                .build();
//        biometricPrompt.authenticate(promptInfo);
////        isHide=true;
//
//    }



}
