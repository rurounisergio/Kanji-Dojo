package com.example.kanjidojo.viewmodel;

import android.app.Application;
import android.util.Log;

import com.example.kanjidojo.entity.JapaneseCharacter;
import com.example.kanjidojo.persistence.JapaneseCharacterRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class QuizViewModel extends AndroidViewModel {
    private JapaneseCharacterRepository japaneseCharacterRepository;
    private static final String LOG_TAG = "QuizViewModel";

    public QuizViewModel(@NonNull Application application) {
        super(application);
        Log.i(LOG_TAG,"Initializing QuizViewModel");
        japaneseCharacterRepository = new JapaneseCharacterRepository(application);
    }

    public LiveData<List<JapaneseCharacter>> getAllJapaneseCharacterss() {
        return null;
    }

    public List<JapaneseCharacter> getAllJapaneseCharacters() {
        return japaneseCharacterRepository.getAllJapaneseCharacters();
    }

    public void insert(JapaneseCharacter japaneseCharacter){
        this.japaneseCharacterRepository.insert(japaneseCharacter);
    }


}
