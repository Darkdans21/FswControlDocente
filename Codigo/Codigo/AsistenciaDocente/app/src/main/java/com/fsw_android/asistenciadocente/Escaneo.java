package com.fsw_android.asistenciadocente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Escaneo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaneo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn_barras = (Button) findViewById(R.id.codigo_barra_button);
        btn_barras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Escaneo.this , RegistroProfesor.class);
                startActivity(i);
            }
        });

        Button btn_codigo_qr = (Button) findViewById(R.id.codigo_qr_button);
        btn_codigo_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Escaneo.this , RegistroProfesor.class);
                startActivity(i);
            }
        });

        ImageButton ImageButton_barras = (ImageButton) findViewById(R.id.imageView_barras);
        ImageButton_barras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Escaneo.this , RegistroProfesor.class);
                startActivity(i);
            }
        });

        ImageButton ImageButton_qr = (ImageButton) findViewById(R.id.imageViewQR);
        ImageButton_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Escaneo.this , RegistroProfesor.class);
                startActivity(i);
            }
        });






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.escaneo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notificaciones)
        {
            Intent i = new Intent(Escaneo.this, Recorrido.class);
            startActivity(i);
        }
        else if (id == R.id.nav_recorrido)
        {
            Intent i = new Intent(Escaneo.this, Recorrido.class);
            startActivity(i);
        }
        else if (id == R.id.nav_hacer_reporte)
        {
            Intent i = new Intent(Escaneo.this, NuevoReporte.class);
            startActivity(i);
        }
        else if (id == R.id.nav_ayuda)
        {
            Intent i = new Intent(Escaneo.this, Ayuda.class);
            startActivity(i);
        }
        else if (id == R.id.nav_cerrrar_sesion)
        {
            Intent i = new Intent(Escaneo.this, CerrandoSesion.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
