package ru.mmm;

import java.util.ArrayList;

/** Класс - лист заданий. */
public class ToDoList {
    /** Список заданий. */
    private static ArrayList<String> tasks;
    /** Задать пустой список заданий. */
    public static void SetToDoList() {
        tasks = new ArrayList<String>();
    }
    /**
     * Задать пустой список заданий.
     * @param _tasks список заданий
     */
    public static void SetToDoList(ArrayList<String> _tasks) {
        tasks = _tasks;
    }
    /**
     * Добавление задачи.
     * @param taskText текст добавляемой задачи
     */
    public static boolean AddTask(String taskText) {
        for (String str : tasks){
            if(str.equals(taskText)) return false;
        }
        tasks.add(taskText);
        return true;
    }
    /**
     * Удаление задачи.
     * @param num порядковый номер удаляемой задачи
     */
    public static void DeleteTask(int num){
        tasks.remove(num);
    }

    /**
     * Удаление задачи.
     * @param taskText текст удаляемой задачи
     */
    public static void DeleteTask(String taskText){
        tasks.remove(num);
    }
    /**
     * Отметка о выполнении задачи.
     * @param num порядковый номер выполненной задачи
     */
    public static void markAsCompleted(int num){
        System.out.println("Great success");
        Chubrick.PlusBore(5);
        DeleteTask(num);
    }
}
