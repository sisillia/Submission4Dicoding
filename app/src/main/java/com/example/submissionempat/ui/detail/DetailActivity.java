package com.example.submissionempat.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.submissionempat.R;
import com.example.submissionempat.db.FavoriteHelper;
import com.example.submissionempat.model.MovieData;
import com.example.submissionempat.model.TvData;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    CircleImageView imgFilm;
    TextView titleFilm, descOfFilm;
    public static final String EXTRA_DATA = "extra_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Film Detail");

        imgFilm = (CircleImageView) findViewById(R.id.img_film);
        titleFilm = (TextView) findViewById(R.id.tv_title_film);

        descOfFilm = (TextView) findViewById(R.id.tv_desc_film);

        MovieData movieData = getIntent().getParcelableExtra(EXTRA_DATA);

        titleFilm.setText(movieData.getTitle());
        descOfFilm.setText(movieData.getOverview());

        Glide.with(this)
                .load(movieData.getPoster_path())
                .into(imgFilm);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.favorite){

        }
        return super.onOptionsItemSelected(item);
    }
}
