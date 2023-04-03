package BackEnd;

public interface GameData {

//    public void Invest(int x,int y,int money,int budget,int deposit)


    public Player findPlayer(String name);
    public long getIntRate(Address address);
    public long getCurrow();
    public long getCurcol();
    public long getRows();
    public long getCol();
    public long getMaxdeposit();

    void backToCenter(Player player);

    int distance(Address startAddress, Address endAddress);

    public boolean relocate(Address targetAddress);
    public boolean invest(Address targetAddress,long money);
    public boolean done();
    public boolean move(long direction);
    public boolean collect(Address targetAddress,long money);
    public boolean shoot(Address targetAddress,long money);
    public boolean loseCenter(Player player);
    public boolean loseRegion();
    public long useRandom();
    public long getRegionDeposit(Address address);
    public long opponent();
    public long nearby(long direction);
    public Address directionAddress(Address targetAddress,long direction);
}
