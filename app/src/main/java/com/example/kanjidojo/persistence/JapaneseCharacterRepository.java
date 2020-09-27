package com.example.kanjidojo.persistence;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.kanjidojo.entity.JapaneseCharacter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class JapaneseCharacterRepository {
    private JapaneseCharacterDao japaneseCharacterDao;
    private final static String LOG_TAG = "JapaneseCharRepository";

    private static boolean initialized = false;

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public JapaneseCharacterRepository(Application application) {
        Log.i(LOG_TAG, "Initializing JapaneseCharRepository");
        AppDatabase appDatabase = AppDatabase.getAppDatabase(application);
        japaneseCharacterDao = appDatabase.japaneseCharacterDao();

    }

    public List<JapaneseCharacter> getAllJapaneseCharacters() {
        //AppDatabase.initializeDatabase(japaneseCharacterDao);
        //initializeDatabase();
        Log.i(LOG_TAG, "Getting all Japanese Characters");
        List<JapaneseCharacter> allJapaneseCharacters = japaneseCharacterDao.getAllJapaneseCharacters();
        if (allJapaneseCharacters != null) {
            Log.i(LOG_TAG, "Number of Japanese Characters Loaded: " + allJapaneseCharacters.size());
            List<JapaneseCharacter> japaneseCharacters = allJapaneseCharacters;
            for (JapaneseCharacter japaneseCharacter : japaneseCharacters) {
                Log.i(LOG_TAG, japaneseCharacter.toString());
            }
        } else {
            Log.i(LOG_TAG, "Loaded nothing from db");
        }
        return allJapaneseCharacters;
    }

    private void initializeDatabase() {
        if (initialized) {
            // Check if room database already populated
            Log.i(LOG_TAG, "Already intiialized");
        } else {
            Log.i(LOG_TAG, "Inside runnable Add Japanese characters to database");
            JapaneseCharacter japaneseCharacter = new JapaneseCharacter();
            japaneseCharacter.setId(6L);
            japaneseCharacter.setKanji("neko");
            japaneseCharacter.setEnglishTranslation("cat");
            Log.i(LOG_TAG, "saving first character");
            insert(japaneseCharacter);
            Log.i(LOG_TAG, "saved first character");

            Log.i(LOG_TAG, "saving second character");
            JapaneseCharacter secondJapaneseCharacter = new JapaneseCharacter();
            secondJapaneseCharacter.setId(7L);
            secondJapaneseCharacter.setKanji("inu");
            secondJapaneseCharacter.setEnglishTranslation("dog");
            insert(secondJapaneseCharacter);
            Log.i(LOG_TAG, "saved second character");

            Log.i(LOG_TAG, " Inside runnable Finished populating db");
            initialized = true;
        }


    }


    public void insert(JapaneseCharacter japaneseCharacter) {
        new insertAsyncTask(japaneseCharacterDao).execute(japaneseCharacter);
    }


    private static class insertAsyncTask extends AsyncTask<JapaneseCharacter, Void, Void> {

        private JapaneseCharacterDao asyncTaskJapaneseCharacterDao;

        insertAsyncTask(JapaneseCharacterDao asyncTaskJapaneseCharacterDao) {
            this.asyncTaskJapaneseCharacterDao = asyncTaskJapaneseCharacterDao;
            Log.i(LOG_TAG, "Initialized async task with dao: " + asyncTaskJapaneseCharacterDao);
        }

        @Override
        protected Void doInBackground(final JapaneseCharacter... params) {
            asyncTaskJapaneseCharacterDao.insert(params[0]);
            return null;
        }
    }

}
