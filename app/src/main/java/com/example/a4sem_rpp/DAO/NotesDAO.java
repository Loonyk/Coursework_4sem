package com.example.a4sem_rpp.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.a4sem_rpp.modelDB.Notes;

import java.util.List;

@Dao
public interface NotesDAO {

    // Добавление заметок в бд
    @Insert
    void insertAll(Notes... notes);

    // Получение всех заметок из бд
    @Query("SELECT * FROM notes")
    List<Notes>getAllNotes();

    @Query("SELECT * FROM notes WHERE title=:title AND note=:text")
    List<Notes>getNotesTitleText(String title, String text);

    @Query("SELECT * FROM notes WHERE note=:text")
    List<Notes>getNotesText(String text);
    @Query("SELECT * FROM notes WHERE id=:id_note")
    List<Notes>getNotesId(int id_note);
}
