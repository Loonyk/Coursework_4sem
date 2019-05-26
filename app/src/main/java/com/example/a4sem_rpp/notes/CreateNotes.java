package com.example.a4sem_rpp.notes;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.a4sem_rpp.AsyncTask.AsyncAdapter;
import com.example.a4sem_rpp.AsyncTask.AsyncNoteInsert;
import com.example.a4sem_rpp.AsyncTask.AsyncTaskNote;
import com.example.a4sem_rpp.R;
import com.example.a4sem_rpp.modelDB.Notes;

import java.util.ArrayList;
import java.util.List;

public class CreateNotes extends AppCompatActivity {

    private TextInputEditText titleEt;
    private TextInputEditText textEt;
    private TextInputLayout titleTil;
    private TextInputLayout textTil;
    private String title;
    private String text;
    private ClipData.Item button;
    private Notes notes;
    AsyncAdapter as;


    public Notes getNotes() {
        return notes;
    }

    public TextInputEditText getTitleEt() {
        return titleEt;
    }

    public void setTitleEt(TextInputEditText titleEt) {
        this.titleEt = titleEt;
    }

    public TextInputEditText getTextEt() {
        return textEt;
    }

    public void setTextEt(TextInputEditText textEt) {
        this.textEt = textEt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);

        notes = ((ArrayList<Notes>)getIntent().getExtras().getSerializable("list")).get(0);

        titleEt = findViewById(R.id.title_et);
        textEt = findViewById(R.id.text_et);
        titleTil = findViewById(R.id.title_til);
        textTil = findViewById(R.id.text_til);

        titleEt.setText(notes.getTitle());
        textEt.setText(notes.getNote());

    }

    @Override    //кнопка сохранить в тулбаре
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.create_note, menu);
        return true;
    }

    //метод сохранения заметки
    private void saveNote(){
        String title = titleEt.getText().toString().trim();
        String text = textEt.getText().toString().trim();

        boolean isCorrect = true;

        if (TextUtils.isEmpty(title)) {
            isCorrect = false;

            titleTil.setError("Необходимо заполнить поле");
            titleTil.setErrorEnabled(true);
        } else {
            titleTil.setErrorEnabled(false);
        }

        if (TextUtils.isEmpty(text)) {
            isCorrect = false;

            textTil.setError("Необходимо заполнить поле");
            textTil.setErrorEnabled(true);
        } else {
            textTil.setErrorEnabled(false);
        }

        if (isCorrect){
            //код вставки заметки в БД + завершение работы активити и вывод списка
            AsyncNoteInsert as= new AsyncNoteInsert();
            as.execute(this);
        }
    }

    @Override  //обработка клика на кнопку сохранить
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save:
                saveNote();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public void openNotes(List<Notes> l) {
        Intent intent = new Intent(".notes.NotesActivity");
        intent.putExtra("list",(ArrayList)l);
        startActivity(intent);
    }

}
