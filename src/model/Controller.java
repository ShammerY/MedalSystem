package model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class Controller {
    ArrayList<Country> countries;
    public Controller(){
        countries = new ArrayList<Country>();
    }
    public void saveData() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("data.json"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        Gson gson = new Gson();
        writer.write(gson.toJson(countries));
        writer.flush();
        fos.close();
    }
    public void loadData() throws IOException{
        try{
            FileInputStream fis= new FileInputStream(new File("data.json"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder msj = new StringBuilder();
            String line = "";
            while((line = reader.readLine())!=null){
                msj.append(line);
            }
            Gson gson = new Gson();
            Country[] array = gson.fromJson(msj.toString(),Country[].class);
            for(Country c:array){
                countries.add(c);
            }
            fis.close();
        }catch(FileNotFoundException ignored){}
    }
    public String addCountry(String[] data){
        try{
            if(!validateMedalType(data[1])){return "\n ERROR : INVALID MEDAL TYPE";}
            Country country = searchCountry(data[0]);
            addMedal(country,data[1],Integer.parseInt(data[2]));
            return "\n Country added Succesfully";
        }catch(NumberFormatException ex){
            return "\n ERROR : INVALID MEDAL AMOUNT";
        }catch(IndexOutOfBoundsException ex){
            return "\n ERROR : NOT ENOUGH INPUT VALUES";
        }
    }
    private void addMedal(Country country,String medal,int amount){
        switch(medal.toUpperCase()){
            case "GOLD":
                country.addGold(amount);
                break;
            case "SILVER":
                country.addSilver(amount);
                break;
            case "BRONZE":
                country.addBronze(amount);
                break;
        }
    }
    private boolean validateMedalType(String medal){
        medal = medal.toUpperCase();
        if(medal.equals("GOLD")||medal.equals("SILVER")||medal.equals("BRONZE")){
            return true;
        }else{
            return false;
        }
    }
    private Country searchCountry(String name){
        for(int i=0;i<countries.size();i++){
            if(countries.get(i).getName().equals(name)){
                return countries.get(i);
            }
        }
        Country c = new Country(name);
        countries.add(c);
        return c;
    }
    public String showCountries(){
        Country temp = null;
        Country[] list = toArray();
        for(int i=0;i<list.length;i++){
            for(int j=1;j<list.length-i;j++){
                if((list[j].getName().compareTo(list[j-1].getName()))<0){
                    temp = list[j];
                    list[j] = list[j-1];
                    list[j-1] = temp;
                }
            }
        }
        return printArray(list);
    }
    private Country[] toArray(){
        Country[] list = new Country[countries.size()];
        for(int i=0;i<list.length;i++){
            list[i] = countries.get(i);
        }
        return list;
    }
    private String printArray(Country[] array){
        StringBuilder msj = new StringBuilder();
        msj.append("\n Country | GOLD | SILVER | BRONZE");
        for(Country c:array){
            msj.append("\n"+c.getName()+" : "+c.getGold()+" : "+c.getSilver()+" : "+c.getBronze());
        }
        return msj.toString();
    }
    public String printList(){
        StringBuilder msj = new StringBuilder();
        for(Country c:countries){
            msj.append("\n"+c.getName()+" : "+c.getGold());
        }
        return msj.toString();
    }
}
