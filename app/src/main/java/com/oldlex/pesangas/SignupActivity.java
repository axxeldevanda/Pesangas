package com.oldlex.pesangas;

import android.app.ProgressDialog;
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
import com.oldlex.pesangas.Model.User;

public class SignupActivity extends AppCompatActivity {
    EditText edtPhoneRegister, edtNameRegister, edtPasswordRegister, edtRetypePassword;
    Button btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtPhoneRegister = findViewById(R.id.edtPhoneRegister);
        edtNameRegister = findViewById(R.id.edtNameRegister);
        edtPasswordRegister = findViewById(R.id.edtPasswordRegister);
        edtRetypePassword = findViewById(R.id.edtRetypePassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("users");


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignupActivity.this);
                mDialog.setMessage("Please Waiting..");
                mDialog.show();

                if (edtPhoneRegister.getText().toString().length() <= 12) {
                    Toast.makeText(SignupActivity.this, "Nomer telepon tidak valid", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                } else if (edtPhoneRegister.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "Nomor telepon harus terisi", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                } else if (edtNameRegister.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "Nama harus terisi", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                } else if (edtPasswordRegister.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "Password harus terisi", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                } else if (edtPasswordRegister.getText().toString().length() == 8) {
                    Toast.makeText(SignupActivity.this, "Password harus berjumlah 8", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                } else {
                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //Check if already exist
                            if (dataSnapshot.child(edtPhoneRegister.getText().toString()).exists()) {
//                                mDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                            } else {
//                                mDialog.dismiss();
                                User user = new User(edtNameRegister.getText().toString(), edtPasswordRegister.getText().toString());
                                table_user.child(edtPhoneRegister.getText().toString()).setValue(user);
                                Toast.makeText(SignupActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();

                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}
