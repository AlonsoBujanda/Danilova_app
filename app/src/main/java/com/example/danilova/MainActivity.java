package com.example.danilova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment());
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }
    /*public void irahome (View view){
        Intent intent = new Intent(this, HomeFragment.class);
        startActivity(intent);
    }*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        int itemId = Item.getItemId();
        if (itemId == R.id.nav_home) { /*Regreso siempre al inicio de la app*/
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        } else if (itemId == R.id.nav_bailes){
            Intent intent = new Intent(MainActivity.this, BailesActivity.class);
            startActivity(intent);


        } else if(itemId == R.id.nav_share) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).addToBackStack(null).commit();


        }else if(itemId == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).addToBackStack(null).commit(); /*addtobacksatck agrega a la fila la pantalla anterior para regresar*/


        }else if (itemId == R.id.nav_logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}