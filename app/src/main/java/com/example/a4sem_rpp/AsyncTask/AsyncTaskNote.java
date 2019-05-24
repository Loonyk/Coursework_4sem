package com.example.a4sem_rpp.AsyncTask;

import android.os.AsyncTask;
import android.view.MenuItem;

import com.example.a4sem_rpp.MenuActivity;
import com.example.a4sem_rpp.db.AppDatabase;
import com.example.a4sem_rpp.modelDB.Notes;
import com.example.a4sem_rpp.notes.ListAdapter;

import java.util.List;

public class AsyncTaskNote extends AsyncTask<MenuActivity, Void, Void> {

    @Override
    protected Void doInBackground(MenuActivity... ltAdap){

        MenuActivity listAdapter = ltAdap[0];
        AppDatabase database = AppDatabase.getDatabase(listAdapter);
        List<Notes> list = database.getNotesDao().getAllNotes();
        // List<Notes> list = database.getNotesDao().getNotesId(ListAdapter.current_position);
        listAdapter.openNotes(list);
        /*
        Notes nt=new Notes();
        nt.setNote("fddfdf");
        nt.setTitle("gggg");
        database.getNotesDao().insertAll(nt);
*/
      //  listAdapter.

        return null;
    }
}
