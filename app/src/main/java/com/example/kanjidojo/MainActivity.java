package com.example.kanjidojo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private final static String LOG_TAG = "MainActivity";

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "Start Main Activity");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });

        Button myButton = new Button(this);
        myButton.setText("JLPT N5");


        int id = 1;
        myButton.setId(id);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                count++;
                Log.i(LOG_TAG, "Button clicked " + view.getId() + ", count: " + count);
                launchQuizActivity();
            }
        });
        LinearLayout buttonLinearLayout = (LinearLayout) this.findViewById(R.id.buttonLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonLinearLayout.addView(myButton, layoutParams);


    }

    private void launchQuizActivity(){
        Log.i(LOG_TAG, "Lauching Quiz Activity");
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("QUIZ_LEVEL", "jlpt5");
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openQuiz(View view) {
        Log.i(LOG_TAG, "Opening Quiz");

    }
}