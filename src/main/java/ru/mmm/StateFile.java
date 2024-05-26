package ru.mmm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

/** Класс - файл состояния. */
@NoArgsConstructor
public class StateFile {
    /** Путь к файлу состояний. */
    @Setter
    private static String stateFilePath;
    /** Список возможных образов чубрика. */
    @Getter
    private static List<String> formNames;
    /** Список возможных цветов чубрика. */
    @Getter
    private static List<Integer> colors;
    /** Список возможных состояний чубрика. */
    @Getter
    private static List<String> states;
    /** Здоровье из файла состояний. */
    @Getter
    private static int startHealth;
    /** Сытость из файла состояний. */
    @Getter
    private static int startHunger;
    /** Счастье из файла состояний. */
    @Getter
    private static int startHappy;
    /** Количество съеденного из файла состояний. */
    @Getter
    private static int startAmountOfEaten;
    /** Образ чубрика из файла состояний. */
    @Getter
    private static String startFormName;
    /** Состояние чубрика из файла состояний. */
    @Getter
    private static String startState;
    /** Цвет чубрика из файла состояний. */
    @Getter
    private static int startColor;
    /** Список заданий из файла состояний. */
    @Getter
    private static ArrayList<String> startToDoList;

    /** Загрузить состояние программы из файла состояний. */
    public static void loadFromJson() throws IOException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(stateFilePath));
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
            startToDoList = mapper.convertValue(rootNode.get("startToDoList"), new TypeReference<ArrayList<String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Выгрузить состояние программы в файл состояний. */
    public static void convertToJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode rootNode = mapper.createObjectNode();

            rootNode.put("formNames", mapper.valueToTree(formNames));
            rootNode.put("colors", mapper.valueToTree(colors));
            rootNode.put("states", mapper.valueToTree(states));

            ObjectNode chubrickNode = mapper.createObjectNode();
            chubrickNode.put("startHealth", Chubrick.getHealth());
            chubrickNode.put("startHunger", Chubrick.getHunger());
            chubrickNode.put("startHappy", Chubrick.getHappy());
            chubrickNode.put("startAmountOfEaten", Chubrick.getAmountOfEaten());
            chubrickNode.put("startFormName", Chubrick.getFormName());
            chubrickNode.put("startColor", Chubrick.getColor());
            chubrickNode.put("startState", Chubrick.getState());
            rootNode.set("chubrick", chubrickNode);

            rootNode.put("startToDoList", mapper.valueToTree(ToDoList.getTasks()));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(stateFilePath), rootNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
