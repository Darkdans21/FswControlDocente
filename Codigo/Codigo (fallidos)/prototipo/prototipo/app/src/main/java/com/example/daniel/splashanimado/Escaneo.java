package com.example.daniel.splashanimado;

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

import static com.example.daniel.splashanimado.R.id.btn_codigo_barras;
import static com.example.daniel.splashanimado.R.id.btn_iniciar_sesion;
import static com.example.daniel.splashanimado.R.id.btn_qr;
import static com.example.daniel.splashanimado.R.id.imageButton_codigo_barras;
import static com.example.daniel.splashanimado.R.id.imageButton_qr;
import static com.example.daniel.splashanimado.R.id.start;

public class Escaneo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaneo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button btn_barras = (Button) findViewById(btn_codigo_barras);
        btn_barras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Escaneo.this , Registro_docentes.class);
                startActivity(i);
            }
        });

        Button btn_codigo_qr = (Button) findViewById(btn_qr);
        btn_codigo_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Escaneo.this , Registro_sanitarios.class);
                startActivity(i);
            }
        });

        ImageButton ImageButton_barras = (ImageButton) findViewById(imageButton_codigo_barras);
        ImageButton_barras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Escaneo.this , Registro_docentes.class);
                startActivity(i);
            }
        });

        ImageButton ImageButton_qr = (ImageButton) findViewById(imageButton_qr);
        ImageButton_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Escaneo.this , Registro_sanitarios.class);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notificaciones)
        {
            Intent i = new Intent(Escaneo.this, Eventos.class);
            startActivity(i);
        }
        else if (id == R.id.nav_evento_maestros)
        {
            Intent i = new Intent(Escaneo.this, Evento_maestros.class);
            startActivity(i);
        }
        else if (id == R.id.nav_evento_baños)
        {
            Intent i = new Intent(Escaneo.this, Evento_sanitarios.class);
            startActivity(i);
        }
        else if (id == R.id.nav_hacer_reporte)
        {
            Intent i = new Intent(Escaneo.this, Escaneo.class);
            startActivity(i);
        }
        else if (id == R.id.nav_ayuda)
        {
            Intent i = new Intent(Escaneo.this, MainActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_cerrrar_sesion)
        {
            Intent i = new Intent(Escaneo.this, Login.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
