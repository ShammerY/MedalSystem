package model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
}
