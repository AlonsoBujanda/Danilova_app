package com.example.danilova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.danilova.placeholder.LoginActivity;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    ViewFlipper v_flipper;
    @SuppressLint("MissingInflatedId")
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
        int images[] = {R.drawable.portada_academia,R.drawable.danilova,R.drawable.mario1,R.drawable.zapatillas_ballet,R.drawable.mario2,R.drawable.logorojo};

        v_flipper = findViewById(R.id.v_flipper);
        /*setencia para validacion*/
        for (int image:images){
            flipperImages(image);
        }
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        /*se estable la duracion de las imagenes 1000=1segundo
         */
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);
        /* activacion de la animacion*/
        v_flipper.setInAnimation(this, android.R.anim.slide_out_right);
        v_flipper.setOutAnimation(this,android.R.anim.slide_in_left);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        int itemId = Item.getItemId();
        if (itemId == R.id.nav_home) { /*Regreso siempre al inicio de la app*/
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        } else if (itemId == R.id.nav_bailes){
            Intent intent = new Intent(MainActivity.this, BailesActivity.class);
            startActivity(intent);


        } else if(itemId == R.id.nav_horarios) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HorariosFragment()).addToBackStack(null).commit();


        }else if(itemId == R.id.nav_contacto) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).addToBackStack(null).commit(); /*addtobacksatck agrega a la fila la pantalla anterior para regresar*/

/*Cerrar sesion*/
        }else if (itemId == R.id.nav_logout) {
            Toast.makeText(this, "Cerrar Sesion", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(String.valueOf(LoginActivity.class));
            startActivity(intent);

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