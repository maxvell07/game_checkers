package com.example.game_dem1;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Chip {
    Boolean hasChip;
    public int X;
    public int Y;

    public Circle circle;

    public Chip(int X, int Y, Circle circle, boolean queen) {
        hasChip = false;
        this.X = X;
        this.Y = Y;
        this.circle = circle;
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
}


