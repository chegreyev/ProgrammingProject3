public class Safe {
    private int n;
    private String side;
    private Position position;

    public Safe(int N, String side, Position position) {
        this.n = N;
        this.side = side;
        this.position = position;
    }

    public int getN() {
        return this.n;
    }

    public String getSide() {
        return this.side;
    }

    public Position getPosition() {
        return this.position;
    }
}
