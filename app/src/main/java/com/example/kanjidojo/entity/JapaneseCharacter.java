package com.example.kanjidojo.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * A Japanese Character with it's English translation and kunyomi/onyomi pronunciation
 */
@Data
@NoArgsConstructor
@Entity(tableName = "japanese_character")
public class JapaneseCharacter {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Long id;

    @ColumnInfo(name = "kanji")
    private String kanji;

    @ColumnInfo(name = "english_translation")
    private String englishTranslation;

    @ColumnInfo(name = "onyomi")
    private String onyomi;

    @ColumnInfo(name = "kunyomi")
    private String kunyomi;

    @ColumnInfo(name = "jlpt_level")
    private String jlptLevel;

    @ColumnInfo(name = "subunit")
    private String subunit;
}
