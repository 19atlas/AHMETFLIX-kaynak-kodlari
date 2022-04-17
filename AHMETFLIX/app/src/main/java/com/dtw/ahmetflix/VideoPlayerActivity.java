package com.dtw.ahmetflix;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;
public class VideoPlayerActivity extends AppCompatActivity {
    protected StyledPlayerView videoPlayer;
    protected ExoPlayer simpleExoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_video_player);
        videoPlayer = findViewById(R.id.exo_player);
        Toast.makeText(this, "test asamasında", Toast.LENGTH_SHORT).show();
    }
    private void setUpExoPlayer(String url){
    }
}