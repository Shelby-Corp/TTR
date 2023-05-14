package database;

import entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    String url = "jdbc:sqlite:src\\data\\db\\pdd.db";


    Connection connection;
    Statement statement;
    public DataBase(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Возращается объект пользователя
    // Если пользователя в базе нет или введены неверные данные null
    public Student getStudent(String fullName){
        try {
            String query =
                    String.format("SELECT COUNT(*) FROM student WHERE fullName='%s'",
                            fullName);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.getInt(1) == 0)
                return null;

            query = String.format("SELECT idStudent, fullName FROM student WHERE fullName='%s'",
                            fullName);

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
            String query = "SELECT * FROM themes";
            ResultSet resultSet = statement.executeQuery(query);
            List<ThemeBlock> themeBlocks = new ArrayList<>();
            themeBlocks.add(new ThemeBlock(resultSet.getInt("idBlockTheme"), resultSet.getString("name")));
            while (resultSet.next()){
                themeBlocks.add(new ThemeBlock(resultSet.getInt("idBlockTheme"), resultSet.getString("name")));
            }
            return themeBlocks;
        }catch (Exception e){
            e.printStackTrace();
        };
        return null;
    }

    public Integer getNumberOfQuestion(ThemeBlock themeBlock){
        try {
            String query = String.format("SELECT COUNT(*) FROM theme WHERE idBlockTheme=%d", themeBlock.getId());
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
            String query = String.format("SELECT * FROM Answers WHERE idQuestion=%d", question.getId());
            ResultSet resultSet = statement.executeQuery(query);
            result.add(new Answer(resultSet.getInt("idAnswer"),
                    resultSet.getInt("idQuestion"),
                    resultSet.getString("text"),
                    resultSet.getInt("isRight")
                    ));

            while (resultSet.next()){
                result.add(new Answer(resultSet.getInt("idAnswer"),
                        resultSet.getInt("idQuestion"),
                        resultSet.getString("text"),
                        resultSet.getInt("isRight")
                ));
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void saveSession(Session session){
        try {
            String query =
                    "INSERT INTO sessions(id_session, id_student, id_type_testing, date_and_time_of_testing) VALUES (%d, %d, %d, %s)";
            statement.executeQuery(String.format(query,
                    session.getId(),
                    session.getStudentId(),
                    session.getTypeTestingId(),
                    session.getDateOfSession().toString()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveResults(List<Result> results, Session session){
        try {
            String query = "INSERT INTO results(id_session, id_answer, date_and_time_of_answering) VALUES (%d, %d, %s)";
            results.forEach((elem) -> {
                try {
                    statement.executeQuery(String.format(query, session.getId(), elem.getAnswerId(), elem.getDateOfResult().toString()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

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
