package com.yunwltn98.photorecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunwltn98.photorecyclerview.model.Album;

public class imageActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = findViewById(R.id.imageView);

        Album album = (Album) getIntent().getSerializableExtra("album");

        Glide.with(imageActivity.this).load(album.url).into(imageView);
    }
}