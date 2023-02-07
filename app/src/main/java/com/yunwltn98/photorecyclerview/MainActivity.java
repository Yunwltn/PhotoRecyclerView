package com.yunwltn98.photorecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yunwltn98.photorecyclerview.Adapter.AlbumAdapter;
import com.yunwltn98.photorecyclerview.model.Album;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final String URL = "https://block1-image-test.s3.ap-northeast-2.amazonaws.com";
    RecyclerView recyclerView;
    AlbumAdapter adapter;
    ArrayList<Album> albumArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, URL + "/photos.json", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray responseData = response.getJSONArray("data");
                            for ( int i = 0; i < responseData.length(); i++ ) {
                                JSONObject data = responseData.getJSONObject(i);

                                Album album = new Album(data.getInt("albumId"), data.getInt("id"), data.getString("title"), data.getString("url"), data.getString("thumbnailUrl"));
                                albumArrayList.add(album);
                            }

                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this,"에러발생",Toast.LENGTH_SHORT).show();
                        }

                        adapter = new AlbumAdapter(MainActivity.this, albumArrayList);
                        recyclerView.setAdapter(adapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"서버에러발생",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request);
    }
}