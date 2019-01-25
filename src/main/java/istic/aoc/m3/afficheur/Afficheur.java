package istic.aoc.m3.afficheur;

import java.awt.*;
import java.awt.geom.*;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.swing.*;

import istic.aoc.m3.generator.GeneratorAsync;
import istic.aoc.m3.observer.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author VinYarD
 * created : 13/11/2018, 16:41
 */

public class Afficheur extends JPanel implements Observer<GeneratorAsync> {

    private static final Logger log = LoggerFactory.getLogger(Afficheur.class);

    private Optional<Long> value = Optional.empty();
    private final Color color;
    private String name;

    public Afficheur(Color color, String name) {
        this.color = color;
        this.name = name;

        this.setOpaque(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        this.setBackground(this.color);
        g2.setFont(new Font("Courier", Font.BOLD, 72));
        Rectangle2D bounds = g2.getFontMetrics().getStringBounds(this.value.isPresent() ? String.valueOf(this.value.get()) : "", g2);
        g2.drawString(this.value.isPresent() ? String.valueOf(this.value.get()) : "", (int) (this.getWidth() / 2 - bounds.getCenterX()),
                (int) (this.getHeight() / 2 - bounds.getCenterY()));
    }

    @Override
    public void update(GeneratorAsync g) {
    
        try {
            this.value = Optional.of(g.getValue().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        this.repaint();

        log.info("{} MAJ : {}", this.name, this.value);
        
    }
}
