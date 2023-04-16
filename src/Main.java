import model.*;

import java.io.IOException;
import java.util.Scanner;
public class Main {
    private Scanner reader;
    private Controller controller;
    public Main(){
        reader = new Scanner(System.in);
        controller = new Controller();
    }
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.loadData();
        main.executeProgram();
    }
    private void loadData() throws IOException {
        controller.loadData();
    }
    private void print(Object t){System.out.println(t);}
    public void executeProgram() throws IOException {
        print(mainMenu());
        switch(reader.next()){
            case "1":
                executeProgram();
                controller.saveData();
            case "2":
                executeProgram();
            case "0":
                System.exit(0);
            default:
                print("\n Invalid Option");
                executeProgram();
        }
    }
    private String mainMenu(){
        return  "\n OPTIONS : \n"+
                "(1) Register Country \n"+
                "(2) Show Medals  \n"+
                "(3) Show Total Medals\n"+
                "(4) Show Countries\n"+
                "(0) Exit Program";
    }
}