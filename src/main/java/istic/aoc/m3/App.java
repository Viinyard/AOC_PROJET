package istic.aoc.m3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import istic.aoc.m3.diffusion.AtomiqueStrategy;
import istic.aoc.m3.diffusion.CausaleStrategy;
import istic.aoc.m3.diffusion.SequentielStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import istic.aoc.m3.active.Canal;
import istic.aoc.m3.afficheur.Afficheur;
import istic.aoc.m3.generator.GeneratorImpl;

/**
 * Hello world!
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {
        final JFrame frame = new JFrame("Active Object Generator");
        frame.setMinimumSize(new Dimension(400, 400));

        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
    
        final GeneratorImpl generator = new GeneratorImpl();

        // les boutons radio pour la diffusion
        {
            final JPanel diffusionPanel = new JPanel();
            diffusionPanel.setLayout(new GridLayout(1, 3));

            final ButtonGroup bg = new ButtonGroup();

            // diffusion atomique
            final JRadioButton radioAtomique = new JRadioButton("Atomique");
            radioAtomique.addActionListener(x -> generator.setDiffusionStrategy(new AtomiqueStrategy()));
            bg.add(radioAtomique);
            diffusionPanel.add(radioAtomique);
            radioAtomique.doClick();

            // diffusion sequentielle
            final JRadioButton radioSequentielle = new JRadioButton("Sequentielle");
            radioSequentielle.addActionListener(x -> generator.setDiffusionStrategy(new SequentielStrategy()));
            bg.add(radioSequentielle);
            diffusionPanel.add(radioSequentielle);

            // diffusion causale
            final JRadioButton radioCausale = new JRadioButton("Causale");
            radioCausale.addActionListener(x -> generator.setDiffusionStrategy(new CausaleStrategy()));
            bg.add(radioCausale);
            diffusionPanel.add(radioCausale);

            contentPane.add(diffusionPanel, BorderLayout.NORTH);

        }

        JPanel afficheurContainer = new JPanel();

        afficheurContainer.setLayout(new GridLayout(2, 2));


        {

            final Afficheur afficheur = new Afficheur(Color.WHITE, "Blanc");
            afficheurContainer.add(afficheur);

            final Canal canal = new Canal();
            canal.addObserver(afficheur);

            generator.addObserver(canal);
        }
        {
            final Afficheur afficheur = new Afficheur(Color.RED, "Rouge");
            afficheurContainer.add(afficheur);

            final Canal canal = new Canal();
            canal.addObserver(afficheur);

            generator.addObserver(canal);
        }
        {
            final Afficheur afficheur = new Afficheur(Color.GREEN, "Vert");
            afficheurContainer.add(afficheur);

            final Canal canal = new Canal();
            canal.addObserver(afficheur);

            generator.addObserver(canal);
        }
        {
            final Afficheur afficheur = new Afficheur(Color.BLUE, "Bleu");
            afficheurContainer.add(afficheur);

            final Canal canal = new Canal();
            canal.addObserver(afficheur);

            generator.addObserver(canal);
        }

        contentPane.add(afficheurContainer, BorderLayout.CENTER);

        JPanel controlContainer = new JPanel();

        controlContainer.setLayout(new GridLayout(1, 2));

        final JButton start = new JButton("Start");
        final JButton stop = new JButton("Stop");
        stop.setEnabled(false);

        RunnableGenerator runnableGenerator = new RunnableGenerator(generator);
        
        start.addActionListener(new ActionListener() {
            Thread t;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (t == null || !t.isAlive()) {
                    t = new Thread(runnableGenerator);
                    t.start();
                    start.setEnabled(!start.isEnabled());
                    stop.setEnabled(!stop.isEnabled());
                }

            }
        });
        controlContainer.add(start);
        stop.addActionListener(e -> {
					runnableGenerator.stop();
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
