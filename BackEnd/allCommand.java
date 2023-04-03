//package BackEnd;
//
//import ErrorAndException.EvalError;
//
//public class allCommand implements listCommand{
//
//
//    @Override
//    public boolean relocate(Address targetAddress, Address centerAddress) {
//        return false;
//    }
//
//    @Override
//    public boolean invest(Address targetAddress, long money) {
//        return false;
//    }
//
//    @Override
//    public boolean done() {
//        return true;
//    }
//
//    @Override
//    public boolean move(long direction) {
//        return false;
//    }
//
//    @Override
//    public boolean collect(Address targetAddress, long money) {
//        return false;
//    }
//
//    @Override
//    public boolean shoot(Address targetAddress, long money) {
//        return false;
//    }
//
//    @Override
//    public boolean loseCenter() {
//        return false;
//    }
//
//    @Override
//    public boolean loseRegion() {
//        return false;
//    }
//
//    @Override
//    public long getSpecialVariables(String name) {
//        return 0;
//    }
//
//    @Override
//    public long useRandom() {
//        return 0;
//    }
//
//    @Override
//    public long getRegionDeposit(Address address) {
//        return 0;
//    }
//
//    @Override
//    public long opponent(long direction) {
//        return 0;
//    }
//
//    @Override
//    public long nearby(long direction) {
//        return 0;
//    }
//
//    @Override
//    public Address directionAddress(Address targetAddress, long direction)throws ArithmeticException,EvalError {
//        /*Address Row and Column start at 0,0*/
//        Address nextAddress=null;
//        if(targetAddress.PositionRow()%2==0)
//            nextAddress= switch ((int) direction) {
//                case 1 -> new Address(targetAddress.PositionRow()-1, targetAddress.PositionColumn()); /*"up"         r-1,c+0     */
//                case 2 -> new Address(targetAddress.PositionRow(),targetAddress.PositionColumn()+1); /*"upright"    r+0,c+1     */
//                case 3 -> new Address(targetAddress.PositionRow()+1,targetAddress.PositionColumn()+1); /*"downright"  r+1,c+1     */
//                case 4 -> new Address(targetAddress.PositionRow()+1, targetAddress.PositionColumn()); /*"down"       r+1,c+0     */
//                case 5 -> new Address(targetAddress.PositionRow()+1,targetAddress.PositionColumn()-1); /*"downleft"   r+1,c-1     */
//                case 6 -> new Address(targetAddress.PositionRow(),targetAddress.PositionColumn()-1); /*"upleft"     r+0,c-1     */
//                default -> throw new EvalError("Wrong input" + direction);
//        };
//        else
//            nextAddress= switch ((int) direction) {
//                case 1 -> new Address(targetAddress.PositionRow()-1, targetAddress.PositionColumn()); /*"up"         r-1,c+0    */
//                case 2 -> new Address(targetAddress.PositionRow()-1,targetAddress.PositionColumn()+1); /*"upright"    r-1,c+1    */
//                case 3 -> new Address(targetAddress.PositionRow(),targetAddress.PositionColumn()+1); /*"downright"  r+0,c+1    */
//                case 4 -> new Address(targetAddress.PositionRow()+1, targetAddress.PositionColumn()); /*"down"       r+1,c+0    */
//                case 5 -> new Address(targetAddress.PositionRow(),targetAddress.PositionColumn()-1); /*"downleft"   r+0,c-1    */
//                case 6 -> new Address(targetAddress.PositionRow()-1,targetAddress.PositionColumn()+1); /*"upleft"     r-1,c-1    */
//                default -> throw new EvalError("Wrong input" + direction);
//            };
//        try{
//            tgetRegion(nextAddress);
//        } catch (NullPointerException e) {
//            throw new RuntimeException(e);
//        } catch (ArrayIndexOutOfBoundsException e) {
//           nextAddress=new Address(0,0);
//        }
//        return nextAddress;
//    }
//}
