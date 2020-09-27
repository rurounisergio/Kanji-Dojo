package com.example.kanjidojo;

import android.content.Intent;
import android.os.Bundle;

import com.example.kanjidojo.entity.JapaneseCharacter;
import com.example.kanjidojo.viewmodel.QuizViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Load characters from quiz level
 * randomize possible answers to create a quiz set
 * <p>
 * for each question, display question and 4 possible answers
 * on selection, check if right or wrong
 * increase score or add to wrongly answered list
 */
public class QuizActivity extends AppCompatActivity {
    private final static String LOG_TAG = "QuizActivity";

    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Log.i(LOG_TAG, "QuizActivity Opened");
        Intent intent = getIntent();
        String message = intent.getStringExtra("QUIZ_LEVEL");
        Log.i(LOG_TAG, "Received quiz level: " + message);

        this.quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        List<JapaneseCharacter> allJapaneseCharacters = this.quizViewModel.getAllJapaneseCharacters();
        if (allJapaneseCharacters != null) {
            Log.i(LOG_TAG, "Number of Japanese Characters Loaded: " + allJapaneseCharacters.size());
            List<JapaneseCharacter> japaneseCharacters = allJapaneseCharacters;
            for (JapaneseCharacter japaneseCharacter : japaneseCharacters) {
                Log.i(LOG_TAG, japaneseCharacter.toString());
            }
        } else {
            Log.i(LOG_TAG, "Loaded nothing from db");
        }

    }
}