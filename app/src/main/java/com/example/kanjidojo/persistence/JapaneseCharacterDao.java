package com.example.kanjidojo.persistence;

import com.example.kanjidojo.entity.JapaneseCharacter;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface JapaneseCharacterDao {

    @Query("SELECT * FROM japanese_character WHERE jlpt_level IN (:jlptLevels)")
    List<JapaneseCharacter> getJapaneseCharactersForJlptLevel(List<String> jlptLevels);

    @Query("SELECT * FROM japanese_character")
    LiveData<List<JapaneseCharacter>> getAllJapaneseCharacters();

    @Insert
    void insert(JapaneseCharacter japaneseCharacter);


}
