package com.example.submissionempat.ui.main.fragment.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.submissionempat.R;
import com.example.submissionempat.ui.main.adapter.TabListAdapter;
import com.google.android.material.tabs.TabLayout;

public class ListAllFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabListAdapter tabListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_all_fragment, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        tabListAdapter = new TabListAdapter(getFragmentManager());
        tabListAdapter.addFragment(new ListMovieFragment(), getResources().getString(R.string.title_fragment_movie));
        tabListAdapter.addFragment(new ListTvFragment(), getResources().getString(R.string.title_fragment_tvshow));

        viewPager.setAdapter(tabListAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
