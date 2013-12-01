package forum.framework;

import java.io.*;

public final class Position implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final int X_DIM = 20;
    private static final int Y_DIM = 20;
    private static Position[][] positions;
    private final int x;
    private final int y;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    static {
        Position.positions = new Position[20][20];
        for (int i = 0; i < Position.positions.length; ++i) {
            for (int j = 0; j < Position.positions[i].length; ++j) {
                Position.positions[i][j] = new Position(i, j);
            }
        }
    }
    
    private Position(final int x, final int y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    public static Position getPosition(final int x, final int y) {
        if (!Position.$assertionsDisabled && x >= 20) {
            throw new AssertionError();
        }
        if (!Position.$assertionsDisabled && y >= 20) {
            throw new AssertionError();
        }
        return Position.positions[x][y];
    }
    
    static Position getStartPosition() {
        return Position.positions[10][10];
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    Position northOf() {
        return getPosition(this.x, (this.y + 1) % 20);
    }
    
    Position eastOf() {
        return getPosition((this.x + 1) % 20, this.y);
    }
    
    Position southOf() {
        return getPosition(this.x, (this.y + 20 - 1) % 20);
    }
    
    Position westOf() {
        return getPosition((this.x + 20 - 1) % 20, this.y);
    }
    
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
