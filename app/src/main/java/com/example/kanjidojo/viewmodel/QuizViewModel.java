package com.example.kanjidojo.viewmodel;

import android.app.Application;

import com.example.kanjidojo.entity.JapaneseCharacter;
import com.example.kanjidojo.persistence.JapaneseCharacterRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class QuizViewModel extends AndroidViewModel {
    private JapaneseCharacterRepository japaneseCharacterRepository;
    private LiveData<List<JapaneseCharacter>> allJapaneseCharacters;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        japaneseCharacterRepository = new JapaneseCharacterRepository(application);
        allJapaneseCharacters = japaneseCharacterRepository.getAllJapaneseCharacters();
    }

    LiveData<List<JapaneseCharacter>> getAllJapaneseCharacters() {
        return this.allJapaneseCharacters;
    }

    public void insert(JapaneseCharacter japaneseCharacter){
        this.japaneseCharacterRepository.insert(japaneseCharacter);
    }


}
