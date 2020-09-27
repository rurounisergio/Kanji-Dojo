package com.example.kanjidojo.persistence;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.kanjidojo.entity.JapaneseCharacter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {JapaneseCharacter.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private final static String LOG_TAG = "AppDatabase";
    private static boolean initialized = false;

    public abstract JapaneseCharacterDao japaneseCharacterDao();

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();


    public static AppDatabase getAppDatabase(final Context context) {
        Log.i(LOG_TAG, "Instance is null: " + INSTANCE);
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            // .createFromAsset("database/jlpt_n5.sql")
                            // TODO only want to initialize the database the first time the app is started
                            // Is data persisted between opening/closing sessions
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                    Log.i(LOG_TAG, "Initialized Room database Instance");

                    Log.i(LOG_TAG, "Populating database with data");

//                  Initialize database
//                    executor.submit(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.i(LOG_TAG, "Executing runnable");
//                            initializeDatabase();
//                        }
//                    });

                }
                initializeDatabase();
                Log.i(LOG_TAG, "Finished initializing database");
            }
        } else {
            Log.i(LOG_TAG, "Database already intiialized");
        }
        return INSTANCE;
    }

    public static void initializeDatabase() {
        if (initialized) {
            Log.i(LOG_TAG, "already initialized");
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

    public static void insert(JapaneseCharacter japaneseCharacter) {
        try {
            new insertAsyncTask(INSTANCE.japaneseCharacterDao()).execute(japaneseCharacter);
        } catch (Exception e) {
            Log.i(LOG_TAG, "Error occured inserting data into database" + e);
        }

    }

    private static class insertAsyncTask extends AsyncTask<JapaneseCharacter, Void, Void> {

        private JapaneseCharacterDao asyncTaskJapaneseCharacterDao;

        insertAsyncTask(JapaneseCharacterDao asyncTaskJapaneseCharacterDao) {
            this.asyncTaskJapaneseCharacterDao = asyncTaskJapaneseCharacterDao;
        }

        @Override
        protected Void doInBackground(final JapaneseCharacter... params) {
            try {
                Log.i(LOG_TAG, "dao: " + asyncTaskJapaneseCharacterDao + ", param: " + params[0]);
                asyncTaskJapaneseCharacterDao.insert(params[0]);
                return null;
            } catch (Exception e) {
                Log.i(LOG_TAG, "Error occurred inserting data into database");
                return null;
            }

        }
    }


}