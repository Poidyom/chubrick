package ru.mmm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@NoArgsConstructor
public class StateFile {
    private static final String stateFilePath = "..\\..\\..\\resources\\ru\\mmm\\stateFile.json";
    @Getter private static List<String> formNames = new ArrayList<>();
    @Getter private static List<Integer> colors = new ArrayList<>();
    @Getter private static List<Integer> states = new ArrayList<>();
    @Getter private static int startHealth;
    @Getter private static int startHunger;
    @Getter private static int startHappy;
    @Getter private static int startAmountOfEaten;
    @Getter private static int startFormName;
    @Getter private static int startColor;
    @Getter private static int startState;
    @Getter private static int startToDoList;

    public void LoadStates(){
        //загрузка состояний ИЗ json
    }

    public void UnloadStates(){
        //загрузка состояний В json
    }
}
