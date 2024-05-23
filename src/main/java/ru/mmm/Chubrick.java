package ru.mmm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Класс чубрика */
@NoArgsConstructor
public class Chubrick {
    /** Здоровье. */
    @Getter private static int health;
    /** Сытость. */
    @Getter private static int hunger;
    /** Счастье. */
    @Getter private static int happy;
    /** Количество съеденного. */
    private static int amountOfEaten;
    /** Образ чубрика. */
    @Getter @Setter
    private static String formName;
    /** Состояние чубрика. */
    @Getter @Setter
    private static String state;
    /** Цвет чубрика. */
    @Getter @Setter
    private static int color;
    /**
     * Задать стартовые параметры чубрика.
     * @param _health стартовое значение здоровья
     * @param _hunger стартовое значение сытости
     * @param _happy стартовое значение счастья
     */
    public static void SetCharacterParams(int _health, int _hunger, int _happy, int _amountOfEaten) {
        health = _health;
        hunger = _hunger;
        happy = _happy;
        amountOfEaten = _amountOfEaten;
    }


    /** Обнуление количества съеденной еды. */
    public static void resetFood(){
        amountOfEaten = 0;
    }
    /**
     * Прибавить 1 к кол-ву съеденной еды.
     * @param value количество сытости, добавляемое съеденной едой
     * @return признак удачи
     */
    public static boolean PlusEat(int value){
        if(amountOfEaten < 3){
            hunger += value;
            if(hunger > 150) hunger = 150;
            amountOfEaten++;
            return true;
        }
        return false;
    }
    /**
     * Увеличить счастье.
     * @param value добавляемое значение счастья
     */
    public static void PlusHappy(int value){
        happy += value;
        if(happy > 150) happy = 150;
    }
    /** Сон. */
    public static void Sleep(){
        health = 150;
        resetFood();
    }
    /**
     * Уменьшение сытости.
     * @param value количество, на которое уменьшается сытость
     */
    public static void MinusEat(int value){
        hunger -= value;
        if(hunger < 0) hunger = 0;
    }
    /**
     * Уменьшение счастья.
     * @param value количество, на которое уменьшается счастье
     */
    public static void MinusHappy(int value){
        happy -= value;
        if(happy < 0) happy = 0;
    }
    /**
     * Уменьшение здоровья.
     * @param value количество, на которое уменьшается здоровье
     */
    public static boolean MinusHealth(int value){
        if(hunger <= 0){
            health -= value;
            if(health <= 0) { health = 0; return true; }
        }
        return false;
    }

    /** Возврат пути до файла с изображением текущей конфигурации чубрика */
    public static String GetPathToAppearance(){
        if (health > 0) {
            return  "/ru/mmm/sprites/" + formName + "_" + state + "_" + color + ".png";
        }
        else {
            return "/ru/mmm/sprites/dead.png";
        }
    }
}
