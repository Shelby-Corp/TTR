package gui;


import logic.Logic;


public class Gui{
    public static void main(String[] args) {
        Logic logic = new Logic();
        System.out.println(logic.login("John Smith"));

    }


}
