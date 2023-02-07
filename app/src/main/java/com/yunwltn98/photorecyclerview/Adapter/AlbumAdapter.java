package com.yunwltn98.photorecyclerview.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yunwltn98.photorecyclerview.MainActivity;
import com.yunwltn98.photorecyclerview.R;
import com.yunwltn98.photorecyclerview.imageActivity;
import com.yunwltn98.photorecyclerview.model.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> albumArrayList;
    String URL;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_row, parent, false);
        return new AlbumAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        URL = album.url;
        holder.txtTitle.setText(album.title);
        holder.txtId.setText("id : " + album.id);
        holder.txtAlbumId.setText("albumId : " + album.albumId);
        Glide.with(context).load(album.thumbnailUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtId;
        TextView txtAlbumId;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtId = itemView.findViewById(R.id.txtId);
            txtAlbumId = itemView.findViewById(R.id.txtAlbumId);
            imageView = itemView.findViewById(R.id.imageView);

            // 이미지뷰 눌렀을때 처리
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    Album album = albumArrayList.get(index);

                    Intent intent = new Intent(context, imageActivity.class);
                    intent.putExtra("album", album);
                    context.startActivity(intent);
                }
            });
        }
    }
}
