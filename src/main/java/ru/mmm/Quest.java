package ru.mmm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.IOException;
import java.io.File;


/** Класс квеста, контроллер*/
@NoArgsConstructor
public class Quest {
    /** Решение первого таска */
    private static String key1 = "нажми:одинкормидвасписокедаеда";
    /** Решение третьего таска */
    private static String key2 = "введиэтустрокувцель";
    /** Подсказка второго таска */
    private static String clue = "На стене есть невидимая кнопка. Нажми ее";
    /** Код первого таска */
    private static ArrayList<Integer> key1Code = new ArrayList<>(Arrays.asList(2, 1, 1));
    /** Русский строчный алфавит */
    private static String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    /** Массив состояний тасков */
    @Getter @Setter
    private static ArrayList<Integer> questState = new ArrayList<>(Arrays.asList(0, 0, 0));
    /** Название файла */
    private static String fileName = "quest.txt";
    /** Массив целочисленных значений, передающий введенные значения */
    private static ArrayList<Integer> inputs = new ArrayList<Integer>();

    /**
     * Вывести нынешний активный таск
     * @return целочисленное значене: -1 при отстутсвтии активных таск, номер таска или 3, если все таски выполнены
     */
    public static Integer currentState()
    {
        if (questState.contains(1))
        {
            return questState.indexOf(1);
        }
        if (questState.indexOf(0) == 0)
        {
            return -1;
        }
        else
        {
            return 3;
        }
    }

    /**
     * Задать новое состояние таску
     * @param pos номер таска, который нужно изменить
     * @param state новое значение
     */
    public static void setState(Integer pos, Integer state)
    {
        questState.set(pos, state);
    }

    /**
     * Генератор сдвинутого на определенное значение алфавита
     * @param shiftNumber число сдвига
     * @return преобразованный алфавит
     */
    public static String shiftGenerator(Integer shiftNumber)
    {
        StringBuilder newAlpahbet = new StringBuilder();
        for(int i = 0; i < alphabet.length(); i++) {
            int newIndex = (i + shiftNumber) % alphabet.length();
            newAlpahbet.append(alphabet.charAt(newIndex));
        }
        return newAlpahbet.toString();
    }

    /**
     * Генератор алфавита с измененным порядком букв
     * @return преобразованный алфавит
     */
    public static String simpleChange()
    {
        StringBuilder newAlphabet = new StringBuilder();
        StringBuilder copyAlphabet = new StringBuilder(alphabet);
        Random rand = new Random();
        Integer randIndex;
        for(int i = 0; i < alphabet.length(); i++) {
            randIndex = rand.nextInt(copyAlphabet.length());
            newAlphabet.append(copyAlphabet.charAt(randIndex));
            copyAlphabet.deleteCharAt(randIndex);
        }
        return newAlphabet.toString();
    }

    /**
     * Шифратор Цезаря
     * @return зашифрованное сообщение методом Цезаря
     */
    public static String caesarCipher()
    {
        StringBuilder code = new StringBuilder();
        Random rand = new Random();
        String newAlphabet = shiftGenerator(rand.nextInt(11) + 1);
        for (int i = 0; i < key1.length(); i++)
        {
            int index = alphabet.indexOf(key1.charAt(i));
            if (index != -1) {
                code.append(newAlphabet.charAt(index));
            } else {
                code.append(key1.charAt(i));
            }
        }
        return code.toString();
    }

    /**
     * Шифратор простой замены
     * @return зашифрованное сообщение методом простой замены
     */
    public static String simpleChangeCipher()
    {
        StringBuilder code = new StringBuilder();
        String newAlphabet = simpleChange();
        for (int i = 0; i < key2.length(); i++)
        {
            int index = alphabet.indexOf(key2.charAt(i));
            if (index != -1) {
                code.append(newAlphabet.charAt(index));
            } else {
                code.append(key2.charAt(i));
            }
        }
        return code.toString();
    }

    /**
     * Изменить значение файла на рабочем столе или создать файл на рабочем столе с заданным значением
     * @param text новое содержание файла
     */
    public static void changeContentOfFile(String text)
    {
        File file = new File(System.getProperty("user.home") + "/Desktop/" + fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка соответствия заданного массива началу массива ключа
     * @return резлультат сравнения
     */
    public static boolean isPrefix(ArrayList<Integer> list1)
    {
        if (list1.size() > key1Code.size())
        {
            return false;
        }
        for (int i = 0; i < list1.size(); i++)
        {
            if (!list1.get(i).equals(key1Code.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Рекурсивный поиск у заданного массива подмассива, соответствующему началу массива ключа
     * @param list заданный массив
     * @return подмассив, который соответствует началу массива ключа
     */
    public static ArrayList<Integer> Checking(ArrayList<Integer> list)
    {
        if ((list.size() == 1) && (list.get(0) == key1Code.get(0)))
        {
            return list;
        }
        if ((list.size() == 1) && (list.get(0) != key1Code.get(0)))
        {
            list.clear();
            return list;
        }
        if (isPrefix(list))
        {
            return list;
        }
        else {
            return Checking(new ArrayList<>(list.subList(1, list.size())));
        }
    }

    /**
     * Создание условий первого таска: формирование шифра и создание файла
     */
    public static void creatingTaskOne()
    {
        setState(0, 1);
        changeContentOfFile(caesarCipher());
    }
    /**
     * Создание условий второго таска: добавление в файл подсказки
     */
    public static void creatingTaskTwo()
    {
        setState(1, 1);
        changeContentOfFile(clue);
        //handlerTaskOfTwo();;

    }
    /**
     * Создание условий третьего таска: формирование шифра и изменение файла
     */
    public static void creatingTaskThree()
    {
        setState(2, 1);
        changeContentOfFile(simpleChangeCipher());
        //handlerTaskOfTree();
    }
    /**
     * Обработчик первого таска: отслеживает последовательность нажатых кнопок на соответствие коду
     * @param input тип нажатой кнопки
     */
    public static void handlerTaskOne(Integer input)
    {
        if (currentState() == 0)
        {
            inputs.add(input);
            if (inputs.equals(key1Code))
            {
                setState(0, 2);
                System.out.println("Первый таск выполнен");
                creatingTaskTwo();
            }
            else
            {
                inputs = Checking(inputs);
            }
        }
    }
    /**
     * Обработчик второго таска: запускает третий)
     */
    public static void handlerTaskOfTwo()
    {
        setState(1, 2);
        System.out.println("Второй таск выполнен");
        creatingTaskThree();
    }

    /**
     * Обработчик третьего таска: сравнивает полученную строку с ключом
     * @param input введенная строка
     */
    public static void handlerTaskOfTree(String input)
    {
        if (currentState() == 2)
        {
            if (input.equals(key2))
            {
                setState(2, 2);
                System.out.println("Третий таск выполнен");
                changeContentOfFile("YOU DID IT!!! YAPPI");
            }
        }
    }
}