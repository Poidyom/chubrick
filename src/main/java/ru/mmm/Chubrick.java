package ru.mmm;

import lombok.Getter;
import lombok.NoArgsConstructor;

/** Класс чубрика */
@NoArgsConstructor
@Getter
public class Chubrick {
    /** Здоровье */
    private static int health;
    /** Голод. */
    private static int hunger;
    /** Скука. */
    private static int boredom;
    /** Количество съеденного. */
    private static int amountOfEaten;

    public static void SetСharacterParams(int _health, int _hunger, int _boredom) {
        health = _health;
        hunger = _hunger;
        boredom = _boredom;
        amountOfEaten = 0;
    }



    public static void resetFood(){
        amountOfEaten = 0;
    }

    public static boolean PlusEat(int value){
        if(amountOfEaten < 3){
            hunger += value;
            if(hunger > 100) hunger = 100;
            amountOfEaten++;
            return true;
        }
        return false;
    }

    public static void PlusBore(int value){
        boredom += value;
        if(boredom > 100) boredom = 100;
    }

    public static void Sleep(){
        health += 10;   //сколько восстанавливает сон?
        if(health > 100) health = 100;
        resetFood();
    }

    public static void MinusEat(int value){
        hunger -= value;
        if(hunger < 0) hunger = 0;
    }

    public static void MinusBore(int value){
        boredom -= value;
        if(boredom < 0) boredom = 0;
    }

    public static boolean MinusHealth(int value){
        health -= value;
        if(health <= 0) { health = 0; return true; }
        return false;
    }
}
