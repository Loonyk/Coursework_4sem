package com.example.a4sem_rpp.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a4sem_rpp.R;
import com.example.a4sem_rpp.modelDB.Notes;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    public ListView list;

    ArrayList<Notes> lists;
    public LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //для списка
        lists = (ArrayList<Notes>)getIntent().getExtras().getSerializable("list");
        list = (ListView)findViewById(R.id.notesListView);
        ListAdapter adapter = new ListAdapter(this,lists);
        list.setAdapter(adapter);

        //обработка клика по активной кнопке
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, CreateNotes.class);
                startActivity(intent);

            }
        });

        //onClickActBtn();
    }

    //обработчик нажатий на список
//    public void onClickActBtn(){
//        list = (ListView)findViewById(R.id.notesListView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_notes);
//        list.setAdapter(adapter);
//
//        list.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }



