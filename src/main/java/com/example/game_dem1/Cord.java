package com.example.game_dem1;

import java.util.Objects;

public class Cord {
    public int X;
    public int Y;

    public Cord(int X,int Y){
    this.X=X;
    this.Y=Y;
    }
    public void setCor(int x,int y) {
        X = x;
        Y=y;
    }
        public void setX(int x) {
        X = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cord cord = (Cord) o;
        return X == cord.X && Y == cord.Y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }

    @Override
    public String toString() {
        return "Cord{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

    public void setY(int y) {
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
