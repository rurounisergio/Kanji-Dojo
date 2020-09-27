package com.example.kanjidojo.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kanjidojo.R;
import com.example.kanjidojo.entity.JapaneseCharacter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JapaneseCharacterAdapter extends RecyclerView.Adapter<JapaneseCharacterAdapter.QuizViewHolder> {
    private final LayoutInflater mInflater;
    private List<JapaneseCharacter> mWords; // Cached copy of words

    public JapaneseCharacterAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new QuizViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        if (mWords != null) {
            JapaneseCharacter current = mWords.get(position);
            holder.japaneseItemView.setText(current.getKanji());
        } else {
            // Covers the case of data not being ready yet.
            holder.japaneseItemView.setText("No Word");
        }
    }

    public void setWords(List<JapaneseCharacter> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    class QuizViewHolder extends RecyclerView.ViewHolder {
        private final TextView japaneseItemView;

        private QuizViewHolder(View itemView) {
            super(itemView);
            japaneseItemView = itemView.findViewById(R.id.textView);
        }
    }
}
