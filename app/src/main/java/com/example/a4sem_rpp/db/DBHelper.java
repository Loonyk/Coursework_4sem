package com.example.a4sem_rpp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Наследник SQLiteOpenHelper, помогающий создавать и обновлять БД
 */
public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context){
        super(context,    //контекст
                DBNotesConstract.DB_NAME, //имя БД
                null, //для курсор фактори, не используем
                DBNotesConstract.DB_VERSION    //версия бд
        );
    }

    @Override
    /**
     * Вызывается если БД не существует и ее нужно создавать
     */
    public void onCreate(SQLiteDatabase db){
        for (String query : DBNotesConstract.CREATE_DATABASE_QUERIES) {
            db.execSQL(query);
        }

    }
    /**
     * Вызывается при смене версии БД
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
