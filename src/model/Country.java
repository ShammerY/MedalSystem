package model;

public class Country implements Comparable<Country>{
    private String name;
    private int gold;
    private int silver;
    private int bronze;
    public Country(String name){
        this.name = name;
        this.gold = 0;
        this.silver = 0;
        this.bronze = 0;
    }
    public String getName() {
        return name;
    }
    public int getGold() {
        return gold;
    }
    public int getSilver() {
        return silver;
    }
    public int getBronze() {
        return bronze;
    }
    public void addGold(int add){
        this.gold+=add;
    }
    public void addSilver(int add){this.silver+=add;}
    public void addBronze(int add){this.bronze+=add;}
    public int getTotal(){return this.gold + this.silver + this.bronze;}
    @Override
    public int compareTo(Country c){
        int criteria = this.gold - c.getGold();
        if(criteria==0){
            criteria = this.silver - c.getSilver();
            if(criteria==0){
                criteria = this.bronze - c.getBronze();
                if(criteria ==0){
                    return this.name.compareTo(c.getName());
                }else{
                    return criteria*-1;
                }
            }else{
                return criteria * -1;
            }
        }else{
            return criteria*-1;
        }
    }
}
