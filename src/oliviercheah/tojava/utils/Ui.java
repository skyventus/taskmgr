package oliviercheah.tojava.utils;

import java.util.Scanner;


public class Ui {

    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public static void printWelcome(){
        System.out.println("Welcome to task manager lvl 10!");
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public String readUserCommand() {
        System.out.print("Your task? ");
        return in.nextLine().trim();
    }
    public String readUserChoice() {
        System.out.print("Which file? ");
        return in.nextLine().trim();
    }

    public static void showCommands(){
        System.out.println("1. todo <input task>");
        System.out.println("--Example: todo submit report");
        System.out.println("2. deadline <input task> /by <date>");
        System.out.println("--Example: deadline submit report /by 11-11-2018");
        System.out.println("3. update <The task index>");
        System.out.println("--Example: update 1");
        System.out.println("4. Delete <The task index>");
        System.out.println("--Example: delete 1");
        System.out.println("5. done <the task index>");
        System.out.println("--Example: done 1");
        System.out.println("6. print");
        System.out.println("7. save");
        System.out.println("8. exit");
    }

    public static void showToUser(String message){
        System.out.println(message);
    }
}
