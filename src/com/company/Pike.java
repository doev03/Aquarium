package com.company;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Pike extends Fish {
    Pike(Graphics2D g, BufferedImage img) {
        super(g, img);
    }

    public void init() {
        Random rnd = new Random();
        init(rnd.nextInt(img.getWidth() - 2 * (int) getSpeed()) + (int) getSpeed(),
                rnd.nextInt(img.getHeight() - 2 * (int) getSpeed()) + (int) getSpeed(),
                40 + rnd.nextInt(20), 5);
    }

    @Override
    void draw() {
        Graphics2D g = getGraphics2D();
        g.setColor(new Color(Colors.PIKE));

        Path2D.Double triangle = new Path2D.Double();

        int x2 = (int) Math.round(getX() - getLength() * Math.sin(getAngle() + Math.PI / 15));
        int y2 = (int) Math.round(getY() + getLength() * Math.cos(getAngle() + Math.PI / 15));
        int x3 = (int) Math.round(getX() - getLength() * Math.sin(getAngle() - Math.PI / 15));
        int y3 = (int) Math.round(getY() + getLength() * Math.cos(getAngle() - Math.PI / 15));
        triangle.moveTo(getX(), getY());
        triangle.lineTo(x2, y2);
        triangle.lineTo(x3, y3);
        triangle.closePath();
        g.fill(triangle);
    }

    public IntersectionPoint look() {
        return super.look(60);
    }

    @Override
    public void run() {
        IntersectionPoint point = look();

        if (point != null && point.getColor() == Colors.CARP) {
            rotate(2 * getAngle() - point.getAngle());
        } else if (new Random().nextInt(10) == 1) {
            rotateBy(new Random().nextBoolean() ? Math.PI / 20 : -Math.PI / 20);
        }

        super.run();
    }
}
