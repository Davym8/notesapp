package com.example.notesapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.notesapp.model.Notes;
import com.example.notesapp.util.Util;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public DatabaseHelper(@Nullable Context context)
    {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String CREATE_TABLE = "CREATE TABLE " + Util.NOTES_TABLE_NAME + " (" + Util.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Util.NOTE_Details + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int ind1)
    {
        String DROP_NOTES_TABLE = "DROP TABLE IF EXISTS " + Util.NOTES_TABLE_NAME;
        sqLiteDatabase.execSQL(DROP_NOTES_TABLE);
    }

    public long createnotes(Notes note)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Util.NOTE_Details, note.getNote_details());
            long rowid = db.insert(Util.NOTES_TABLE_NAME, null, contentValues);
            db.close();
            return rowid;
        }
        catch (Exception e)
            {
                Log.e("Error", "Exception " + e.getMessage());
                return 0;
            }
    }

    public ArrayList<Notes> fetchNotes()
    {
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + Util.NOTES_TABLE_NAME;
            Cursor cursor = db.rawQuery(query, new String[]{});
            ArrayList<Notes> notes = new ArrayList<>();
            if (cursor.moveToFirst())
            {
                do {
                    int noteid = cursor.getInt(cursor.getColumnIndex(Util.NOTE_ID));
                    String notedata = cursor.getString(cursor.getColumnIndex(Util.NOTE_Details));
                    Notes note = new Notes(noteid, notedata);
                    notes.add(note);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return notes;
        }
        catch (Exception e)
        {
            Log.e("Error", "Exception " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean removenotes(int note_id)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(Util.NOTES_TABLE_NAME, Util.NOTE_ID + "=?", new String[]{"" + note_id}) > 0;
        }
        catch (Exception e)
        {
            Log.e("Error", "Exception " + e.getMessage());
            return false;
        }
    }

    public boolean updatenotes(Notes note)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Util.NOTE_ID, note.getNote_id());
            contentValues.put(Util.NOTE_Details, note.getNote_details());
            return db.update(Util.NOTES_TABLE_NAME, contentValues, Util.NOTE_ID + " = " + note.getNote_id(), null) > 0;
        }
        catch (Exception e)
        {
            Log.e("Error", "Exception " + e.getMessage());
            return false;
        }
    }
}
