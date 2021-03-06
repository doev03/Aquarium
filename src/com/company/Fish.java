package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Fish {
    BufferedImage img;
    private double x;
    private double y;
    private double angle;
    private double speed;
    private int length;
    private Graphics2D g;

    int getX() {
        return (int) x;
    }

    int getY() {
        return (int) y;
    }

    double getAngle() {
        return angle;
    }

    double getSpeed() {
        return speed;
    }

    int getLength() {
        return length;
    }

    Graphics2D getGraphics2D() {
        return g;
    }

    Fish(Graphics2D g, BufferedImage img) {
        this.g = g;
        g.setStroke(new BasicStroke(3));
        this.img = img;
    }

    public void init(int x, int y, int length, double speed) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.speed = speed;
    }

    abstract void draw();

    public IntersectionPoint look(int rangeVision) {
        int color;

        for (int i = -29; i <= 30; i++) {
            try {
                color = img.getRGB((int) (x + rangeVision * Math.sin(angle + Math.PI / 60 * i)),
                        (int) (y - rangeVision * Math.cos(angle + Math.PI / 60 * i)));
            } catch (Exception e) {
                continue;
            }

            if (color != Colors.WATER) {
                return new IntersectionPoint(this, color, rangeVision, (int) (x + rangeVision * Math.sin(angle + Math.PI / 60 * i)),
                        (int) (y - rangeVision * Math.cos(angle + Math.PI / 60 * i)), angle - Math.PI / 60 * i);
            }
        }

        for (int i = -29; i <= 30; i++) {
            try {
                color = img.getRGB((int) (x + rangeVision / 2 * Math.sin(angle + Math.PI / 60 * i)),
                        (int) (y - rangeVision / 2 * Math.cos(angle + Math.PI / 60 * i)));
            } catch (Exception e) {
                continue;
            }

            if (color != Colors.WATER) {
                return new IntersectionPoint(this, color, rangeVision / 2, (int) (x + rangeVision / 2 * Math.sin(angle + Math.PI / 60 * i)),
                        (int) (y - rangeVision / 2 * Math.cos(angle + Math.PI / 60 * i)), angle - Math.PI / 60 * i);
            }
        }

        return null;
    }

    public void run() {
        double x1 = x + speed * Math.sin(angle);
        double y1 = y - speed * Math.cos(angle);
        if (x1 > speed + 20 && y1 > speed + 20 && x1 < img.getWidth() - speed - 20 && y1 < img.getHeight() - speed - 20) {
            x = x1;
            y = y1;
        } else {
            rotateBy(new Random().nextBoolean() ? Math.PI / 10 : -Math.PI / 10);
        }
    }

    public void rotate(int x1, int y1) {
        this.angle = Math.atan2(x1 - x, -(y1 - y));
    }

    public void rotate(double angle) {
        this.angle = angle;
    }

    public void rotateBy(double angle) {
        this.angle += angle;
    }
}
