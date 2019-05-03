public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public boolean equals(Position other){
        return this.getX() == other.getX();
    }

    public String toString() {
        return ""+this.x+" "+this.y ;
    }
}