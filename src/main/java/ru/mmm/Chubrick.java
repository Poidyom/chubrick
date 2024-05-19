package ru.mmm;

import lombok.Getter;
import lombok.NoArgsConstructor;

/** Класс чубрика */
@NoArgsConstructor
public class Chubrick {
    /** Здоровье. */
    //@JsonProperty("id")
    //@CsvBindByName(column = "id")
    @Getter private static int health;
    /** Сытость. */
    @Getter private static int hunger;
    /** Скука. */
    @Getter private static int boredom;
    /** Количество съеденного. */
    private static int amountOfEaten;
    /** Образ чубрика. */
    @Getter private static String form;
    /** Цвет чубрика. */
    @Getter private static int color;
    /**
     * Задать стартовые параметры чубрика.
     * @param _health стартовое значение здоровья
     * @param _hunger стартовое значение сытости
     * @param _boredom стартовое значение скуки
     */
    public static void SetCharacterParams(int _health, int _hunger, int _boredom) {
        health = _health;
        hunger = _hunger;
        boredom = _boredom;
        amountOfEaten = 0;
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
     * Увеличить скуку.
     * @param value добавляемое значение скуки
     */
    public static void PlusBore(int value){
        boredom += value;
        if(boredom > 150) boredom = 150;
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
     * Уменьшение скуки.
     * @param value количество, на которое уменьшается скука
     */
    public static void MinusBore(int value){
        boredom -= value;
        if(boredom < 0) boredom = 0;
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
}
