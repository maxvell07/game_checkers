package com.example.game_dem1;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Chip {
    Boolean hasChip;
    public int X;
    public int Y;

    public Circle circle;
    boolean queen =false;

    public Chip(int X, int Y, Circle circle,boolean hasChip, boolean queen) {
        this.hasChip = hasChip;
        this.X = X;
        this.Y = Y;
        this.circle = circle;
        this.queen=queen;
    }

    public void placeChip() {
        hasChip = true;
    }

    public int getX() {
        return (int) X;
    }

    public int getY() {
        return (int) Y;
    }

    public void moveChip(int X, int Y) {
        this.X = X;
        this.Y = Y;
        circle.setCenterX((double) X);
        circle.setCenterY((double) Y);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chip chip = (Chip) o;

        if (X != chip.X) return false;
        if (Y != chip.Y) return false;
        if (queen != chip.queen) return false;
        if (hasChip != null ? !hasChip.equals(chip.hasChip) : chip.hasChip != null) return false;
        return circle != null ? circle.equals(chip.circle) : chip.circle == null;
    }

    @Override
    public int hashCode() {
        int result = hasChip != null ? hasChip.hashCode() : 0;
        result = 31 * result + X;
        result = 31 * result + Y;
        result = 31 * result + (circle != null ? circle.hashCode() : 0);
        result = 31 * result + (queen ? 1 : 0);
        return result;
    }

}


