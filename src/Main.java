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
                print(registerCountry());
                controller.saveData();
                break;
            case "2":
                print(controller.showMedals());
                break;
            case "3":
                print(controller.showTotalMedals());
                break;
            case "4":
                print(controller.showCountries());
                break;
            case "0":
                System.exit(0);
            default:
                print("\n Invalid Option");
        }
        executeProgram();
    }
    private String registerCountry(){
        print("\n Enter : country::medal::amount\n Format Example : Australia::gold::3\n");
        reader.nextLine();
        String[] data = reader.nextLine().split("::");
        return controller.addCountry(data);
    }
    private String mainMenu(){
        return  "\n OPTIONS : \n"+
                "(1) Register Country / Add Medals\n"+
                "(2) Show Medals  \n"+
                "(3) Show Total Medals\n"+
                "(4) Show Countries\n"+
                "(0) Exit Program";
    }
}