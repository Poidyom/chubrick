package ru.mmm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;

import java.io.IOException;

/** Класс - файл состояния. */
@NoArgsConstructor
public class StateFile {
    /** Путь к файлу состояний. */
    private static final String stateFilePath = "src\\main\\resources\\ru\\mmm\\stateFile.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    /** Список возможных образов чубрика. */
    @Getter @Setter
    private static List<String> formNames;
    /** Список возможных цветов чубрика. */
    @Getter @Setter
    private static List<Integer> colors;
    /** Список возможных состояний чубрика. */
    @Getter @Setter
    private static List<String> states;
    /** Здоровье из файла состояний. */
    @Getter @Setter
    private static int startHealth;
    /** Сытость из файла состояний. */
    @Getter @Setter
    private static int startHunger;
    /** Счастье из файла состояний. */
    @Getter @Setter
    private static int startHappy;
    /** Количество съеденного из файла состояний. */
    @Getter @Setter
    private static int startAmountOfEaten;
    /** Образ чубрика из файла состояний. */
    @Getter @Setter
    private static String startFormName;
    /** Состояние чубрика из файла состояний. */
    @Getter @Setter
    private static String startState;
    /** Цвет чубрика из файла состояний. */
    @Getter @Setter
    private static int startColor;
    /** Список заданий из файла состояний. */
    @Getter @Setter
    private static List<String> startToDoList;

    /** Загрузить состояние программы из файла состояний. */
    public static void loadFromJson() throws IOException {
        File file = new File(stateFilePath);
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(file);
            formNames = mapper.convertValue(rootNode.get("formNames"), new TypeReference<List<String>>() {});
            colors = mapper.convertValue(rootNode.get("colors"), new TypeReference<List<Integer>>() {});
            states = mapper.convertValue(rootNode.get("states"), new TypeReference<List<String>>() {});

            JsonNode chubrickNode = rootNode.get("chubrick");
            startHealth = chubrickNode.get("startHealth").asInt();
            startHunger = chubrickNode.get("startHunger").asInt();
            startHappy = chubrickNode.get("startHappy").asInt();
            startAmountOfEaten = chubrickNode.get("startAmountOfEaten").asInt();
            startFormName = mapper.convertValue(chubrickNode.get("startFormName"), String.class);
            startColor = chubrickNode.get("startColor").asInt();
            startState = mapper.convertValue(chubrickNode.get("startState"), String.class);
            startToDoList = mapper.convertValue(rootNode.get("startToDoList"), new TypeReference<List<String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выгрузить состояние программы в файл состояний

     */
    public static void UnloadStates(){
        //загрузка состояний В json
    }
}
