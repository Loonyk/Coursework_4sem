package com.example.a4sem_rpp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.a4sem_rpp.DAO.NotesDAO;
import com.example.a4sem_rpp.DAO.ProductsDAO;
import com.example.a4sem_rpp.modelDB.Notes;
import com.example.a4sem_rpp.modelDB.Products;

@Database(entities = {Notes.class, Products.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase db;
    public abstract NotesDAO getNotesDao();
    public abstract ProductsDAO getProductsDao();


    public static AppDatabase getDatabase(Context context)
    {
        if(db == null)
        {
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database").build();
        }
        return db;
    }

}
