package Code.game.src.main.java.com.example;

enum Object{
    NONE,
    WALKWAY,
    WALL,
    FIRE,
    AID,
    SPAWN,
    TREASURE
}

public abstract class Square {
    private Object content;
    private boolean blocks;
    private int effectOnHealthPoints;

    Square(){
        this.content = Object.NONE;
        this.blocks = false;
        this.effectOnHealthPoints = 0;
    }

    public Object getContent() {
        return content;
    }

    public boolean isBlocking() {
        return blocks;
    }

    public int getEffectOnHealthPoints() {
        return effectOnHealthPoints;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public void setBlocks(boolean blocks) {
        this.blocks = blocks;
    }

    public void setEffectOnHealthPoints(int effectOnHealthPoints) {
        this.effectOnHealthPoints = effectOnHealthPoints;
    }
}
