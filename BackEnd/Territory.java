package BackEnd;

public class Territory {
    private  int m,n;
    private  long max_dep,interest_pct;
    private Region[][] territory=null;


   public Territory(long m,long n,long max_dep,long interest_pct){
       this.m = (int) m;
       this.n = (int) n;
       this.max_dep = max_dep;
       this.interest_pct = interest_pct;
       this.territory = new Region[(int) m][(int) n];
        for(int i =0;i<m;i++){
            for(int j=0;j<n;j++){
                this.territory[i][j] = new Region(this.max_dep,this.interest_pct,i+1,j+1);
            }
        }
    }


    public  Region getRegion(Address address) throws NullPointerException,ArrayIndexOutOfBoundsException
    {

        if((address.PositionRow()-1)<0||(address.PositionRow()-1)>this.m)
        {
            throw new ArrayIndexOutOfBoundsException("Row is OutOfBounds");
        }
        if((address.PositionColumn()-1)<0||(address.PositionColumn()-1)>this.n)
        {
            throw new ArrayIndexOutOfBoundsException("Column is OutOfBounds");
        }
        return this.territory[(int) (address.PositionRow()-1)][(int) address.PositionColumn()-1];
    }
}
