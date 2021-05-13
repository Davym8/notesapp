package com.example.notesapp.model;

public class Notes {
    private int note_id;
    private String note_details;

    public Notes(int note_id, String note_details)
    {
        this.note_id = note_id;
        this.note_details = note_details;
    }

    public Notes(String note_details)
    {
        this.note_details = note_details;
    }

    public int getNote_id()
    {
        return note_id;
    }

    public void setNote_id(int note_id)
    {
        this.note_id = note_id;
    }

    public String getNote_details()
    {
        return note_details;
    }

    public void setNote_details(String note_details)
    {
        this.note_details = note_details;
    }
}
