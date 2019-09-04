package com.example.submissionempat.ui.detail;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.submissionempat.R;
import com.example.submissionempat.db.DatabaseContract;
import com.example.submissionempat.db.DatabaseHelper;
import com.example.submissionempat.db.FavoriteHelper;
import com.example.submissionempat.model.MovieData;
import com.example.submissionempat.model.TvData;
import com.example.submissionempat.viewmodel.MainViewModel;

import static com.example.submissionempat.db.DatabaseContract.*;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView imgFilm;
    private TextView titleFilm, descOfFilm;
    ImageView imgFavorite;
    private String imgUrl;
    private int id;

    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_FAVORITES = "extra_favorites";
    public static final String EXTRA_POSITION = "extra_position";

    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 101;

    private final int ALERT_DIALOG_DELETE = 20;
    public static final int RESULT_DELETE = 301;

    private MovieData movieData;
    private FavoriteHelper favoriteHelper;
    private DatabaseHelper databaseHelper;
    private MainViewModel mainViewModel;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Film Detail");

//        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//        mainViewModel.getCheckFavorite(this, movieData.getId() ).observe(this,getCheckMovie);

        imgFilm = (CircleImageView) findViewById(R.id.img_circle);
        titleFilm = (TextView) findViewById(R.id.tv_title_film);
        descOfFilm = (TextView) findViewById(R.id.tv_desc_film);
        imgFavorite = (ImageView) findViewById(R.id.img_favorite);

        if(FavoriteHelper.getInstance(this).isFavorite(id)) {

        }

        movieData = getIntent().getParcelableExtra(EXTRA_DATA);


        titleFilm.setText(movieData.getTitle());
        descOfFilm.setText(movieData.getOverview());
        imgUrl = movieData.getPoster_path();
        id = movieData.getId();

        Glide.with(this)
                .load(movieData.getPoster_path())
                .into(imgFilm);

        imgFavorite.setOnClickListener(this);

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());

        MovieFavorite();
    }

    private Observer<? super MovieData> getCheckMovie = new Observer<MovieData>() {
        @Override
        public void onChanged(MovieData movieData) {

        }
    };


    private void MovieFavorite() {
        movieData = getIntent().getParcelableExtra(EXTRA_FAVORITES);
        if(movieData != null){
            position = getIntent().getIntExtra(EXTRA_POSITION,0);
//            isFavorite = true;
        } else {
            movieData = new MovieData();
        }



//        if (isFavorite){
//            imgFilm.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_favorite_black_24dp));
//            if(movieData != null){
//                titleFilm.setText(movieData.getTitle());
//                descOfFilm.setText(movieData.getOverview());
//
//                Glide.with(this)
//                        .load(movieData.getPoster_path())
//                        .into(imgFilm);
//            }
//        } else {
//            imgFilm.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_favorite_border_black_24dp));
//        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.img_favorite){


            if(FavoriteHelper.getInstance(this).isFavorite(id)) {
                showAlertDialog(ALERT_DIALOG_DELETE);
            } else {
                String title = titleFilm.getText().toString().trim();
                String desc = descOfFilm.getText().toString().trim();

                movieData.setTitle(title);
                movieData.setOverview(desc);
                movieData.setPoster_path(imgUrl);
                movieData.setId(id);

                Intent intent = new Intent();
                intent.putExtra(EXTRA_FAVORITES,movieData);
                intent.putExtra(EXTRA_POSITION,0);
            }

//            if(isFavorite){
//                showAlertDialog(ALERT_DIALOG_DELETE);
//            } else {
//                long result = favoriteHelper.insertNote(movieData);
//
//                if (result > 0 ){
//                    movieData.setId((int)result);
//                    setResult(RESULT_ADD, intent);
//                    finish();
//                } else {
//                    Toast.makeText(DetailActivity.this, "Gagal menambahkan ke favorite", Toast.LENGTH_SHORT).show();
//                }
//            }
        }
    }

    private void showAlertDialog(int type) {

        String dialogTitle, dialogMessage;

        dialogMessage = "Apakah anda yakin ingin menghapus item ini dari favorite?";
        dialogTitle = "Hapus Note";

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        long result = favoriteHelper.deleteNote(movieData.getId());
                        if (result > 0) {
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_POSITION, position);
                            setResult(RESULT_DELETE, intent);
                            finish();
                        } else {
                            Toast.makeText(DetailActivity.this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
