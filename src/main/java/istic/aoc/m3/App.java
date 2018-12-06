package istic.aoc.m3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import istic.aoc.m3.active.Canal;
import istic.aoc.m3.afficheur.Afficheur;
import istic.aoc.m3.diffusion.Diffusion;
import istic.aoc.m3.generator.GeneratorImpl;

/**
 * Hello world!
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static Diffusion diffusionSelectionee;

    public static void definirDiffusion(final Diffusion diffusion) {
        // il est inutile de continuer plus loin
        if (diffusion == App.diffusionSelectionee) {
            return;
        }
        log.info("Changement de diffusion : {} -> {}", App.diffusionSelectionee, diffusion);
        App.diffusionSelectionee = diffusion;
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("test");
        frame.setMinimumSize(new Dimension(400, 400));

        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        // les boutons radio pour la diffusion
        {
            final JPanel diffusionPanel = new JPanel();
            diffusionPanel.setLayout(new GridLayout(1, 3));

            final ButtonGroup bg = new ButtonGroup();

            // diffusion atomique
            final JRadioButton radioAtomique = new JRadioButton("Atomique");
            radioAtomique.addActionListener(x -> App.definirDiffusion(Diffusion.ATOMIQUE));
            bg.add(radioAtomique);
            diffusionPanel.add(radioAtomique);

            // diffusion sequentielle
            final JRadioButton radioSequentielle = new JRadioButton("Sequentielle");
            radioSequentielle.addActionListener(x -> App.definirDiffusion(Diffusion.SEQUENTIELLE));
            bg.add(radioSequentielle);
            diffusionPanel.add(radioSequentielle);

            // diffusion causale
            final JRadioButton radioCausale = new JRadioButton("Causale");
            radioCausale.addActionListener(x -> App.definirDiffusion(Diffusion.CAUSALE));
            bg.add(radioCausale);
            diffusionPanel.add(radioCausale);

            contentPane.add(diffusionPanel, BorderLayout.NORTH);

        }

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

        contentPane.add(afficheurContainer, BorderLayout.CENTER);

        JPanel controlContainer = new JPanel();

        controlContainer.setLayout(new GridLayout(1, 2));

        final JButton start = new JButton("Start");
        final JButton stop = new JButton("Stop");
        stop.setEnabled(false);

        start.addActionListener(new ActionListener() {
            Thread t;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (t == null || !t.isAlive()) {
                    t = new Thread(generator);
                    t.start();
                    start.setEnabled(!start.isEnabled());
                    stop.setEnabled(!stop.isEnabled());
                }

            }
        });
        controlContainer.add(start);
        stop.addActionListener(e -> {
            generator.stop();
            start.setEnabled(!start.isEnabled());
            stop.setEnabled(!stop.isEnabled());
        });
        controlContainer.add(stop);

        contentPane.add(controlContainer, BorderLayout.SOUTH);

        frame.setContentPane(contentPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

}
