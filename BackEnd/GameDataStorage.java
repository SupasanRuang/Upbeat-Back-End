package BackEnd;

import ErrorAndException.EvalError;

import java.util.*;

public class GameDataStorage implements GameData {
    private static Random rand;
    public static Random getRandom()
    {
        if(GameDataStorage.rand==null)
        {
            rand=new Random(System.currentTimeMillis());
        }

        return rand;
    }
    public  int pivot;

    public int numberPlayer;
    public ArrayList<Player> listPlayer;
    public Territory TerritoryMap;
    public ConfigurationValue configurationValue;
    public Player nowPlayer;
    int round;

    public GameDataStorage(int numberPlayer, ConfigurationValue configurationValue) {
        this.numberPlayer = numberPlayer;
        this.listPlayer = new ArrayList<>(numberPlayer);
        this.TerritoryMap = new Territory(configurationValue.getM() ,configurationValue.getN(),configurationValue.getMax_dep(),configurationValue.getInterest_pct());
        this.configurationValue = configurationValue;
        this.nowPlayer = null;
        this.round=0;
        this.pivot=-1;
    }
    public boolean addPlayer(String name)
    {
        if(listPlayer.size()>=numberPlayer)
        {
            return false;
        }
        long randRow=GameDataStorage.getRandom().nextLong(configurationValue.getM())+1;
        long randCol=GameDataStorage.getRandom().nextLong(configurationValue.getN())+1;
        Region tempRegion =this.TerritoryMap.getRegion(new Address(randRow,randCol));
        while (tempRegion.isCityCenter()){
            randRow=GameDataStorage.getRandom().nextLong(configurationValue.getM())+1;
            randCol=GameDataStorage.getRandom().nextLong(configurationValue.getN())+1;
            tempRegion =this.TerritoryMap.getRegion(new Address(randRow,randCol));
        }

        Player tempPlayer =new Player(name,configurationValue.getInit_budget(),randRow,randCol);
        tempRegion.changeOwner(name);
        tempRegion.changeCityCenter(true);
        tempRegion.invest(configurationValue.getInit_center_dep());
        this.listPlayer.add(tempPlayer);
        return true;

    }
    public int nextPivot()
    {
        int i=0;
        while (!listPlayer.isEmpty()&&i<listPlayer.size()-1)
        {
            this.pivot++;
            if(pivot>=listPlayer.size())
            {
                pivot=0;
            }
            this.nowPlayer=listPlayer.get(pivot);
            if(nowPlayer.isSurvival())
            {
                return pivot;
            }
            i++;

        }
         return -1;/*list empty or have last one*/
    }
    public boolean startTurn(){
        int status=this.nextPivot();
        if(status<0)
        {
            return false;//endgame
        }
        this.nowPlayer.setPlayer_turn(this.nowPlayer.getPlayer_turn()+1);
        for(Address curAddress:this.nowPlayer.getRegionPossessing())
        {
            Region curRegion =this.TerritoryMap.getRegion(curAddress) ;
            curRegion.updateDeposit(this.nowPlayer.getPlayer_turn());
        }
        return true;
    }



    @Override
    public Player findPlayer(String name) {
        for (int i = 0; i < this.listPlayer.size(); i++) {
            if (this.listPlayer.get(i).getName().equals(name)) {
                return this.listPlayer.get(i);
            }
        }
        return null;
    }

    @Override
    public void backToCenter(Player player) {
        player.setCityCrew(player.getCityCenter());
    }

    @Override
    public int distance(Address startAddress, Address endAddress) {
        Queue<Address> queue = new LinkedList<>();
        Set<Address> collectVisited = new HashSet<>();
        Map<Address, Integer> dist = new HashMap<>();
        collectVisited.add(startAddress);
        dist.put(startAddress, 0);
        collectVisited.add(startAddress);
        queue.add(startAddress);
        while (!queue.isEmpty()) {
            Address curAddress = queue.poll();
            if (collectVisited.contains(endAddress)) {
                return dist.get(endAddress);
            }

            for (int i = 1; i <= 6; i++) {
                Address tempAddress = this.directionAddress(curAddress, i);
                if (tempAddress != null) {
                    if (!collectVisited.contains(tempAddress)) {
                        int integer = dist.get(curAddress);
                        dist.put(tempAddress, integer + 1);
                        queue.add(tempAddress);
                        collectVisited.add(tempAddress);
                    }
                }
            }
        }
        return dist.get(endAddress);
    }


    @Override
    public boolean relocate(Address targetAddress){
        Address centerAddress=this.nowPlayer.getCityCenter();

        int cost = 5 * this.distance(targetAddress, centerAddress) + 10;

        Region targetRegion=this.TerritoryMap.getRegion(targetAddress);
        if (this.nowPlayer.getBudget() >= cost&&targetRegion.isOwner(this.nowPlayer.getName())) {
            this.nowPlayer.setBudget(this.nowPlayer.getBudget() - cost);
            Region centerRegion=this.TerritoryMap.getRegion(centerAddress);
            centerRegion.changeCityCenter(false);
            targetRegion.changeCityCenter(true);
            this.nowPlayer.setCityCenter(targetAddress);
            return true;

        }


        return false;
    }

    @Override
    public boolean collect(Address targetAddress, long money) {
        if (nowPlayer.getBudget() > 1) {
            nowPlayer.setBudget(nowPlayer.getBudget() - 1);
            Region tempRegion = this.TerritoryMap.getRegion(targetAddress);
            if (this.nowPlayer.getBudget() >= money && tempRegion.getOwner().equals(this.nowPlayer.getName())) {
                boolean loseLand = tempRegion.collect(money);
                this.nowPlayer.setBudget(this.getBudget() + money);
                if (loseLand) {
                    if (this.nowPlayer.getCityCenter().equals(targetAddress)) {

                        this.loseCenter(nowPlayer);
                        return true;
                    }
                    this.nowPlayer.loseRegionPossessing(targetAddress);
                }
            }
        }
        return false;
    }


    @Override
    public boolean shoot(Address targetAddress, long money)throws ArrayIndexOutOfBoundsException {
        if (nowPlayer.getBudget() > 1) {
            nowPlayer.setBudget(nowPlayer.getBudget() - 1);
            Region tempRegion = this.TerritoryMap.getRegion(targetAddress);
            if (this.nowPlayer.getBudget() >= money) {
                String ownerName = tempRegion.getOwner();
                boolean loseLand = tempRegion.beShot(money);
                if (loseLand) {
                    Player shootedPlayer = this.findPlayer(ownerName);
                    if (shootedPlayer.getCityCenter().equals(targetAddress)) {

                        this.loseCenter(shootedPlayer);
                        if (nowPlayer.getName().equals(ownerName)) {
                            return true;
                        }
                    }
                    shootedPlayer.loseRegionPossessing(targetAddress);
                }

            }
        }
        return false;
    }

    @Override
    public boolean invest(Address targetAddress, long money) {
        if (this.nowPlayer.getBudget() > 1) {
            this.nowPlayer.setBudget(this.nowPlayer.getBudget() - 1);
            Region tempRegion = this.TerritoryMap.getRegion(targetAddress);
            if (this.nowPlayer.getBudget() >= money && tempRegion.canInvest(this.nowPlayer.getName())) {
               boolean canTake= tempRegion.invest(money);
                this.nowPlayer.setBudget(this.getBudget() - money);
                if (canTake) {
                    tempRegion.changeOwner(this.nowPlayer.getName());
                    this.nowPlayer.addRegionPossessing(targetAddress);
                }
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean done() {
        return true;
    }

    @Override
    public boolean move(long direction) {
        Address targetAddress = directionAddress(nowPlayer.getCityCrew(), direction);
        if (targetAddress == null) {
            return false;
        }
        Region targetRegion = this.TerritoryMap.getRegion(targetAddress);
        if (targetRegion.isOwner(nowPlayer.getName()) || targetRegion.isOwner("")) {
            nowPlayer.setCityCrew(targetAddress);
            return true;
        }

        return false;
    }


    @Override
    public boolean loseCenter(Player loser) {
        loser.setSurvival(false);
        ArrayList<Address> listAddress = loser.getRegionPossessing();
        for (int i = 0; i < listAddress.size(); i++) {
            Region loseRegion = this.TerritoryMap.getRegion(listAddress.get(i));
            loseRegion.isOwner("");
        }
        loser.setRegionPossessing(new ArrayList<>());
        return false;
    }

    @Override
    public boolean loseRegion() {
        return false;
    }

    public long getBudget() {
        return nowPlayer.getBudget();
    }

    @Override
    public long getIntRate(Address address) {
        return TerritoryMap.getRegion(address).Int(nowPlayer.getPlayer_turn());
    }

    @Override
    public long getCurrow() {
        return nowPlayer.getCityCrew().PositionRow();
    }

    @Override
    public long getCurcol() {
        return nowPlayer.getCityCrew().PositionColumn();
    }

    @Override
    public long getRows() {
        return configurationValue.getM();
    }

    @Override
    public long getCol() {
        return configurationValue.getN();
    }

    @Override
    public long getMaxdeposit() {
        return configurationValue.getMax_dep();
    }


    @Override
    public long useRandom() {
        return GameDataStorage.getRandom().nextInt(1000);
    }

    @Override
    public long getRegionDeposit(Address address) {

        return TerritoryMap.getRegion(address).giveDeposit(nowPlayer.getName());
    }

    @Override
    public long opponent() {
        long minimalDistance = Long.MAX_VALUE;
        long direction = 0;
        for (long i = 1; i <= 6; i++) {
            long tempDistance = this.nearby(i) / 100;
            if (minimalDistance > tempDistance && tempDistance != 0) {
                minimalDistance = tempDistance;
                direction = i;
            }
            //System.out.println("op "+i);
        }

        return direction;
    }

    @Override
    public long nearby(long direction) {
        long count = 0;
        Region tempRegion;
        Address tempAddress = directionAddress(nowPlayer.getCityCrew(), direction);

        while (tempAddress != null) {
            count++;
           // System.out.println(count);
            tempRegion = this.TerritoryMap.getRegion(tempAddress);
            //System.out.println(tempRegion.toString());
            if (!tempRegion.isOwner(nowPlayer.getName()) && !tempRegion.isOwner("")) {
                return (long) (count * 100 + Math.floor(Math.log10(tempRegion.giveDeposit(nowPlayer.getName()) * -1)));
            }

            tempAddress = directionAddress(tempAddress, direction);
            //System.out.println(tempAddress.toString());
        }
        return 0;
    }

    @Override
    public Address directionAddress(Address targetAddress, long direction) throws ArithmeticException {
        /*Address Row and Column start at 1,1*/
        Address nextAddress;
        if (targetAddress == null) {
            throw new NullPointerException();
        }
        try {
            if (targetAddress.PositionColumn() % 2 == 1)
                nextAddress = switch ((int) direction) {
                    case 1 ->
                            new Address(targetAddress.PositionRow() - 1, targetAddress.PositionColumn()); /*"up"         r-1,c+0     */
                    case 2 ->
                            new Address(targetAddress.PositionRow(), targetAddress.PositionColumn() + 1); /*"upright"    r+0,c+1     */
                    case 3 ->
                            new Address(targetAddress.PositionRow() + 1, targetAddress.PositionColumn() + 1); /*"downright"  r+1,c+1     */
                    case 4 ->
                            new Address(targetAddress.PositionRow() + 1, targetAddress.PositionColumn()); /*"down"       r+1,c+0     */
                    case 5 ->
                            new Address(targetAddress.PositionRow() + 1, targetAddress.PositionColumn() - 1); /*"downleft"   r+1,c-1     */
                    case 6 ->
                            new Address(targetAddress.PositionRow(), targetAddress.PositionColumn() - 1); /*"upleft"     r+0,c-1     */
                    default -> throw new EvalError("Wrong input" + direction);
                };
            else
                nextAddress = switch ((int) direction) {
                    case 1 ->
                            new Address(targetAddress.PositionRow() - 1, targetAddress.PositionColumn()); /*"up"         r-1,c+0    */
                    case 2 ->
                            new Address(targetAddress.PositionRow() - 1, targetAddress.PositionColumn() + 1); /*"upright"    r-1,c+1    */
                    case 3 ->
                            new Address(targetAddress.PositionRow(), targetAddress.PositionColumn() + 1); /*"downright"  r+0,c+1    */
                    case 4 ->
                            new Address(targetAddress.PositionRow() + 1, targetAddress.PositionColumn()); /*"down"       r+1,c+0    */
                    case 5 ->
                            new Address(targetAddress.PositionRow(), targetAddress.PositionColumn() - 1); /*"downleft"   r+0,c-1    */
                    case 6 ->
                            new Address(targetAddress.PositionRow() - 1, targetAddress.PositionColumn() -1); /*"upleft"     r-1,c-1    */
                    default -> throw new EvalError("Wrong input" + direction);
                };
        } catch (ArithmeticException e) {
            return null;
        }
        if ((nextAddress.PositionRow() - 1) < 0 || (nextAddress.PositionRow() - 1) >=this.configurationValue.getM()) {
            return null;
        }
        if ((nextAddress.PositionColumn() - 1) < 0 || (nextAddress.PositionColumn() - 1) >= this.configurationValue.getN()) {
            return null;
        }
        return nextAddress;
    }

}
