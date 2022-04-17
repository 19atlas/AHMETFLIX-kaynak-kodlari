package com.dtw.ahmetflix;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {
    ImageView movieImage;
    TextView movieName;
    Button playButton;
    String mName, mImage, mId, mFileUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().hide();
        movieImage = findViewById(R.id.movie_image);
        movieName = findViewById(R.id.movie_name);
        playButton = findViewById(R.id.play_button);
        mId = getIntent().getStringExtra("movieId");
        mName = getIntent().getStringExtra("movieName");
        mImage = getIntent().getStringExtra("movieImageUrl");
        mFileUrl= getIntent().getStringExtra("movieFile");
        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);
        playButton.setOnClickListener(v -> {
            Intent i = new Intent(MovieDetailsActivity.this,VideoPlayerActivity.class);
            i.putExtra("url",mFileUrl);
            i.putExtra("name",mName);
            startActivity(i);
        });
    }
}