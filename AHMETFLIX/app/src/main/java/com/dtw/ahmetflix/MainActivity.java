package com.dtw.ahmetflix;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
public class MainActivity extends AppCompatActivity {
    ImageView settings,aramaDume;
    FirebaseAuth auth;
    FirebaseAnalytics anal;
    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = findViewById(R.id.settings);
        aramaDume = findViewById(R.id.aramaDume);
        anal= FirebaseAnalytics.getInstance(this);
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        settings.setOnClickListener(view -> {
            if (auth.getCurrentUser()!=null){
                Intent git = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(git);
            }else {
                Toast.makeText(this, "giriş hakkın yok", Toast.LENGTH_SHORT).show();
            }
        });
        settings.setOnLongClickListener(view -> {
            Toast.makeText(MainActivity.this,versionName+" kod:"+versionCode , Toast.LENGTH_SHORT).show();
            return false;
        });
    }
    boolean zorlayici = false;
    @Override
    public void onBackPressed() {
        if (zorlayici) {super.onBackPressed();return;}
        this.zorlayici = true;
        Toast.makeText(this, "Çıkmak için tekrar basın", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> zorlayici =false, 2000);
    }
}