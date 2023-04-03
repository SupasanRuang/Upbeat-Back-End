package test;

import BackEnd.Address;
import BackEnd.Region;
import BackEnd.Territory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testRegion {
    @Test
    public  void regionTest()
    {
        Region land=new Region(10000,5,1,2);
        assertEquals("Region{posRow=1, posCol=2, deposit=0, max_dep=10000, interest_pct=5, Owner='', CityCenter=false}",land.toString());
        assertEquals(false,land.isOwner("gg"));
        assertEquals("",land.getOwner());
        assertEquals(true,land.canInvest("gg"));
        assertEquals("",land.getOwner());

        land.changeOwner("gg");
        assertEquals(true,land.isOwner("gg"));
        assertEquals("gg",land.getOwner());
        assertEquals(true,land.canInvest("gg"));
        assertEquals(false,land.canInvest("ff"));
        land.changeCityCenter(true);
        land.setDeposit(5000);
        assertEquals("Region{posRow=1, posCol=2, deposit=5000, max_dep=10000, interest_pct=5, Owner='gg', CityCenter=true}",land.toString());
        assertEquals(new Address(1,2),land.getAddress());
        assertEquals(false,land.collect(10000));
        assertEquals(false,land.collect(2500));
        assertEquals("Region{posRow=1, posCol=2, deposit=2500, max_dep=10000, interest_pct=5, Owner='gg', CityCenter=true}",land.toString());
        assertEquals(true,land.collect(2500));
        assertEquals("Region{posRow=1, posCol=2, deposit=0, max_dep=10000, interest_pct=5, Owner='', CityCenter=false}",land.toString());
        assertEquals(true,land.invest(3000));
        assertEquals(-3000,land.giveDeposit("gg"));
        land.changeOwner("gg");
        assertEquals(false,land.invest(8000));
        assertEquals(10000,land.giveDeposit("gg"));

        assertEquals(-10000,land.giveDeposit("ff"));
        land.setDeposit(5000);
        assertEquals(12,land.Int(2));
        land.updateDeposit(2);
        assertEquals( 5600,land.giveDeposit("gg"));
        assertEquals(false,land.beShot(600));
        assertEquals( 5000,land.giveDeposit("gg"));
        assertEquals(true,land.beShot(6000));
        assertEquals( 0,land.giveDeposit(""));
        assertEquals(true,land.isOwner(""));
        Territory tMap=new Territory(3,5,10000,5);
        Region land2=new Region(10000,5,3,2);

        assertEquals(land2.toString(),tMap.getRegion(new Address(3,2)).toString());





    }

}
