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
import android.widget.TextView;

public class Recorrido extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textview1 = (TextView) findViewById(R.id.textView1);
        textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento_validacion = new Intent(Recorrido.this, Escaneo.class);
                startActivity(intento_validacion);
            }
        });

        TextView textview2 = (TextView) findViewById(R.id.textView2);
        textview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento_validacion = new Intent(Recorrido.this, Escaneo.class);
                startActivity(intento_validacion);
            }
        });

        TextView textview3 = (TextView) findViewById(R.id.textView3);
        textview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento_validacion = new Intent(Recorrido.this, Escaneo.class);
                startActivity(intento_validacion);
            }
        });

        TextView textview4 = (TextView) findViewById(R.id.textViewBaño);
        textview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento_validacion = new Intent(Recorrido.this, EscaneoSanitario.class);
                startActivity(intento_validacion);
            }
        });

        TextView textview5 = (TextView) findViewById(R.id.textViewSalon);
        textview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento_validacion = new Intent(Recorrido.this, Escaneo.class);
                startActivity(intento_validacion);
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
        getMenuInflater().inflate(R.menu.recorrido, menu);
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
            Intent i = new Intent(Recorrido.this, Recorrido.class);
            startActivity(i);
        }
        else if (id == R.id.nav_recorrido)
        {
            Intent i = new Intent(Recorrido.this, Recorrido.class);
            startActivity(i);
        }
        else if (id == R.id.nav_hacer_reporte)
        {
            Intent i = new Intent(Recorrido.this, NuevoReporte.class);
            startActivity(i);
        }
        else if (id == R.id.nav_ayuda)
        {
            Intent i = new Intent(Recorrido.this, Ayuda.class);
            startActivity(i);
        }
        else if (id == R.id.nav_cerrrar_sesion)
        {
            Intent i = new Intent(Recorrido.this, CerrandoSesion.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
