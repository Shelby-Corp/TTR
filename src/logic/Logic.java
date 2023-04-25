package logic;

import entities.*;
import database.DataBase;

import java.util.*;


public class Logic {
    public DataBase db;
    public Student student;
    public Session session;

    public TypeTesting typeTesting;

    public List<Result> results = new ArrayList<>();

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

        List<Session> sessions = db.getAllSessionOfUser(student.getId());

        if (sessions.size() > 0)
            session = new Session(sessions.stream().max((a, b) -> a.id - b.id).get().getId() + 1,
                    student.getId(),
                    selectedTypeTesting.getId());
        else
            session = new Session(1,
                    student.getId(),
                    selectedTypeTesting.getId());
    }

    public Map<Question, List<Answer>> getQuestions(ThemeBlock chosenThemeBlock){

        Integer typeId = typeTesting.getId();
        List<ThemeBlock> themeBlocks = db.getAllThemeBlocks();

        Map<ThemeBlock, Integer> numbersForTheme = new HashMap<>();

        Map<Question, List<Answer>> questionAnswerMap = new HashMap<>();

        // Стандартное тестирование
        if (typeId == 0){
            int numberOfQuestion = 20;
            ListIterator<ThemeBlock> iterator = themeBlocks.listIterator();
            while (numberOfQuestion > 0){
                if (iterator.hasNext()){
                    ThemeBlock themeBlock = iterator.next();
                    if (!numbersForTheme.containsKey(themeBlock) || numbersForTheme.get(themeBlock) == 1){
                        if (numberOfQuestion > 1 && numbersForTheme.get(themeBlock) != 1) {
                            int localNumber = (int) Math.round(Math.random() * 2);
                            numbersForTheme.put(themeBlock, localNumber);
                            numberOfQuestion -= localNumber;
                        }
                        else {
                            numbersForTheme.put(themeBlock, 1);
                        }
                    }
                } else
                    iterator = themeBlocks.listIterator();
            }
        // Тестирование по блоку
        } else {
            numbersForTheme.put(chosenThemeBlock, db.getNumberOfQuestion(chosenThemeBlock));
        }
        // генерируем по сгенерированным блокам вопросы
        for (ThemeBlock block: numbersForTheme.keySet()) {
            Question prevQuestion;
            Question nowQuestion = null;
            for (int i = 0; i < numbersForTheme.get(block); i++) {
                prevQuestion = db.getRandomQuestion(block);
                while (prevQuestion.equals(nowQuestion)) {
                    prevQuestion = db.getRandomQuestion(block);
                }
                nowQuestion = prevQuestion;
                questionAnswerMap.put(nowQuestion, db.getAnswers(nowQuestion));
            }
        }

        return questionAnswerMap;
    }

    public void AppendStudentAnswer(Answer answer) {
        results.add(new Result(answer.id, session.id, new Date()));
    }

    public void EndSession(){
        session.setDateOfSession(new Date());
        db.saveResults(results);
        db.saveSession(session);
    }

    public List<Picture> getPictures(Question question){
        return db.getPictures(question);
    }
}
