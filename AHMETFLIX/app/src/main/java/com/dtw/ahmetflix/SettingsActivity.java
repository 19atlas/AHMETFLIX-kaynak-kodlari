package com.dtw.ahmetflix;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.dtw.ahmetflix.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
public class SettingsActivity extends AppCompatActivity {
    EditText kulAdi,eStatus,istek;
    ImageView backArrow,plus,pph,ppsil,aracDume;
    TextView otrmkpa,developerlar,hessil,rolTe;
    LinearLayout aracTakimi;
    FirebaseDatabase database;
    FirebaseUser user;
    FirebaseAuth auth;
    FirebaseStorage storage;
    AppCompatButton save;
    ActivityResultLauncher<String> mGetContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();
        pph = (ImageView) findViewById(R.id.pph);
        ppsil = (ImageView) findViewById(R.id.ppsil);
        kulAdi = findViewById(R.id.kulAdi);
        aracTakimi = (LinearLayout)findViewById(R.id.aracTakimi);
        eStatus = findViewById(R.id.eStatus);
        istek = findViewById(R.id.istek);
        developerlar = findViewById(R.id.developerlar);
        otrmkpa = findViewById(R.id.otrmkapa);
        hessil = findViewById(R.id.hessil);
        backArrow = findViewById(R.id.backArrow);
        aracDume = (ImageView)findViewById(R.id.aracDume);
        save = findViewById(R.id.saveButton);
        auth = FirebaseAuth.getInstance();
        plus = findViewById(R.id.plus);
        rolTe = findViewById(R.id.rolu);
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        veriAlmak();
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
            if (result!=null){
                pph.setImageURI(result);
                final StorageReference reference = storage.getReference().child("pics").child(auth.getUid());
                reference.putFile(result).addOnSuccessListener(taskSnapshot -> reference.getDownloadUrl().addOnSuccessListener(uri -> {
                    database.getReference().child("Users").child(auth.getUid()).child("profilepic").setValue(uri.toString());
                    Toast.makeText(SettingsActivity.this, "foto yüklendi", Toast.LENGTH_SHORT).show();
                    plus.setVisibility(View.INVISIBLE);
                }));
            }
        });
        hessil.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);
            dialog.setTitle("AHMETFLIX Hesap Sistemi");
            dialog.setMessage("Hesabın silinsin mi?");
            dialog.setIcon(R.drawable.applogo);
            dialog.setPositiveButton("tamam", (dialogInterface, i) -> {
                String id = user.getUid();
                database.getReference().child("Users").child(id).removeValue();
                user.delete().addOnCompleteListener(task -> {
                    Toast.makeText(SettingsActivity.this, "hesabın silindi", Toast.LENGTH_LONG).show();
                    auth.signOut();
                    Intent iny = new Intent(SettingsActivity.this,SignUpActivity.class);
                    iny.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(iny);
                }).addOnFailureListener(e -> {
                    Toast.makeText(SettingsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                });
            }).show();
        });
        backArrow.setOnClickListener(view -> onBackPressed());
        otrmkpa.setOnClickListener(view -> {
            AlertDialog.Builder dialo = new AlertDialog.Builder(SettingsActivity.this);
            dialo.setTitle("AHMETFLIX Hesap Sistemi");
            dialo.setMessage("Hesabın çıkış yapılsın mı?");
            dialo.setIcon(R.drawable.applogo);
            dialo.setPositiveButton("tamam", (dialogInterface, i) -> {
                auth.signOut();
                Intent ins = new Intent(SettingsActivity.this,SignInActivity.class);
                ins.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ins);
            }).show();
        });
        ppsil.setOnClickListener(view -> {
            AlertDialog.Builder diallo = new AlertDialog.Builder(SettingsActivity.this);
            diallo.setTitle("AHMETFLIX Dosya İşlem");
            diallo.setMessage("Profil Resmi Silinsin mi?").setIcon(R.drawable.applogo).setPositiveButton("sil", (dialogInterface, i) -> {
                StorageReference ppDosya = storage.getReference().child("pics").child(auth.getUid());
                ppDosya.delete().addOnSuccessListener(unused -> {
                    database.getReference().child("Users").child(auth.getUid()).child("profilepic").removeValue();
                    Toast.makeText(SettingsActivity.this, "pp silindi", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                });
            }).setNegativeButton("kalsın",null).show();
        });
        aracDume.setOnClickListener(view -> {
            if (aracTakimi.getVisibility() != View.VISIBLE){
                aracDume.setImageDrawable(getResources().getDrawable(R.drawable.aracdumkapama, getApplicationContext().getTheme()));
                aracTakimi.setVisibility(View.VISIBLE);
            }else {
                aracDume.setImageDrawable(getResources().getDrawable(R.drawable.aracdumacmak, getApplicationContext().getTheme()));
                aracTakimi.setVisibility(View.GONE);
            }
        });
        save.setOnClickListener(view -> {
            String durum = eStatus.getText().toString().trim().toLowerCase();
            String username = kulAdi.getText().toString().trim().toLowerCase();
            String iste = istek.getText().toString().trim();
            veriAlmak();
            if (username.isEmpty()||username.equals("ㅤ")){
                kulAdi.setError("boş olamaz");
                return;
            }else {
                HashMap<String ,Object> obj = new HashMap<>();
                obj.put("adi",username);
                obj.put("durum",durum);
                obj.put("istek",iste);
                database.getReference().child("Users").child(auth.getUid()).updateChildren(obj).addOnSuccessListener(unused -> Toast.makeText(SettingsActivity.this, "Başarıyla güncellendi", Toast.LENGTH_SHORT).show());
            }
        });
        plus.setOnClickListener(view -> mGetContent.launch("image/*"));
    }
    private void veriAlmak(){
        database.getReference().child("Users").child(auth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users users = snapshot.getValue(Users.class);
                        Glide.with(getApplicationContext())
                                .load(users.getprofilepic())
                                .placeholder(R.drawable.applogo)
                                .into(pph);
                        kulAdi.setText(users.getAdi());
                        eStatus.setText(users.getDurum());
                        istek.setText(users.getIstek());
                        rolTe.setText("Rutbe: "+users.getRutbe());
                        if (users.getprofilepic()!=null) {
                            ppsil.setVisibility(View.VISIBLE);
                            plus.setVisibility(View.INVISIBLE);
                        }else {
                            plus.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error){}
                });
    }
}