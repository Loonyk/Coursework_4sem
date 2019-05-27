package com.example.a4sem_rpp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.a4sem_rpp.AsyncTask.AsyncTaskNote;
import com.example.a4sem_rpp.AsyncTask.AsyncTaskProd;
import com.example.a4sem_rpp.modelDB.Notes;
import com.example.a4sem_rpp.modelDB.Products;
import com.example.a4sem_rpp.products.CreateProducts;
import com.example.a4sem_rpp.products.ProductsFragmentList;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProductsFragmentList prodFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        prodFrag = new ProductsFragmentList();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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

        /** Для работы с фрагментами*/
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.products) {
            fragmentTransaction.replace(R.id.container, prodFrag);
            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MenuActivity.this, CreateProducts.class);
                    startActivity(intent);
                }
            });
            AsyncTaskProd asP = new AsyncTaskProd();
            asP.execute(this);


        } else if (id == R.id.sorting) {

        } else if (id == R.id.notes) {
            //fragmentTransaction.replace(R.id.container, noteFragment);
            AsyncTaskNote as = new AsyncTaskNote();
            as.execute(this);


        } else if (id == R.id.searching) {

        }else if (id == R.id.exit) {
            openQuitApp();
        } fragmentTransaction.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*My function*/
    public void openNotes(List<Notes> l) {
        Intent intent = new Intent(".notes.NotesActivity");
        intent.putExtra("list",(ArrayList)l);
        startActivity(intent);
    }

    /** !!!!!!!!*/
    public void openProducts(List<Products> prod) {
        Intent intent = new Intent(MenuActivity.this, ProductsFragmentList.class);
        intent.putExtra("list",(ArrayList)prod);
        startActivity(intent);
    }

    private void openQuitApp(){   // кнопка Выход
        AlertDialog.Builder quitApp = new AlertDialog.Builder(MenuActivity.this);
        quitApp.setTitle("Вы уверены, что хотите выйти?");
        quitApp.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        quitApp.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        quitApp.show();
    }

}
