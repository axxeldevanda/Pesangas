package com.oldlex.pesangas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oldlex.pesangas.Common.Common;
import com.oldlex.pesangas.Model.User;

public class SigninActivity extends AppCompatActivity {
    EditText edtPhoneLogin, edtPasswordLogin;
    Button btnSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edtPhoneLogin = findViewById(R.id.edtPhoneLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnSignIn = findViewById(R.id.btnSignIn);
        //init Firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("users");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SigninActivity.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //Check if user not exist in Database
                        if (dataSnapshot.child(edtPhoneLogin.getText().toString()).exists()) {
                            //Get User Information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhoneLogin.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhoneLogin.getText().toString());//Set Phone
                            if (user.getPassword() != null && edtPasswordLogin.getText() != null && user.getPassword().equals(edtPasswordLogin.getText().toString())) {
                                {
                                    Intent homeIntent = new Intent(SigninActivity.this, WelcomeActivity.class);
                                    Common.currentUser = user;
                                    startActivity(homeIntent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(SigninActivity.this, "Sign In Gagal!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(SigninActivity.this, "Belum Terdaftar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
