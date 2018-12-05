package istic.aoc.m3;

import java.awt.*;

import javax.swing.*;

import istic.aoc.m3.active.Canal;
import istic.aoc.m3.afficheur.Afficheur;
import istic.aoc.m3.generator.GeneratorImpl;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel afficheurContainer = new JPanel();

        afficheurContainer.setLayout(new GridLayout(2, 2));

        final GeneratorImpl generator = new GeneratorImpl();

        {

            final Afficheur afficheur = new Afficheur(Color.WHITE);
            afficheurContainer.add(afficheur);

            final Canal canal = new Canal();
            canal.attach(afficheur);

            generator.attach(canal);
        }
        {
            final Afficheur afficheur = new Afficheur(Color.RED);
            afficheurContainer.add(afficheur);

            final Canal canal = new Canal();
            canal.attach(afficheur);

            generator.attach(canal);
        }
        {
            final Afficheur afficheur = new Afficheur(Color.GREEN);
            afficheurContainer.add(afficheur);

            final Canal canal = new Canal();
            canal.attach(afficheur);

            generator.attach(canal);
        }
        {
            final Afficheur afficheur = new Afficheur(Color.BLUE);
            afficheurContainer.add(afficheur);

            final Canal canal = new Canal();
            canal.attach(afficheur);

            generator.attach(canal);
        }

        contentPane.add(afficheurContainer);

        JPanel controlContainer = new JPanel();

        controlContainer.setLayout(new GridLayout(1, 2));

        controlContainer.add(new JButton("start"));
        controlContainer.add(new JButton("stop"));

        contentPane.add(controlContainer, BorderLayout.SOUTH);

        frame.setContentPane(contentPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        new Thread(generator).start();
    }

}
