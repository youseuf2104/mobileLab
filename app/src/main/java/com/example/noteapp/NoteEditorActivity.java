package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editTitleText = findViewById(R.id.editTextTitle);
        EditText editContentText = findViewById(R.id.editText);

        // Fetch data that is passed from MainActivity
        Intent intent = getIntent();

        // Accessing the data using key and value
        noteId = intent.getIntExtra("noteId", -1);
        if (noteId != -1) {
            editTitleText.setText(MainActivity.notes.get(noteId));
            editContentText.setText(MainActivity.notesContent.get(noteId));
        } else {

            MainActivity.notes.add("");
            MainActivity.notesContent.add("");
            noteId = MainActivity.notes.size() - 1;
            noteId = MainActivity.notesContent.size() - 1;
            MainActivity.arrayAdapter.notifyDataSetChanged();

        }

        editTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // add your code here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notes.set(noteId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                // Creating Object of SharedPreferences to store data in the phone
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();

                //ADDED
                MainActivity.notesContent.set(noteId, String.valueOf(charSequence));
                SharedPreferences sharedPreferences2 = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set2 = new HashSet(MainActivity.notesContent);
                sharedPreferences2.edit().putStringSet("notesContent", set2).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // add your code here
            }
        });


        //ADDED

        findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                /*
                NavHostFragment.findNavController(NoteEditorActivity.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                        */
                 finish();
            }
        });


    }
}

