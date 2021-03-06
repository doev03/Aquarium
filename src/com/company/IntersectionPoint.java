package com.company;

public class IntersectionPoint {
    private Fish fish;
    private int color;
    private int dist;
    private int x;
    private int y;
    private double angle;

    IntersectionPoint(Fish fish, int color, int dist, int x, int y, double angle) {
        this.fish = fish;
        this.color = color;
        this.dist = dist;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public Fish getFish() {
        return fish;
    }

    public int getColor() {
        return color;
    }

    public int getDist() {
        return dist;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }
}