package database;

import entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    String url = "jdbc:sqlite://localhost:";
    String user = "root";
    String password = "";

    Connection connection;
    Statement statement;
    public DataBase(){
        try {
            Class.forName("com.sqlite.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Возращается объект пользователя
    // Если пользователя в базе нет или введены неверные данные null
    public Student getStudent(String fullName, String password){
        try {
            String query =
                    String.format("SELECT COUNT(*) FROM students WHERE fullName='%s' AND password='%s'",
                            fullName,
                            password);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.getInt(1) == 0)
                return null;

            query = String.format("SELECT id, fullName FROM students WHERE fullName='%s' AND password='%s'",
                            fullName,
                            password);
            resultSet = statement.executeQuery(query);

            return new Student(resultSet.getInt(1), resultSet.getString(2));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Получение коллекции всех режимов тестирования
    public List<TypeTesting> getAllTypeTesting(){
        try {
            List<TypeTesting> result = new ArrayList<>();
            String query = "SELECT * FROM typeTesting";
            ResultSet resultSet = statement.executeQuery(query);
            result.add(new TypeTesting(resultSet.getInt("idTypeTest"),
                    resultSet.getString("description")));

            while (resultSet.next()){
                result.add(new TypeTesting(resultSet.getInt("idTypeTest"),
                        resultSet.getString("description")));
            }
            return result;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Session> getAllSessionOfUser(Integer userId){
        try {
            List<Session> sessions = new ArrayList<>();
            String query = String.format("SELECT COUNT(*) FROM sessions WHERE idStudent=%d", userId);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.getInt(1) == 0)
                return null;

            query = String.format("SELECT * FROM sessions WHERE idStudent=%d", userId);
            resultSet = statement.executeQuery(query);

            sessions.add(new Session(resultSet.getInt("idSession"),
                    userId,
                    resultSet.getInt("idTypeTesting")));
            while (resultSet.next()){
                sessions.add(new Session(resultSet.getInt("idSession"),
                        userId,
                        resultSet.getInt("idTypeTesting")));
            }
            return sessions;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ThemeBlock> getAllThemeBlocks(){
        try {
            String query = "SELECT * FROM themeBlocks";
            ResultSet resultSet = statement.executeQuery(query);
            List<ThemeBlock> themeBlocks = new ArrayList<>();
            themeBlocks.add(new ThemeBlock(resultSet.getInt("idBlockTheme"), resultSet.getString("name"),
                    resultSet.getString("description")));
            while (resultSet.next()){
                themeBlocks.add(new ThemeBlock(resultSet.getInt("idBlockTheme"), resultSet.getString("name"),
                        resultSet.getString("description")));
            }
            return themeBlocks;
        }catch (Exception e){
            e.printStackTrace();
        };
        return null;
    }

    public Integer getNumberOfQuestion(ThemeBlock themeBlock){
        try {
            String query = String.format("SELECT COUNT(*) FROM questions WHERE idBlockTheme=%d", themeBlock.getId());
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet.getInt(1);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    public Question getRandomQuestion(ThemeBlock block){
        try {
            String query = String.format("SELECT * FROM questions WHERE idBlockTheme=%d", block.getId());
            ResultSet resultSet = statement.executeQuery(query);
            return new Question(resultSet.getInt("idQuestion"), resultSet.getString("questionText"));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Answer> getAnswers(Question question){
        try {
            List<Answer> result = new ArrayList<>();
            String query = "SELECT * FROM Answers WHERE ";
            ResultSet resultSet = statement.executeQuery(query);
            result.add(new Answer(resultSet.getInt("idAnswer"),
                    resultSet.getInt("idQuestion"),
                    resultSet.getString("text"),
                    resultSet.getBoolean("isRight")
                    ));

            while (resultSet.next()){
                result.add(new Answer(resultSet.getInt("idAnswer"),
                        resultSet.getInt("idQuestion"),
                        resultSet.getString("text"),
                        resultSet.getBoolean("isRight")
                ));
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void saveSession(Session session){}

    public void saveResults(List<Result> results){}

    public List<Picture> getPictures(Question question){
        try {
            List<Picture> result = new ArrayList<>();
            String query = String.format("SELECT * FROM Pictures WHERE idQuestion=%d", question.getId());
            ResultSet resultSet = statement.executeQuery(query);
            result.add(new Picture(resultSet.getInt("idQuestion"),
                    resultSet.getInt("numberOfPicture"),
                    resultSet.getString("imagePath")));
            while (resultSet.next()){
                result.add(new Picture(resultSet.getInt("idQuestion"),
                        resultSet.getInt("numberOfPicture"),
                        resultSet.getString("imagePath")));
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void EndWork() {
        try {
            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
