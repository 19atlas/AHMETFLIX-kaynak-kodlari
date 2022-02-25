package com.dtw.ahmetflix;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {
    EditText etEmail,etPassword;
    TextView notHaveAccount;
    Button btnSignIn,btnGoogle;
    ImageView ImageViewke;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        notHaveAccount = findViewById(R.id.notHaveAccount);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnGoogle = findViewById(R.id.btnGoogle);
        ImageViewke = findViewById(R.id.ImageViewke);
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(SignInActivity.this);
        progressDialog.setTitle("AHMETFLIX Hesap Sistemi");
        progressDialog.setMessage("Hesap bulunuyor...");
        progressDialog.setIcon(R.drawable.applogo);
        ImageViewke.setOnLongClickListener(view -> {
            Uri linkimiz= Uri.parse("https://discord.gg/cNUJfDs4dz");
            Intent intentimiz =new Intent(Intent.ACTION_VIEW,linkimiz);
            startActivity(intentimiz);
            return false;
        });
        btnSignIn.setOnClickListener(view -> {
            if(etEmail.getText().toString().isEmpty()){
                etEmail.setError("E-mail lağzım yada Discord");
                return;
            }
            if(etPassword.getText().toString().isEmpty()){
                etPassword.setError("Yeni şifre lağzım");
            }
            else {
                progressDialog.show();
                auth.signInWithEmailAndPassword(etEmail.getText().toString().trim(), etPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignInActivity.this, "hata:" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        notHaveAccount.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}