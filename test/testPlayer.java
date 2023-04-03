package test;

import BackEnd.Address;
import BackEnd.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
public class testPlayer {
    @Test
    public void playerTest()
    {

        Player p=new Player("55",1000,5,5);
        assertEquals("Player{name='55', budget=1000, CityCrew=(5,5), CityCenter=(5,5), PersonalValue=null, player_turn=0, RegionPossessing=null, survival=true}",p.toString());
        ArrayList<Address> listRegion=new ArrayList<>(p.getRegionPossessing());
        p.addRegionPossessing(new Address(9,9));
        listRegion.add(new Address(9,9));
        assertEquals(listRegion,p.getRegionPossessing());
        p.loseRegionPossessing(new Address(9,9));
        listRegion.remove(new Address(9,9));
        assertEquals(listRegion,p.getRegionPossessing());
        HashMap<String,Long> mapVal=new HashMap<>(p.getPersonalValue());
        mapVal.put("ss", Long.valueOf(55));
        p.addPersonalValue("ss",Long.valueOf(55));
        assertEquals(mapVal,p.getPersonalValue());

    }

}
