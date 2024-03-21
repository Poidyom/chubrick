package ru.mmm;

import lombok.Getter;
import lombok.NoArgsConstructor;

/** Класс чубрика */
@NoArgsConstructor
@Getter
public class Chubrick {
    /** Здоровье. */
    //@JsonProperty("id")
    //@CsvBindByName(column = "id")
    private static int health;
    /** Сытость. */
    private static int hunger;
    /** Скука. */
    private static int boredom;
    /** Количество съеденного. */
    private static int amountOfEaten;
    /**
     * Задать стартовые параметры чубрика.
     * @param _health стартовое значение здоровья
     * @param _hunger стартовое значение сытости
     * @param _boredom стартовое значение скуки
     */
    public static void SetСharacterParams(int _health, int _hunger, int _boredom) {
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
            if(hunger > 100) hunger = 100;
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
        if(boredom > 100) boredom = 100;
    }
    /** Сон. */
    public static void Sleep(){
        health += 10;   //сколько восстанавливает сон?
        if(health > 100) health = 100;
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
        health -= value;
        if(health <= 0) { health = 0; return true; }
        return false;
    }
}
