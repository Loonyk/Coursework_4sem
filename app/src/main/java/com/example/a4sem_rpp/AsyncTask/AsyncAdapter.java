package com.example.a4sem_rpp.AsyncTask;

import android.os.AsyncTask;

import com.example.a4sem_rpp.MenuActivity;
import com.example.a4sem_rpp.db.AppDatabase;
import com.example.a4sem_rpp.modelDB.Notes;
import com.example.a4sem_rpp.notes.ListAdapter;

import java.util.List;

public class AsyncAdapter extends AsyncTask<ListAdapter, Void, Void> {

    @Override
    protected Void doInBackground(ListAdapter... listAdapters) {

        ListAdapter lAdapter = listAdapters[0];
        AppDatabase database = AppDatabase.getDatabase(lAdapter.context);

        Notes nt = lAdapter.getNotes();
        nt.setTitle(lAdapter.getTitleEt().toString());
        nt.setNote(lAdapter.getTextEt().toString());
        database.getNotesDao().Update(nt);
        List<Notes> list = database.getNotesDao().getNotesId(lAdapter.current_position);
        lAdapter.load_notes(list);

        return null;


    }
}
// nt.setNote(lAdapter.getTextEt().getText().toString());
// nt.setTitle(lAdapter.getTitleEt().getText().toString());
// List<Notes> list  = database.getNotesDao().getNotesTitleText(nt.getTitle(), nt.getNote());