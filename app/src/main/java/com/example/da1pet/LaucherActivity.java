package com.example.da1pet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.da1pet.home.HomeFragment;
import com.example.da1pet.user.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class LaucherActivity extends AppCompatActivity {
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laucher);
        vp = findViewById(R.id.vp);
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        BottomNavigationView bottomNavigationView = findViewById(R.id.btmnav);
        bottomNavigationView.setDrawingCacheBackgroundColor(Color.BLACK);
        vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        vp.setCurrentItem(0);
                        break;

                    case R.id.user:
                        vp.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

    }

    public class VPAdapter extends FragmentStatePagerAdapter {

        public VPAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new HomeFragment();

                case 1: return new UserFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}