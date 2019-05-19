package com.example.a4sem_rpp.db;


import android.net.Uri;
import android.provider.BaseColumns;

/** для описания всей БД (таблицы, названия) **/

public final class DBNotesConstract {

    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 1;

    public static final String AUTHORITY = "com.example.a4sem_rpp.provider"; //для contentProvider
    public static final String URI = "content://" + AUTHORITY;   //базовый ури для доступа к контенту


    /**
     * массив запросов, требующиеся для создания БД
     */
    public static final String[] CREATE_DATABASE_QUERIES = {
            Notes.CREATE_TABLE,
            Notes.CREATE_UPDATED_TS_INDEX,
    };

    private DBNotesConstract(){

    }

    /**
     * Описание таблицы с заметками
     */

    public static abstract class Notes implements BaseColumns {

        /**
         * интерфейс BaseColumns уже определены константы _ID и _COUNT
         */

        public static final String TABLE_NAME = "notes";

        public static final Uri URI = Uri.parse(DBNotesConstract.URI
        + "/" + TABLE_NAME);

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_CREATED_TS = "created_ts";
        public static final String COLUMN_UPDATED_TS = "updated_ts";

        /**
         * запрос для создания таблицы
         */
        public static final String CREATE_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL);",
                TABLE_NAME,
                _ID,
                COLUMN_TITLE,
                COLUMN_NOTE,
                COLUMN_CREATED_TS,
                COLUMN_UPDATED_TS,
                _COUNT);

        /**
         * запрос для создания индексов
         */
        public static final String CREATE_UPDATED_TS_INDEX = String.format("CREATE INDEX updated_ts_index " +
                        "ON %s (%s);",
                TABLE_NAME,
                COLUMN_UPDATED_TS);

        /**
         * Столбцы, которые будем выбирать
         */

        public static final String[] LIST_PROJECTION = {
                _ID,
                COLUMN_TITLE,
                COLUMN_CREATED_TS,
                COLUMN_UPDATED_TS
        };

        public static final String[] SINGLE_PROJECTION = {
                _ID,
                COLUMN_TITLE,
                COLUMN_NOTE,
                COLUMN_CREATED_TS,
                COLUMN_UPDATED_TS
        };


        /**
         * Типы данных
         */

        // Список заметок
        public static final String URI_TYPE_NOTE_DIR = "vnd.android.cursor.dir/vnd.a4sem_rpp.note";

        // Одна заметка
        public static final String URI_TYPE_NOTE_ITEM = "vnd.android.cursor.item/vnd.a4sem_rpp.note";


    }

}
