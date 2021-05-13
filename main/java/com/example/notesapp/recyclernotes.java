package com.example.notesapp;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Notes;
import com.example.notesapp.recyclerview.Recycler;

import java.util.ArrayList;

public class recyclernotes extends AppCompatActivity
{
    DatabaseHelper databaseHelper;
    RecyclerView notesrecycler;
    Recycler recycler;
    ArrayList<Notes> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showrecycler);
        databaseHelper = new DatabaseHelper(this);
        setup();
    }

    private void setup()
    {
        notes = databaseHelper.fetchNotes();
        notesrecycler = findViewById(R.id.noterecycler);
        recycler = new Recycler(notes, this);
        notesrecycler.setAdapter(recycler);
        notesrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setup();
    }
}
