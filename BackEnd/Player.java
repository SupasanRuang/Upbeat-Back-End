package BackEnd;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name; //ชื่อของผู้เล่น
    private long budget; //จำนวนเงินที่มี
    private Address CityCrew = null; //สร้าง Address CityCrew
    private Address CityCenter = null; // สร้าง Address CityCenter
    public HashMap<String, Long> PersonalValue = null; //เอาไว้เก็บตัวแปรของผู็เล่นนั้นๆ
    private int player_turn = 0; //จำนวนเทิร์นที่ผู้เล่นเล่นไปแล้ว
    private ArrayList<Address> RegionPossessing = new ArrayList<>(); //เก็บตำแหน่งRegionที่ครอบครองไว้

    public boolean isSurvival() {
        return survival;
    }

    public void setSurvival(boolean survival) {
        this.survival = survival;
    }

    private boolean survival=true;

    public Player(String name, long budget, long row, long col) {
        this.player_turn = 0;
        this.name = name;
        this.budget = budget;
        this.CityCrew = new Address((int) row,(int) col);
        this.CityCenter = new Address((int) row,(int) col);
        this.PersonalValue = new HashMap<String, Long>();
        this.survival=true;
    }

    public void addPersonalValue(String s, long i){
        this.PersonalValue.put(s,i);

    }

    public String getName() {
        return this.name;
    }

    public long getBudget() {
        return this.budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public Address getCityCrew() {
        return this.CityCrew;
    }

    public void setCityCrew(Address cityCrew) {
        this.CityCrew = cityCrew;
    }
    /*รีเทิร์นค่าMapออกมา*/
    public HashMap<String, Long> getPersonalValue() {
        return PersonalValue;
    }

    public int getPlayer_turn() {
        return player_turn;
    }

    public void setPlayer_turn(int player_turn) {
        this.player_turn = player_turn;
    }

    public ArrayList<Address> getRegionPossessing() {
        return RegionPossessing;
    }

    public void setRegionPossessing(ArrayList<Address> regionPossessing) {
        this.RegionPossessing = regionPossessing;
    }
    public void addRegionPossessing(Address regionAddress){
        this.RegionPossessing.add(regionAddress);
    }
    public void loseRegionPossessing(Address regionAddress){
        this.RegionPossessing.remove(regionAddress);
    }

    public Address getCityCenter() {
        return this.CityCenter;
    }

    public void setCityCenter(Address cityCenter) {
        this.CityCenter = cityCenter;
    }

    @Override
    public String toString() {
        StringBuilder PersonalValueString=new StringBuilder();
        if(this.PersonalValue.isEmpty())
        {
            PersonalValueString.append("null");
        }
            else
        for (String name: this.PersonalValue.keySet()) {
            String key = name;
            String value =this.PersonalValue.get(name).toString();
            PersonalValueString.append(" { " +key + " " + value+" },");
        }
        StringBuilder RegionPossessingString=new StringBuilder();
        if(this.RegionPossessing.isEmpty())
        {
            RegionPossessingString.append("null");
        }
        else
        for(Address address:RegionPossessing)
        {
            PersonalValueString.append(" (" +address.PositionColumn() + "," + address.PositionRow()+"),");
        }

        return "Player{" +
                "name='" + this.name + "'" +
                ", budget=" + this.budget +
                ", CityCrew=" + (this.CityCrew==null?"null":"(" +this.CityCrew.PositionColumn() + "," + this.CityCrew.PositionRow()+")" )+
                ", CityCenter=" + (this.CityCenter==null?"null":"(" +this.CityCenter.PositionColumn() + "," + this.CityCenter.PositionRow()+")" )+
                ", PersonalValue=" + PersonalValueString.toString() +
                ", player_turn=" + this.player_turn +
                ", RegionPossessing=" + RegionPossessingString.toString() +
                ", survival=" + this.survival +
                '}';
    }
}
