package com.example.a4sem_rpp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Sleep sleep = new Sleep(this);
        sleep.start();
    }

    void openMenuActivity(){
        Intent intent = new Intent(".MenuActivity");
        startActivity(intent);
        finish();
    }

}
