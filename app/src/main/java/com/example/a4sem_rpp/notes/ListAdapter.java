package com.example.a4sem_rpp.notes;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a4sem_rpp.R;
import com.example.a4sem_rpp.modelDB.Notes;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    public Context context;
    public List<Notes> list;
    public LayoutInflater inflater;
    public TextInputEditText titleEt;
    public TextInputEditText textEt;
    public static int current_position = 0;

    private Notes notes;


    public TextView title;

    public ListAdapter(Context context,List<Notes> l)
    {
        this.context=context;
        list = l;

    }

    public Context getContext() {
        return context;
    }

    public TextInputEditText getTitleEt() {
        return titleEt;
    }

    public TextInputEditText getTextEt() {
        return textEt;
    }

    public Notes getNotes() {
        return notes;
    }

    /**!!!!!!!!!!!!*/
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_notes, parent, false);

        title = (TextView)view.findViewById(R.id.titleNot);
        title.setText(this.list.get(position).getTitle());

        titleEt = (TextInputEditText)view.findViewById(R.id.title_et);
        textEt = (TextInputEditText)view.findViewById(R.id.text_et);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_position = list.get(position).getId();
                ArrayList<Notes> pos = new ArrayList<Notes>();
                pos.add(list.get(position));
                Intent intent = new Intent(context, CreateNotes.class);
                intent.putExtra("list", pos);
                context.startActivity(intent);
//                if((titleEt.getText() != null) && (textEt.getText() != null))
//                {
//                    titleNotes = titleEt.getText().toString();
//                    textNotes = textEt.getText().toString().toLowerCase().trim();
//                    AsyncAdapter as = new AsyncAdapter();
//                    as.execute(ListAdapter.this);
                }
               // String title = titleEt.getText().toString().trim();
              //  String text = textEt.getText().toString().trim();

               // titleEt = title.getText(this.list.get(position).getTitle());
//                titleNotes = titleEt.getText().toString();
//                textNotes = titleEt.getText().toString().toLowerCase().trim();
//                AsyncAdapter as = new AsyncAdapter();
//                as.execute(ListAdapter.this);

        });


        return view;
    }

    public void load_notes(List<Notes> nt)
    {
        Intent intent=new Intent(this.context,CreateNotes.class);
        intent.putExtra("list",(ArrayList)nt);
        this.context.startActivity(intent);
    }
}
