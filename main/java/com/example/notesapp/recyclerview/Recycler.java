package com.example.notesapp.recyclerview;
import android.content.Context;
import android.content.Intent;
import android.service.controls.templates.TemperatureControlTemplate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.NoteActivity;
import com.example.notesapp.R;
import com.example.notesapp.model.Notes;
import com.example.notesapp.util.Util;
import java.text.BreakIterator;
import java.util.ArrayList;

public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder>
{
    private ArrayList<Notes> notes;
    private Context context;
    public Recycler(ArrayList<Notes> notes, Context context)
    {
        this.notes = notes;
        this.context = context;
    }
    @NonNull
    @Override
    public Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rowlayout, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.noteview.setText("Note " + position);
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, NoteActivity.class);
                intent.putExtra(Util.NOTE_ID, notes.get(position).getNote_id());
                intent.putExtra(Util.NOTE_Details, notes.get(position).getNote_details());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView noteview;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            noteview = itemView.findViewById(R.id.notestext);
        }
    }
}
