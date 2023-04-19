package logic;

import entities.*;
import database.DataBase;

import java.util.*;


public class Logic {
    public DataBase db;
    public Student student;
    public Session session;

    public TypeTesting typeTesting;

    public static void main(String[] args) {

    }

    public Logic() {
        db = new DataBase();
    }

    // Авторизация и ауентификация, если пользователя нет или данные неверны возращаем false, иначе true
    public Boolean login(String fullName, String password){
        student = db.getStudent(fullName, password);
        return student != null;
    }

    public List<TypeTesting> getAllTypeTesting(){
        if (student == null)
            return null;
        return db.getAllTypeTesting();
    }

    public void startSession(TypeTesting selectedTypeTesting){

        typeTesting = selectedTypeTesting;

        Optional<Integer> number = db.getAllSessionOfUser(student.getId()).stream().max((a, b)-> a - b);

        if (number.isPresent())
            session = new Session(number.get(), student.getId(), selectedTypeTesting.getId());
        else
            session = new Session(1, student.getId(), selectedTypeTesting.getId());
    }

    public List<Question> getQuestions(){
        Integer typeId = typeTesting.getId();
        if (typeId == 0){
            List<ThemeBlock> themeBlocks = db.getAllThemeBlocks();
            Map<ThemeBlock, Integer> numbersForTheme = new HashMap<>();
            int numberOfQuestion = 20;
            ListIterator<ThemeBlock> iterator = themeBlocks.listIterator();
            while (numberOfQuestion > 0){
                if (iterator.hasNext()){
                    ThemeBlock themeBlock = iterator.next();
                    if (!numbersForTheme.containsKey(themeBlock)){
                        if (numberOfQuestion > 1) {
                            int localNumber = (int) Math.round(Math.random() * 2);
                            numbersForTheme.put(themeBlock, localNumber);
                            numberOfQuestion -= localNumber;
                        }
                        else {
                            numbersForTheme.put(themeBlock, 1);
                            break;
                        }
                    }
                } else
                    iterator = themeBlocks.listIterator();
            }
        }

    }
}
