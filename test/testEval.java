package test;
import BackEnd.Address;
import BackEnd.ConfigurationValue;
import BackEnd.GameDataStorage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class testEval {
    @Test
    public void evalTest()
    {
        ConfigurationValue confi= new ConfigurationValue(3,4,5,0,2000,1000,30,0,5,10000,5);
        GameDataStorage game =new GameDataStorage(2,confi);
        assertEquals(-1,game.nextPivot());
        assertEquals(true,game.addPlayer("p1"));
        assertEquals(-1,game.nextPivot());
        assertEquals(false,game.startTurn());
        assertEquals(true,game.addPlayer("p2"));
        assertEquals(false,game.addPlayer("p3"));
        assertEquals(0,game.nextPivot());
        assertEquals(1,game.nextPivot());
        assertEquals(0,game.nextPivot());
        assertEquals(true,game.startTurn());
        assertEquals(game.listPlayer.get(0).toString(),game.findPlayer("p1").toString());
        assertEquals(null,game.findPlayer("p3"));

        game.nowPlayer.setCityCrew(new Address(1,1));
        game.nowPlayer.setCityCenter(new Address(2,1));

        assertEquals(false,game.nowPlayer.getCityCrew().equals(game.nowPlayer.getCityCenter()));
        game.backToCenter(game.nowPlayer);
        assertEquals(true,game.nowPlayer.getCityCrew().equals(game.nowPlayer.getCityCenter()));
        assertEquals(new Address(1,3),game.directionAddress(new Address(2,3),1));
        assertEquals(new Address(2,4),game.directionAddress(new Address(2,3),2));
        assertEquals(new Address(3,4),game.directionAddress(new Address(2,3),3));
        assertEquals(new Address(3,3),game.directionAddress(new Address(2,3),4));
        assertEquals(new Address(3,2),game.directionAddress(new Address(2,3),5));
        assertEquals(new Address(2,2),game.directionAddress(new Address(2,3),6));

        assertEquals(new Address(1,2),game.directionAddress(new Address(2,2),1));

        assertEquals(new Address(1,3),game.directionAddress(new Address(2,2),2));
        assertEquals(new Address(2,3),game.directionAddress(new Address(2,2),3));
        assertEquals(new Address(3,2),game.directionAddress(new Address(2,2),4));
        assertEquals(new Address(2,1),game.directionAddress(new Address(2,2),5));
        assertEquals(new Address(1,1),game.directionAddress(new Address(2,2),6));
        assertEquals(null,game.directionAddress(new Address(1,1),1));

        game.pivot=0;
        game.nowPlayer=game.listPlayer.get(0);
        game.nowPlayer.setCityCrew(new Address(2,3));
        game.nowPlayer.setCityCenter(new Address(2,3));
        game.listPlayer.get(1).setCityCrew(new Address(1,1));
        game.listPlayer.get(1).setCityCenter(new Address(3,2));
        System.out.println("start  "+game.nowPlayer.getCityCrew().toString());
        System.out.println("1 "+game.nearby(1));

        System.out.println("start  "+game.nowPlayer.getCityCrew().toString());
        System.out.println("5 "+game.nearby(2));
        System.out.println("start  "+game.nowPlayer.getCityCrew().toString());
        System.out.println("5 "+game.nearby(3));
        System.out.println("start  "+game.nowPlayer.getCityCrew().toString());
        System.out.println("5 "+game.nearby(4));

        System.out.println("6 "+game.nearby(5));
        System.out.println("start  "+game.nowPlayer.getCityCrew().toString());
        System.out.println("5 "+game.nearby(6));
        System.out.println("start  "+game.nowPlayer.getCityCrew().toString());
        System.out.println("5 "+game.opponent());




    }

}
