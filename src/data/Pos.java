package data;

public class Pos {
    private int left;
    private int right;

    public Pos(int left, int right) {
        this.left = left;
        this.right = right;
    }
    
    public boolean isInteraval() {
        return this.left < this.right;
    }
    
    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
}
