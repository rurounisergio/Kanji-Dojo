package com.example.kanjidojo.service;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LevelLoader {


    public List<String> getAvailableLevels() {
        List<String> levels = new ArrayList<>();
        levels.add("JLPT N5");
        levels.add("JLPT N4");
        levels.add("JLPT N3");

        return levels;

    }

}
