package istic.aoc.m3.afficheur;

import java.awt.*;
import java.awt.geom.*;
import java.util.concurrent.Future;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import istic.aoc.m3.generator.Generator;
import istic.aoc.m3.observer.Observer;

/**
 * @author VinYarD
 * created : 13/11/2018, 16:41
 */

public class Afficheur extends JPanel implements Observer<Void> {

    private static final Logger log = LoggerFactory.getLogger(Afficheur.class);

    private long value;
    private final Color color;
    private final String readableColor;

    public Afficheur(Color color) {
        this.color = color;
        if (this.color == Color.RED) {
            this.readableColor = "Red  ";
        } else if (this.color == Color.BLUE) {
            this.readableColor = "Blue ";
        } else if (this.color == Color.WHITE) {
            this.readableColor = "White";
        } else if (this.color == Color.GREEN) {
            this.readableColor = "Green";
        } else {
            this.readableColor = "None ";
        }

        this.setOpaque(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        this.setBackground(this.color);

        Rectangle2D bounds = g2.getFontMetrics().getStringBounds(String.valueOf(this.value), g2);
        g2.drawString(String.valueOf(this.value), (int) (this.getWidth() / 2 - bounds.getCenterX()),
                (int) (this.getHeight() / 2 - bounds.getCenterY()));
    }

    @Override
    public Future<Void> update(Generator g) {

        this.value = g.getValue();
        this.repaint();

        log.info("Nouvelle valeur pour l'afficheur {} : {}", this.readableColor, this.value);

        return null;
    }
}
