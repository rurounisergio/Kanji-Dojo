package com.example.kanjidojo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kanjidojo.entity.JapaneseCharacter;
import com.example.kanjidojo.viewmodel.JapaneseCharacterAdapter;
import com.example.kanjidojo.viewmodel.QuizViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FirstFragment extends Fragment {

    private QuizViewModel quizViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        Log.i("FirstFragment", "Initializing Recycler View");
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
        Log.i("FirstFragment", "Found Recycler View: " + recyclerView);
        final JapaneseCharacterAdapter adapter = new JapaneseCharacterAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        this.quizViewModel.getAllJapaneseCharacterss().observe(this.getViewLifecycleOwner(), new Observer<List<JapaneseCharacter>>(){

            @Override
            public void onChanged(List<JapaneseCharacter> japaneseCharacters) {
                adapter.setWords(japaneseCharacters);
            }
        });

        return rootView;


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}