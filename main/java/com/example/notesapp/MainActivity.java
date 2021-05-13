package com.example.notesapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    ImageView homescreen;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homescreen =(ImageView) findViewById(R.id.homescreen);
    }
    public void createnote(View view)
{
    Intent intent = new Intent(this, createnotes.class);
    startActivity(intent);
}

    public void shownotes(View view)
    {
        Intent intent = new Intent(this, recyclernotes.class);
        startActivity(intent);
    }

}