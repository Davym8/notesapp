package com.example.notesapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Notes;
import com.example.notesapp.util.Util;

public class NoteActivity extends AppCompatActivity
{
    DatabaseHelper databaseHelper;
    Button updatebtn, deletebtn;
    EditText editText;
    String notedata;
    int noteindex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editnote);

        updatebtn = findViewById(R.id.editbtn);
        deletebtn = findViewById(R.id.deletebtn);
        editText = findViewById(R.id.updatenotetext);
        editText = findViewById(R.id.updatenotetext);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            noteindex = extras.getInt(Util.NOTE_ID);
            notedata = extras.getString(Util.NOTE_Details);
            editText.setText(notedata);

        } else
            {
            Toast.makeText(NoteActivity.this, "Edit failed", Toast.LENGTH_LONG).show();
            finish();
        }
        databaseHelper = new DatabaseHelper(this);
    }
    public void updatenotes(View view)
    {
        String notedataset = editText.getText().toString();
        if (notedataset.isEmpty())
        {
            Toast.makeText(NoteActivity.this, "Error Note Empty", Toast.LENGTH_LONG).show();
        }
        else
        {
            Notes notes = new Notes(noteindex, notedataset);

            if (databaseHelper.updatenotes(notes))
            {
                Toast.makeText(NoteActivity.this, "Update successful", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                finish();
            }
            else
            {
                Toast.makeText(NoteActivity.this, "Update failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void delete(View view)
    {
        if (databaseHelper.removenotes(noteindex))
        {
            Toast.makeText(NoteActivity.this, "Note Deleted", Toast.LENGTH_LONG).show();
            finish();
        }
        else
        {
            Toast.makeText(NoteActivity.this, "Deletion Failed", Toast.LENGTH_LONG).show();
        }
    }
}
