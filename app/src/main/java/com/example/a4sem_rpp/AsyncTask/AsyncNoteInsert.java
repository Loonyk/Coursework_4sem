package com.example.a4sem_rpp.AsyncTask;

import android.os.AsyncTask;

import com.example.a4sem_rpp.MenuActivity;
import com.example.a4sem_rpp.db.AppDatabase;
import com.example.a4sem_rpp.modelDB.Notes;
import com.example.a4sem_rpp.notes.CreateNotes;

import java.util.List;

public class AsyncNoteInsert extends AsyncTask<CreateNotes,Void,Void> {
    @Override
    public Void doInBackground(CreateNotes... createNotes){


        CreateNotes listAdapter = createNotes[0];
        AppDatabase database = AppDatabase.getDatabase(listAdapter);

        Notes nt=new Notes();
        nt.setNote(listAdapter.getTextEt().getText().toString());
        nt.setTitle(listAdapter.getTitleEt().getText().toString());
        database.getNotesDao().insertAll(nt);
        List<Notes> l=database.getNotesDao().getAllNotes();
        listAdapter.openNotes(l);
        return  null;
    }
}
