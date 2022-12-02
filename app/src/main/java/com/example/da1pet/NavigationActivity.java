package com.example.da1pet;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.da1pet.databinding.ActivityNavigationBinding;

public class NavigationActivity extends AppCompatActivity {
    Menu menu;
    MenuItem menuItem;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigation.toolbar);
        binding.appBarNavigation.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_user,  R.id.nav_pass, R.id.addproduct)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        try {
            if (getIntent().getExtras().getString("username").equals("")) {
                menu = navigationView.getMenu();
                menuItem = menu.findItem(R.id.nav_user);
                menuItem.setVisible(false);
                MenuItem menuItem1 = menu.findItem(R.id.addproduct);
                menuItem1.setVisible(false);
                MenuItem menuItem2 = menu.findItem(R.id.nav_pass);
                menuItem2.setVisible(false);
                MenuItem menuItem3 = menu.findItem(R.id.nav_logout);
                menuItem3.setVisible(false);
                MenuItem menuItem4 = menu.findItem(R.id.nav_Khachhang);
                menuItem4.setVisible(false);
            } else {
                if (getIntent().getExtras().getString("username").equals("admin")) {
                    menu = navigationView.getMenu();
                    MenuItem menuItem1 = menu.findItem(R.id.addproduct);
                    menuItem1.setVisible(true);
                    MenuItem menuItem4 = menu.findItem(R.id.nav_Khachhang);
                    menuItem4.setVisible(true);
                } else {
                    menu = navigationView.getMenu();
                    MenuItem menuItem1 = menu.findItem(R.id.addproduct);
                    menuItem1.setVisible(false);
                    MenuItem menuItem4 = menu.findItem(R.id.nav_Khachhang);
                    menuItem4.setVisible(false);
                }
                menu = navigationView.getMenu();
                menuItem = menu.findItem(R.id.nav_login);
                menuItem.setVisible(false);
                MenuItem menuItem2 = menu.findItem(R.id.nav_pass);
                menuItem2.setVisible(true);
                MenuItem menuItem3 = menu.findItem(R.id.nav_logout);
                menuItem3.setVisible(true);
            }
        } catch (Exception e) {
            e.getMessage();
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_login) {
                    Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.nav_logout) {
                    Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    NavigationUI.onNavDestinationSelected(item, navController);
                    drawer.closeDrawers();
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}