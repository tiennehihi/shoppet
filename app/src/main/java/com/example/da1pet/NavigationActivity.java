package com.example.da1pet;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ActionMenuView;
import android.widget.SearchView;

import com.example.da1pet.DbRoom.DbRoom;
import com.example.da1pet.Model.ProductsViewModel;
import com.example.da1pet.Shop.Shoppet;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProvider;
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
    DbRoom db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = DbRoom.getInstance(this);

        setSupportActionBar(binding.appBarNavigation.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_user, R.id.nav_pass, R.id.addproduct)
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
                MenuItem menuItem5 = menu.findItem(R.id.nav_donhang);
                menuItem5.setVisible(false);
            } else {
                if (getIntent().getExtras().getString("username").equals("admin")) {
                    menu = navigationView.getMenu();
                    MenuItem menuItem1 = menu.findItem(R.id.addproduct);
                    menuItem1.setVisible(true);
                    MenuItem menuItem4 = menu.findItem(R.id.nav_Khachhang);
                    menuItem4.setVisible(true);
                    MenuItem menuItem5 = menu.findItem(R.id.nav_donhang);
                    menuItem5.setVisible(true);
                } else {
                    menu = navigationView.getMenu();
                    MenuItem menuItem1 = menu.findItem(R.id.addproduct);
                    menuItem1.setVisible(false);
                    MenuItem menuItem4 = menu.findItem(R.id.nav_Khachhang);
                    menuItem4.setVisible(false);
                    MenuItem menuItem5 = menu.findItem(R.id.nav_donhang);
                    menuItem5.setVisible(false);
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
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        menu.findItem(R.id.btngiohang).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (getIntent().getExtras().getString("username").equals("")){
                    Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(NavigationActivity.this, Shoppet.class);
                    intent.putExtra("idcart",getIntent().getExtras().getString("username"));
                    startActivity(intent);
                }
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ProductsViewModel model = new ViewModelProvider(NavigationActivity.this).get(ProductsViewModel.class);
                model.setListProduct(db.productsDAO().getSearch("%" + newText + "%"));
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}