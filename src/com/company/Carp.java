package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Carp extends Fish {
    private boolean isExists = true;

    Carp(Graphics2D g, BufferedImage img) {
        super(g, img);
    }

    public void init() {
        Random rnd = new Random();
        init(rnd.nextInt(img.getWidth() - 2 * (int) getSpeed()) + (int) getSpeed(),
                rnd.nextInt(img.getHeight() - 2 * (int) getSpeed()) + (int) getSpeed(),
                20 + rnd.nextInt(20), 5);
    }

    @Override
    void draw() {
        Graphics2D g = getGraphics2D();

        g.setColor(new Color(Colors.CARP));
        g.drawLine(getX(), getY(),
                (int) (getX() - getLength() * Math.sin(getAngle())), (int) (getY() + getLength() * Math.cos(getAngle())));

        double beta = getAngle();
        double alfa = Math.PI / 5;
        int r1 = getLength() / 3;

        int x2 = (int) Math.round(getX() - r1 * Math.sin(beta + alfa));
        int y2 = (int) Math.round(getY() + r1 * Math.cos(beta + alfa));
        g.drawLine(getX(), getY(), x2, y2);

        x2 = (int) Math.round(getX() - r1 * Math.sin(beta - alfa));
        y2 = (int) Math.round(getY() + r1 * Math.cos(beta - alfa));
        g.drawLine(getX(), getY(), x2, y2);
    }

    public IntersectionPoint look() {
        return super.look(60);
    }

    @Override
    public void run() {
        IntersectionPoint point = look();

        if (point != null && (point.getColor() == Colors.PIKE || point.getColor() == 0)) {
            rotate(point.getAngle());
        } else if (new Random().nextInt(10) == 1) {
            rotateBy(new Random().nextBoolean() ? Math.PI / 20 : -Math.PI / 20);
        }

        super.run();

        if (point != null && point.getColor() == Colors.PIKE && point.getDist() <= 30) {
            isExists = false;
        }
    }

    public boolean isExists() {
        return isExists;
    }
}
