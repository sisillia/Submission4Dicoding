package com.example.submissionempat.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submissionempat.model.MovieData;
import com.example.submissionempat.model.TvData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {

    private static final String API_KEY = "ca17afdd8c6d6638a7ee520a9b84ea8f";
    private MutableLiveData<ArrayList<MovieData>> listMovieData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvData>> lisTvData = new MutableLiveData<>();

    public void setListMovieData() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieData> listItemsMovie = new ArrayList<>();
        final ArrayList<TvData> listItemTv = new ArrayList<>();
        String urlMovie = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US";
        String urlTv = "https://api.themoviedb.org/3/discover/tv?api_key="+API_KEY+"language=en-US";

        client.get(urlTv, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        TvData tvData = new TvData(tv);

                        listItemTv.add(tvData);
                    }
                    lisTvData.postValue(listItemTv);

                }catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        client.get(urlMovie, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);

                        MovieData movieData = new MovieData(movie);
                        listItemsMovie.add(movieData);
                    }
                    listMovieData.postValue(listItemsMovie);

                }catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<MovieData>> getMovieData() {
        return listMovieData;
    }

    public LiveData<ArrayList<TvData>> getTVData(){
        return lisTvData;
    }
}
