package com.example.submissionempat.ui.main;
import android.content.Intent;
import android.provider.Settings;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.submissionempat.R;
import com.example.submissionempat.ui.main.adapter.TabListAdapter;
import com.example.submissionempat.ui.main.fragment.ListAllFragment;
import com.example.submissionempat.ui.main.fragment.ListFavoriteFragment;
import com.example.submissionempat.ui.main.fragment.ListMovieFragment;
import com.example.submissionempat.ui.main.fragment.ListTvFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabListAdapter tabListAdapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new ListAllFragment());

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_main);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.home_menu:
                fragment = new ListAllFragment();
                break;
            case R.id.favorite_menu:
                fragment = new ListFavoriteFragment();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_change_settings){
            Intent setting = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(setting);
        }

        return super.onOptionsItemSelected(item);
    }


}
