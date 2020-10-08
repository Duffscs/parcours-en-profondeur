package draw;
import type.Position;
class Noeud {
    private Position pos;
    String name;

    public Noeud(String name, Position pos) {
        this.pos = pos;
        this.name = name;
    }

    public int x() {
        return this.pos.x();
    }

    public int y() {
        return this.pos.y();
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}

