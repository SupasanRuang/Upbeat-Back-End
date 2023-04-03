package BackEnd;

public class Address {
    private long posRow;
    private long posCol;
    public Address(long row, long col) throws ArithmeticException{
        if(row <0|| col <0)
            throw new ArithmeticException("parameter is more than zero");
        this.posRow = row;
        this.posCol = col;
    }

    long PositionRow(){
        return this.posRow;
    }

    long PositionColumn(){
        return this.posCol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return this.posRow == address.posRow && this.posCol == address.posCol;
    }

    @Override
    public int hashCode() {
        return (int) (posRow *1031+ posCol);
    }

    @Override
    public String toString() {
        return
                "(" + posRow +
                "," + posCol +
                ')';
    }
}
