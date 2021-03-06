package com.company;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Aquarium extends Frame {
    Graphics2D g;
    ArrayList<Pike> pikes;
    ArrayList<Carp> carps;
    BufferedImage img;

    public Aquarium(String title) {
        super(title);
        setSize(800, 600);
        setResizable(false);
        setBackground(new Color(84, 128, 194));
        setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(12, getHeight() - 40, getWidth(), 30);
        add(controlPanel);
        controlPanel.setBackground(new Color(-11239230));

        Button addCarp = new Button("Добавить карпа");
        addCarp.setBounds(0, 0, 200, 30);
        addCarp.setBackground(new Color(Colors.CARP));
        controlPanel.add(addCarp);
        addCarp.addActionListener(e -> {
            Carp carp = new Carp(g, img);
            carp.init();
            carps.add(carp);
        });

        Button addPike = new Button("Добавить щуку");
        addPike.setBounds(controlPanel.getWidth() - 222, 0, 200, 30);
        addPike.setBackground(new Color(Colors.PIKE));
        controlPanel.add(addPike);
        addPike.addActionListener(e -> {
            Pike pike = new Pike(g, img);
            pike.init();
            pikes.add(pike);
        });
    }


    public void init() {
        Random rnd = new Random();
        img = new BufferedImage(getWidth(), getHeight() - 30, BufferedImage.TYPE_INT_ARGB);
        g = img.createGraphics();

        carps = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            carps.add(new Carp(g, img));
            carps.get(i).init(100 + i * 20, 200 + i * 10, 20 + rnd.nextInt(20), 5);
        }

        pikes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pikes.add(new Pike(g, img));
            pikes.get(i).init(500 + i * 20, 500 + i * 10, 40 + rnd.nextInt(20), 5);
        }
    }

    public void run() {
        ActionListener timer = e -> {
            g.clearRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(Colors.WATER));
            g.fillRect(0, 0, getWidth(), getHeight());

            for (int i = 0; i < carps.size(); i++) {
                if (!carps.get(i).isExists()) {
                    carps.remove(i);
                } else {
                    carps.get(i).draw();
                }
            }
            for (int i = 0; i < pikes.size(); i++) {
                Pike value = pikes.get(i);
                value.draw();
            }

            for (int i = 0; i < carps.size(); i++) {
                Carp carp = carps.get(i);
                carp.run();
            }
            for (int i = 0; i < pikes.size(); i++) {
                Pike pike = pikes.get(i);
                pike.run();
            }

            getGraphics().drawImage(img, 0, 10, this);
        };
        new Timer(40, timer).start();
    }
}