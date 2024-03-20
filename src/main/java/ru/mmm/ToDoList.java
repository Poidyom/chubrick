package ru.mmm;

import java.util.ArrayList;

/** Класс - лист заданий. */
public class ToDoList {
    private static ArrayList<String> tasks;

    public static void SetToDoList() {
        tasks = new ArrayList<String>();
    }

    public static void SetToDoList(ArrayList<String> _tasks) {
        tasks = _tasks;
    }

    public static boolean AddTask(String taskText) {
        for (String str : tasks){
            if(str.equals(taskText)) return false;
        }
        tasks.add(taskText);
        return true;
    }

    public static void DeleteTask(int num){
        tasks.remove(num);
    }

    public static void markAsCompleted(int num){
        System.out.println("Great success");
        Chubrick.PlusBore(5);
        DeleteTask(num);
    }
}
