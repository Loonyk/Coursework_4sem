package com.example.a4sem_rpp.products;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a4sem_rpp.MenuActivity;
import com.example.a4sem_rpp.R;

public class CreateProducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_products);
    }

    @Override    //кнопка сохранить в тулбаре
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.create_note, menu);
        return true;
    }

    @Override  //обработка клика на кнопку сохранить
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
