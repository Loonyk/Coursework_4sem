package com.example.a4sem_rpp.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class DBNotesProvider extends ContentProvider {
    private DBHelper dbHelper;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int NOTES = 1;  //список заметок
    private static final int NOTE = 2;  //одна заметка

    static {
        /** 1-AUTHORIYY, 2-путь к данным, 3-константа, #-плейсхолдер для id
         */
        URI_MATCHER.addURI(DBNotesConstract.AUTHORITY, "notes", NOTES);
        URI_MATCHER.addURI(DBNotesConstract.AUTHORITY, "notes/#", NOTE);
    }

    @Override
    /** Создается БД
     */
    public boolean onCreate(){
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    /** Для запроса данных из БД и доступа ко всей БД
     */
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder){

        SQLiteDatabase db = dbHelper.getReadableDatabase(); //для чтения из БД
        /** с какими данными работаем*/
        switch (URI_MATCHER.match(uri)){
            case NOTES:
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = DBNotesConstract.Notes.COLUMN_UPDATED_TS + " DESC";
                //сортировка по убыванию столбца "последние обновление"
                }
                //проксируется запрос БД, которую ранее получили
                return db.query(DBNotesConstract.Notes.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, // groupBy, не используется
                        null,   // having, не используется
                        sortOrder);

            case NOTE:
                String id = uri.getLastPathSegment();  //получаем идентификатор заметки
                if(TextUtils.isEmpty(selection)){   //проверка, не задан ли аргумент параметром выборки
                    selection = DBNotesConstract.Notes._ID + " = ?";   //если не задан, создаем аргемент
                    selectionArgs = new String[]{id};                   //и параметр выборки
                } else {   //если задан
                    selection = selection + " AND " + DBNotesConstract.Notes._ID + " + ?";
                    String[] newSelectionArgs = new String[selectionArgs.length + 1];

                    System.arraycopy(selectionArgs, 0,          //копируются переданные ранее аргументы выборки
                            newSelectionArgs, 0, selectionArgs.length);
                    newSelectionArgs[newSelectionArgs.length - 1] = id;  // и добавляем к ним полученный ранее идентиф
                    selectionArgs = newSelectionArgs;
                }
                return db.query(DBNotesConstract.Notes.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, // groupBy, не используется
                        null,   // having, не используется
                        sortOrder);

                default:
                    return null;
        }

    }

    @Nullable
    @Override
    /** возвращает тип контента, который определяли в DBNotesConstract
     */
    public String getType(@NonNull Uri uri){
        switch (URI_MATCHER.match(uri)){
            case NOTES:
                return DBNotesConstract.Notes.URI_TYPE_NOTE_DIR;
            case NOTE:
                return DBNotesConstract.Notes.URI_TYPE_NOTE_ITEM;

            default:
                return null;
        }
    }

    @Nullable
    @Override
    /** вставка данных в БД, возвращает Uri созданного элемента, если был создан
     */
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (URI_MATCHER.match(uri)) {
            case NOTES:
                long rowId = db.insert(DBNotesConstract.Notes.TABLE_NAME,
                        null,
                        contentValues);

                if (rowId > 0) {
                    Uri noteUri = ContentUris.withAppendedId(DBNotesConstract.Notes.URI, rowId);
                    getContext().getContentResolver().notifyChange(uri, null);

                    return noteUri;
                }
                return null;
            default:
                return null;
        }
    }

//    public  Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues){
//        SQLiteDatabase db = dbHelper.getWritableDatabase(); //для записи в бд
//
//        switch (URI_MATCHER.match(uri)){
//            case NOTES:
//                long rowId = db.insert(DBNotesConstract.Notes.TABLE_NAME,
//                        null,
//                        contentValues);  //идентификатор вставленной строки
//                //если вставка прошла успешно, то rowId > 0
//                if(rowId > 0){
//                    // создаем и возвращаем uri
//                    Uri noteUri = ContentUris.withAppendedId(DBNotesConstract.Notes.URI, rowId);
//                    //оповещение системы об изменении данных
//                    getContext().getContentResolver().notifyChange(uri, null);
//
//                    return noteUri;
//                }
//                return null;
//
//                default:
//                    return null;
//        }
//    }

    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs){
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues contentValues,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs){
        return 0;
    }

}
