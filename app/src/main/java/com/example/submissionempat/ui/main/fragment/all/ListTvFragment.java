package com.example.submissionempat.ui.main.fragment.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submissionempat.R;
import com.example.submissionempat.model.TvData;
import com.example.submissionempat.ui.main.adapter.all.ListTvShowAdapter;
import com.example.submissionempat.viewmodel.MainViewModel;

import java.util.ArrayList;

public class ListTvFragment extends Fragment {

    private ListTvShowAdapter listTvShowAdapter;
    private RecyclerView recyclerData;
    private MainViewModel mainViewModel;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_recycler_item_tv_movie, container, false);
        progressBar = view.findViewById(R.id.progressBar);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getTVData().observe(this, getTVData);

        listTvShowAdapter = new ListTvShowAdapter(getContext());
        listTvShowAdapter.notifyDataSetChanged();

        recyclerData = (RecyclerView) view.findViewById(R.id.recycler_data);
        recyclerData.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerData.setAdapter(listTvShowAdapter);


        mainViewModel.setListMovieData();
        showLoading(true);

        return view;
    }

    private Observer<ArrayList<TvData>> getTVData = new Observer<ArrayList<TvData>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvData> tvData) {
            if (tvData != null){
                listTvShowAdapter.setTvData(tvData);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
