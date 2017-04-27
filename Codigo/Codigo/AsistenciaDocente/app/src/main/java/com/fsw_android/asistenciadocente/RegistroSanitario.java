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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistroSanitario extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_sanitario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn_nuevo_incorrecto = (Button) findViewById(R.id.btn_nuevo_cancelar);
        btn_nuevo_incorrecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento_validacion = new Intent(RegistroSanitario.this, RegistroSanitarioCorrecto.class);
                startActivity(intento_validacion);
            }
        });

        Button btn_nuevo_correcto = (Button) findViewById(R.id.btn_nuevo_aceptar);
        btn_nuevo_correcto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento_validacion = new Intent(RegistroSanitario.this, RegistroSanitarioCorrecto.class);
                startActivity(intento_validacion);
            }
        });


        Spinner spinner_edificio = (Spinner) findViewById(R.id.spinner_edificio);
        String[] valores_edificio = {"Uno","Dos","Tres","Cuatro","Cinco","Seis", "Siete", "Ocho", "Nueve", "Diez","Once"};
        spinner_edificio.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores_edificio));
        spinner_edificio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();}
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {// vacio//
            }
        });

        Spinner spinner_nivel = (Spinner) findViewById(R.id.spinner_nivel);
        String[] valores_nivel = {"Uno","Dos","Tres"};
        spinner_nivel.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores_nivel));
        spinner_nivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();}
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {// vacio//
            }
        });

        Spinner spinner_grupo = (Spinner) findViewById(R.id.spinner_grupo);
        String[] valores_grupo = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17"};
        spinner_grupo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores_grupo));
        spinner_grupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();}
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {// vacio//
            }
        });


        Spinner spinner_hora = (Spinner) findViewById(R.id.spinner_hora);
        String[] valores_hora = {"M1","M2","M3","M4","M5","M6","V1","V2","V3","V4","V5","V6","N1","N2","N3","N4","N5","N6"};
        spinner_hora.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores_hora));
        spinner_hora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();}
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {// vacio//
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
        getMenuInflater().inflate(R.menu.registro_sanitario, menu);
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
            Intent i = new Intent(RegistroSanitario.this, Recorrido.class);
            startActivity(i);
        }
        else if (id == R.id.nav_recorrido)
        {
            Intent i = new Intent(RegistroSanitario.this, Recorrido.class);
            startActivity(i);
        }
        else if (id == R.id.nav_hacer_reporte)
        {
            Intent i = new Intent(RegistroSanitario.this, NuevoReporte.class);
            startActivity(i);
        }
        else if (id == R.id.nav_ayuda)
        {
            Intent i = new Intent(RegistroSanitario.this, Ayuda.class);
            startActivity(i);
        }
        else if (id == R.id.nav_cerrrar_sesion)
        {
            Intent i = new Intent(RegistroSanitario.this, CerrandoSesion.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
