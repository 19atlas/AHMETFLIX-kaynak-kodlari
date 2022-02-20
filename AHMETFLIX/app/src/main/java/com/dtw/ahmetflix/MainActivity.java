package com.dtw.ahmetflix;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;
public class MainActivity extends AppCompatActivity {
    ImageView settings;
    EditText videobulucu;
    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = findViewById(R.id.settings);
        videobulucu = findViewById(R.id.videobulucu);
        settings.setOnClickListener(view -> {
            Intent git = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(git);
        });
        settings.setOnLongClickListener(view -> {
            Toast.makeText(MainActivity.this,versionName+" kod:"+versionCode , Toast.LENGTH_SHORT).show();
            return false;
        });
    }
}