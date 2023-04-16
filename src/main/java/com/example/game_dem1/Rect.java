package com.example.game_dem1;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Rect {
    public int X;
    public int Y;
    public Rectangle rectangle;

    Chip chip;
    public Rect(Chip[] chip, Rectangle rectangle) {
        this.rectangle=rectangle;
    }
    public int getX(){
        return (int)rectangle.getX();
    }
    public int getY(){
        return (int)rectangle.getY();
    }
    public boolean hasChip() {return this.chip != null;}

    public void setChip(Chip chip) {
        this.chip = chip;
    }

    public void removeChip() {
        this.chip = null;
    }
}
