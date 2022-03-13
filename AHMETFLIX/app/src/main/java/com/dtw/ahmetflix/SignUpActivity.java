package com.dtw.ahmetflix;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dtw.ahmetflix.model.Users;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
public class SignUpActivity extends AppCompatActivity {
    ImageView logosupriz;
    EditText etUserName,etEmail,etPassword;
    TextView haveaccount,discordPass;
    Button btnSignUp,btnGoogle,btnSinirli;
    FirebaseAnalytics analytics;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    AlertDialog.Builder alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        logosupriz = findViewById(R.id.sulogosupriz);
        etUserName = findViewById(R.id.suUserName);
        etEmail = findViewById(R.id.suEmail);
        etPassword = findViewById(R.id.suPassword);
        haveaccount = findViewById(R.id.haveaccount);
        discordPass = findViewById(R.id.sudiscordPass);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnGoogle = findViewById(R.id.suGoogle);
        btnSinirli = findViewById(R.id.suSinirli);
        analytics = FirebaseAnalytics.getInstance(this);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        alertDialog = new AlertDialog.Builder(SignUpActivity.this);
        alertDialog.setTitle("AHMETFLIX Hesap Sistemi");
        alertDialog.setCancelable(false);
        final EditText input = new EditText(SignUpActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.setHint("Sizinle paylaşılan şifre");
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        alertDialog.setView(input);
        alertDialog.setIcon(R.drawable.applogo);
        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("AHMETFLIX Hesap Sistemi");
        progressDialog.setMessage("Hesap yaratılıyor...");
        progressDialog.setIcon(R.drawable.applogo);
        alertDialog.setPositiveButton("tamam", (dialog, which) -> {
            progressDialog.show();
            DatabaseReference mpasscode = FirebaseDatabase.getInstance().getReference().child("girisler");
            final String sifre = input.getText().toString().trim();
            mpasscode.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String passc1 = Objects.requireNonNull(snapshot.child("sifre1").getValue()).toString().trim();
                    if (passc1.equals(sifre)){
                        hesapYarat();
                        return;
                    }
                    String passc2 = Objects.requireNonNull(snapshot.child("sifre2").getValue()).toString().trim();
                    if (passc2.equals(sifre)){
                        hesapYarat();
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "Seni gidi bot git şifre bul!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SignUpActivity.this, "hata:"+error.getCode(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        });
        alertDialog.setNegativeButton("yok", (dialog, which) -> finish());
        logosupriz.setOnLongClickListener(view -> {
            Uri linkimiz= Uri.parse("https://discord.gg/cNUJfDs4dz");
            Intent intentimiz =new Intent(Intent.ACTION_VIEW,linkimiz);
            startActivity(intentimiz);
            return false;
        });
        btnSignUp.setOnClickListener(view -> {
            if(etEmail.getText().toString().isEmpty()){
                etEmail.setError("E-mail lağzım");
                return;
            }
            if(etPassword.getText().toString().isEmpty()){
                etPassword.setError("Yeni şifre lağzım");
                return;
            }
            if (etUserName.getText().toString().isEmpty()){
                etUserName.setError("Yeni kullanıcı adı gir");
            }
            else {
                alertDialog.show();
            }
        });
        haveaccount.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
            startActivity(intent);
        });
        btnSinirli.setOnClickListener(view -> {
            Intent intet = new Intent(SignUpActivity.this,MainActivity.class);
            startActivity(intet);
        });
        if (auth.getCurrentUser()!= null){
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    private void hesapYarat(){
        auth.createUserWithEmailAndPassword(etEmail.getText().toString().trim(), etPassword.getText().toString().trim()).
                addOnCompleteListener(task1 -> {
                    progressDialog.dismiss();
                    if (task1.isSuccessful()) {
                        Users user = new Users(etUserName.getText().toString().trim().toLowerCase());
                        String id = Objects.requireNonNull(task1.getResult().getUser()).getUid();
                        String rol = "izleyici";
                        database.getReference().child("Users").child(id).setValue(user);
                        database.getReference().child("Users").child(id).child("rutbe").setValue(rol);
                        Toast.makeText(SignUpActivity.this, "Kullanıcı Oluşturuldu", Toast.LENGTH_SHORT).show();
                        Intent itent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(itent);
                    } else {
                        Toast.makeText(SignUpActivity.this,task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}