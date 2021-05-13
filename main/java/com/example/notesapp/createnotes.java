package com.example.notesapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Notes;

public class createnotes extends AppCompatActivity
{
    EditText noteedit;
    Button savebtn;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makenotes);
        noteedit = findViewById(R.id.notedatatext);
        savebtn = findViewById(R.id.savebtn);
        databaseHelper = new DatabaseHelper(this);
    }

    public void saveNote(View view)
    {
        String notedeets = noteedit.getText().toString();

        if (notedeets.isEmpty())
        {
            Toast.makeText(createnotes.this, "its empty!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Notes note = new Notes(notedeets);
            long result = databaseHelper.createnotes(note);
            if (result > -1)
            {
                Toast.makeText(createnotes.this, "Note saved!", Toast.LENGTH_LONG).show();
                finish();
            }
            else
            {
                Toast.makeText(createnotes.this, "something broke!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
