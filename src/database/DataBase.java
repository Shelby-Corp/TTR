package database;

import entities.*;

import java.util.List;

public class DataBase {
    public DataBase(){

    }

    // Возращается объект пользователя
    // Если пользователя в базе нет или введены неверные данные null
    public Student getStudent(String fullName, String password){
        return null;
    }

    // Получение коллекции всех режимов тестирования
    public List<TypeTesting> getAllTypeTesting(){
        return null;
    }

    public List<Integer> getAllSessionOfUser(Integer userId){
        return null;
    }

    public List<ThemeBlock> getAllThemeBlocks(){
        return null;
    }
}
