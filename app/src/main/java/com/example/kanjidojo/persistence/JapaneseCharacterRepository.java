package com.example.kanjidojo.persistence;

import android.app.Application;
import android.os.AsyncTask;

import com.example.kanjidojo.entity.JapaneseCharacter;

import java.util.List;

import androidx.lifecycle.LiveData;

public class JapaneseCharacterRepository {
    private JapaneseCharacterDao japaneseCharacterDao;
    private LiveData<List<JapaneseCharacter>> allJapaneseCharacters;

    public JapaneseCharacterRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(application);
        japaneseCharacterDao = appDatabase.japaneseCharacterDao();
        allJapaneseCharacters = japaneseCharacterDao.getAllJapaneseCharacters();
    }

    public LiveData<List<JapaneseCharacter>> getAllJapaneseCharacters() {
        return this.allJapaneseCharacters;
    }

    public void insert(JapaneseCharacter japaneseCharacter) {
        new insertAsyncTask(japaneseCharacterDao).execute(japaneseCharacter);
    }


    private static class insertAsyncTask extends AsyncTask<JapaneseCharacter, Void, Void> {

        private JapaneseCharacterDao asyncTaskJapaneseCharacterDao;

        insertAsyncTask(JapaneseCharacterDao asyncTaskJapaneseCharacterDao) {
            asyncTaskJapaneseCharacterDao = asyncTaskJapaneseCharacterDao;
        }

        @Override
        protected Void doInBackground(final JapaneseCharacter... params) {
            asyncTaskJapaneseCharacterDao.insert(params[0]);
            return null;
        }
    }

}
